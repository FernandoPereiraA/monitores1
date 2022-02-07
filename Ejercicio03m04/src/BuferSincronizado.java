// BuferSincronizado sincroniza un solo entero compartido.
public class BuferSincronizado implements Bufer
{
 private int bufer = -1; 
 private boolean BuferLleno = false; 
 public synchronized void establecer( int valor )
 {
 // obtener nombre del subproceso que llam� a este m�todo, para mostrarlo en pantalla
 String nombre = Thread.currentThread().getName();
 // mientras el entero tenga valor , colocar subproceso en estado de espera
 while ( BuferLleno == true ) {
 // mostrar informaci�n del subproceso y del b�fer, despu�s esperar
 try {
 System.err.println( nombre + " trata de escribir." );
 System.err.println( "Bufer lleno. " + nombre + " espera." );
 wait();
 }
 // si se interrumpi� el subproceso en espera imprimir el rastreo de la pila
 catch ( InterruptedException excepcion ) {
 excepcion.printStackTrace();
 }
 } // fin de instrucci�n while
 bufer = valor; 
 
 BuferLleno=true;
 System.err.println( nombre + " escribe " + bufer );
 notify(); // indicar al subproceso en espera que entre al estado listo
 } 
 
 public synchronized int obtener()
 {
 // obtener nombre del subproceso que llam� a este m�todo, para mostrarlo en pantalla
 String nombre = Thread.currentThread().getName();
 // mientras no haya datos que leer, colocar subproceso en estado de espera
 while ( BuferLleno == false ) {
 // mostrar informaci�n del subproceso y del b�fer, despu�s esperar
	 try {
		 System.err.println( nombre + " trata de leer." );
		 System.err.println( "Bufer vacio. " + nombre + " espera." );
		 wait();
		 }
		 // si se interrumpi� el subproceso en espera, imprimir el rastreo de la pila
		 catch ( InterruptedException excepcion ) {
		 excepcion.printStackTrace();
		 }
		 } 
		 BuferLleno=false;
		 System.err.println( nombre + " lee " + bufer );
		 notify(); // indicar al subproceso en espera que est� listo para ejecutarse
		 return bufer;
		 } // fin del m�todo obtener; libera bloqueo en BuferSincronizado

		} // fin de la clase BuferSincronizado