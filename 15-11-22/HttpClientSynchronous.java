import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientSynchronous {

    /**
     * Worker
     */
    public static class Worker implements Runnable {
        String name;
        String ip;

        private static final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        public Worker(String name, String ip) {
            this.name = name;
            this.ip = ip;
        }

        @Override
        public void run() {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://" + ip + ":8080/searchtoken"))
                        .header("X-Debug", "true")
                        .header("Content-Type", "text/plain; charset=UTF-8")
                        .POST(BodyPublishers.ofString("17576000,IPN"))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                // print response headers
                HttpHeaders headers = response.headers();
                headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

                // print status code
                System.out.println(response.statusCode());

                // print response body
                System.out.println(response.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int MAX_T = Integer.parseInt(args[1]);
        String ip = "172.20.10.6";
        int m = Integer.parseInt(args[0]);
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        for (int i = 0; i < m; i++) {
            pool.execute(new Worker("Worker " + (i + 1), ip));
        }
        // passes the Task objects to the pool to execute (Step 3)
        pool.shutdown();
    }

}
