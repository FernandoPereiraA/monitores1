// La interfaz Bufer especifica los m�todos llamados por el Productor y el Consumidor.

public interface Bufer {
 public void establecer( int valor ); // colocar valor en Bufer
 public int obtener(); // devolver valor de Bufer
}