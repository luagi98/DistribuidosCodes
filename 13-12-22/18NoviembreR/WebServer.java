
//Librerias para construir un server en Java

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class WebServer {
    // Cadenas endpoint del server
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String SEARCHTOKEN_ENDPOINT = "/searchtoken";
    private static final String ISPRIME_ENDPOINT = "/isprime";

    private final int port;
    private HttpServer server;// Implementa un server HTTP sencillo

    public static void main(String[] args) {
        // Se asigna el puerto
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();// Inicializa la configuración del server

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public WebServer(int port) {// Constructor, recibe puerto e inicializa el port
        this.port = port;
    }

    public void startServer() {
        try {
            // Se crea una instacia de socket TCP vinculada a una IP y al puerto PORT
            // Segundo parametro: tamaño de la lista de solicitudes pendientes que
            // permitimos al server mantener en una cola de espera
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        /*
         * HttpContext: mapeo entre el identificador uniforme de recursos y un
         * HttpHandler
         */
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);// Crea objeto HttpContext sin un HttpHandler
                                                                          // pero con la ruta asignada
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);

        HttpContext searchTokenContext = server.createContext(SEARCHTOKEN_ENDPOINT);

        HttpContext isPrimeContext = server.createContext(ISPRIME_ENDPOINT);

        statusContext.setHandler(this::handleStatusCheckRequest);// Se vincula el handler para este contexto
        taskContext.setHandler(this::handleTaskRequest);
        searchTokenContext.setHandler(this::handleSearchTokenRequest);
        isPrimeContext.setHandler(this::handleIsPrime);

        server.setExecutor(Executors.newFixedThreadPool(8));// Establece un objeto del tipo executor para el servidor,
                                                            // se proveen 8 hilos
        server.start();// Se inicia la ejecucion del server en un nuevo hilo en segundo plano
    }

    private void handleIsPrime(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String[] keyValue = null;
        String[] finalVal = new String[2];

        if (query != null) {
            String[] partes = query.split("&");

            for (int i = 0; i < partes.length; i++) {
                keyValue = partes[i].split("=");
                finalVal[i] = keyValue[1];
            }

            /*
             * for (String string : finalVal) {
             * System.out.println(string);
             * }
             */
        }
        WebClient cliente = new WebClient();
        CompletableFuture<String> future = new CompletableFuture<>();

        String task = finalVal[0] + "," + finalVal[1];
        System.out.println(task);

        future = cliente.sendTask("http://localhost:8081/task", task.getBytes());

        String responseMessage = future.join();// Recibimos la respuesta del server de procesamiento
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {// Se obtiene el metodo
            exchange.close();// Si no es post cierra el exchange
            return;
        }

        Headers headers = exchange.getRequestHeaders();// Se recuperan los headers con ayuda de map
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";// Respuesta conocida
            sendResponse(dummyResponse.getBytes(), exchange);// Se envia respuesta
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;// Se activa modo debug
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();// recupera cuerpo de mensaje
        byte[] responseBytes = calculateResponse(requestBytes);// Aquí se pasan los numeros que se quieren multiplicar

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            long realTime = (finishTime - startTime);// Tiempo real
            long seconds = (realTime / 1_000_000_000);// segundos
            long temp = (seconds * 1_000_000_000);
            long miliseconds = Math.max(0L, Math.round((realTime - temp) / 1_000_000.0d));
            String debugMessage = String.format("La operacion tomo %d nanosegundos = %d segundos con %d milisegundos",
                    (finishTime - startTime), seconds, miliseconds);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));// Se mete el mensaje a los
                                                                                           // headers de respuesta
        }

        sendResponse(responseBytes, exchange);// Se envia la respuesta y el exchange
    }

    private byte[] calculateResponse(byte[] requestBytes) {// Metodo que multiplica dos numeros del tipo BigInteger
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");

        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {// Se busca que el metodo sea GET
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);// Agrega status code y la longitud de la respuesta
        OutputStream outputStream = exchange.getResponseBody();// Se escribe en el cuerpo del mensaje
        outputStream.write(responseBytes);// Se escribe en el stream
        outputStream.flush();// Se hace limpieza
        outputStream.close();// Se cierra el stream
        exchange.close();// Se cierra el intercambio
    }

    private void handleSearchTokenRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {// Se obtiene el metodo
            exchange.close();// Si no es post cierra el exchange
            return;
        }

        Headers headers = exchange.getRequestHeaders();// Se recuperan los headers con ayuda de map
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";// Respuesta conocida
            sendResponse(dummyResponse.getBytes(), exchange);// Se envia respuesta
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;// Se activa modo debug
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();// recupera cuerpo de mensaje
        String bodyString = new String(requestBytes);
        String[] datos = bodyString.split(",");// n,token
        int response = timesFindedStringBuilder(Integer.parseInt(datos[0]), datos[1]);
        byte[] responseBytes = String.format("El numero de ocurrencias de la palabra %s es %d\n", datos[1], response)
                .getBytes();

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            long realTime = (finishTime - startTime);// Tiempo real
            long seconds = (realTime / 1_000_000_000);// segundos
            long temp = (seconds * 1_000_000_000);
            long miliseconds = Math.max(0L, Math.round((realTime - temp) / 1_000_000.0d));
            String debugMessage = String.format("La operacion tomo %d nanosegundos = %d segundos con %d milisegundos",
                    (finishTime - startTime), seconds, miliseconds);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));// Se mete el mensaje a los
                                                                                           // headers de respuesta
        }

        sendResponse(responseBytes, exchange);// Se envia la respuesta y el exchange
    }

    private int timesFindedStringBuilder(int n, String cadena) {
        // long start = System.nanoTime();
        int occurencies = 0;

        StringBuilder words = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char[] letras = new char[4];
            for (int j = 0; j < letras.length; j++) {
                if (i != 3)
                    letras[j] = (char) ((Math.random() * (26 - 1) + 1) + 64);
                else
                    letras[j] = (char) 32;
            }
            words.append(letras);
        }
        // String ipn = "IPN";

        for (int i = words.indexOf(cadena); i >= 0; i = words.indexOf(cadena, i + 1)) {
            occurencies++;
        }
        // long end = System.nanoTime();
        // System.out.println("Tiempo con StringBuilder: " + (end - start) + "
        // nanosegundos");
        return occurencies;
    }
}