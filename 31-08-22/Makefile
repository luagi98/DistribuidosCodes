JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \ Coordenada.java \ Figura.java \ Rectangulo.java \ Cuadrado.java \ PruebaRectangulo.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java PruebaRectangulo

