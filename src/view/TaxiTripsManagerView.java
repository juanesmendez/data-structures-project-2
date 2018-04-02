package view;

import java.util.Scanner;

import controller.Controller;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;

/**
 * view del programa
 */
public class TaxiTripsManagerView 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{
			//imprime menu
			printMenu();

			//opcion req
			int option = sc.nextInt();

			switch(option)
			{
			//1C cargar informacion dada
			case 1:

				//imprime menu cargar
				printMenuCargar();

				//opcion cargar
				int optionCargar = sc.nextInt();

				//directorio json
				String linkJson = "";
				switch (optionCargar)
				{
				//direccion json pequeno
				case 1:

					linkJson = TaxiTripsManager.DIRECCION_SMALL_JSON;
					break;

					//direccion json mediano
				case 2:

					linkJson = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
					break;

					//direccion json grande
				case 3:

					linkJson = TaxiTripsManager.DIRECCION_LARGE_JSON;
					break;
				}

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();

				//Cargar data
				Controller.cargarSistema(linkJson);

				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");

				break;

				//1A	
			case 2:

		
				break;
				
			case 3: //2A
				

				break;
				
			case 4: //3A
				

				break;
				
			case 5: //4A
				

				break;
				
			case 6: //1B
				
				break;
				
			case 7: //2B

				break;
				
			case 8: //3B
				break;
				
			case 9: //4B

				break;
				
			case 10: //2C				

				break;
				
			case 11: //3C			

				break;
				
			case 12: //4C
				break;
 
			case 13: //salir
				fin=true;
				sc.close();
				break;

			}
		}
	}
	/**
	 * Menu 
	 */
	private static void printMenu() //
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 1----------------------");
		System.out.println("Cargar data (1C):");
		System.out.println("1. Cargar toda la informacion dada una fuente de datos (small,medium, large).");

		System.out.println("\nParte A:\n");
		System.out.println("2.Obtenga una lista con todos los servicios de taxi ordenados cronologicamente por su fecha/hora inicial, \n"
				+ " que se prestaron en un periodo de tiempo dado por una fecha/hora inicial y una fecha/hora final de consulta. (1A) ");
		System.out.println("3.Dada una compania y un rango de fechas y horas, obtenga  el taxi que mas servicios inicio en dicho rango. (2A)");
		System.out.println("4.Consulte la compania, dinero total obtenido, servicios prestados, distancia recorrida y tiempo total de servicios de un taxi dados su id y un rango de fechas y horas. (3A)");
		System.out.println("5.Dada una fecha y un rango de horas, obtenga una lista de rangos de distancia en donde cada pocision contiene los servicios de taxis cuya distancia recorrida pertence al rango de distancia. (4A)\n");

		System.out.println("Parte B: \n");
		System.out.println("6. Obtenga el numero de companias que tienen al menos un taxi inscrito y el numero total de taxis que trabajan para al menos una compania. \n"
				+ "Luego, genera una lista (en orden alfabetico) de las companias a las que estan inscritos los servicios de taxi. Para cada una, indique su nombre y el numero de taxis que tiene registrados. (1B)");
		System.out.println("7. Dada una compania, una fecha/hora inicial y una fecha/hora final, buscar el taxi de la compania que mayor facturacion gener� en el tiempo dado. (2B)");
		System.out.println("8. Dada una zona de la ciudad, una fecha/hora inicial y una fecha/hora final, dar la siguiente informacion: (3B) \n"
				+ "   -Numero de servicios que iniciaron en la zona dada y terminaron en otra zona, junto con el valor total pagado por los usuarios. \n"
				+ "   -Numero de servicios que iniciaron en otra zona y terminaron en la zona dada, junto con el valor total pagado por los usuarios. \n"
				+ "   -Numero de servicios que iniciaron y terminaron en la zona dada, junto con el valor total pagado por los usuarios.");
		System.out.println("9. Dado un rango de fechas, obtener la lista de todas las zonas, ordenadas por su identificador. Para cada zona, dar la lista de fechas dentro del rango (ordenadas cronol�gicamente) \n "
				+ "y para cada fecha, dar el numero de servicios que se realizaron en dicha fecha. (4B)");


		System.out.println("\nParte C: \n");
		System.out.println("10. Dado un numero n, una fecha/hora inicial y una fecha/hora final, mostrar las n companias que mas servicios iniciaron dentro del rango. La lista debe estar ordenada descendentemente \n por el numero de servicios. Para cada compania, dar el nombre y el numero de servicios (2C)");
		System.out.println("11. Para cada compania, dar el taxi mas rentable. La rentabilidad es dada por la relacion entre el dinero ganado y la distancia recorrida. (3C)");
		System.out.println("12. Dado un taxi, dar el servicio resumen, resultado de haber comprimido su informacion segun el enunciado. (4C) ");
		System.out.println("13. Salir");
		System.out.println("Type the option number for the task, then press enter: (e.g., 1):");

	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea cargar?");
		System.out.println("-- 1. Small");
		System.out.println("-- 2. Medium");
		System.out.println("-- 3. Large");
		System.out.println("-- Type the option number for the task, then press enter: (e.g., 1)");
	}

}
