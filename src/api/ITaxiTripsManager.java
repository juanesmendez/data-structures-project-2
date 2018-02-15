package api;


import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.vo.Compania;
import model.vo.CompaniaServicios;
import model.vo.CompaniaTaxi;
import model.vo.InfoTaxiRango;
import model.vo.RangoDistancia;
import model.vo.RangoFechaHora;
import model.vo.Servicio;
import model.vo.ServiciosValorPagado;
import model.vo.Taxi;
import model.vo.ZonaServicios;

/**
 * API para la clase de logica principal  
 */
public interface ITaxiTripsManager 
{
	//1A
	/**
	 * Generar	una	Cola con	 todos	los	servicios	de	 taxi	que	se	prestaron	en	un	periodo	de	
	 * tiempo	 dado	 por	 una	 fecha/hora	 inicial	 y	 una	 fecha/hora	 final	 de	 consulta.	 
	 * El	 inicio	 y	terminacion	del	servicio	debe	estar	incluido dentro	del	periodo	de	consulta.	
	 * Los	servicios deben	mostrarse	en	orden	cronologico	de	su	fecha/hora	inicial.	
	 */
	public IQueue<Servicio> darServiciosEnPeriodo(RangoFechaHora rango);

	//2A
	/**
	 * Buscar el taxi de una compa��a dada que m�s servicios inici� en un periodo de tiempo
	 * dado por una fecha/hora inicial y una fecha/hora final de consulta.
	 */
	public Taxi darTaxiConMasServiciosEnCompaniaYRango(RangoFechaHora rango, String company);

	//3A
	/**
	 * Buscar la informaci�n completa de un taxi, a partir de su identificador, en un periodo
	 * de tiempo dado por una fecha/hora inicial y una fecha/hora final de consulta. Incluye el
	 * nombre de su compa��a y los valores totales de plata ganada, de servicios prestados, de
	 * distancia recorrida y de tiempo total de servicios.
	 */
	public InfoTaxiRango darInformacionTaxiEnRango(String id, RangoFechaHora rango);

	//4A
	/**
	 * Retornar una lista de rangos de distancia recorrida, en la que se encuentran todos los
	 * servicios de taxis servidos por las compa��as, en una fecha dada y en un rango de horas
	 * especificada. La informaci�n debe estar ordenada por la distancia recorrida, as� la primera 
	 * posici�n de la lista tiene a su vez una lista con todos los servicios cuya distancia recorrida
	 * esta entre [0 y 1) milla. En la segunda posici�n, los recorridos entre [1 y 2) millas, y as�
	 * sucesivamente.
	 */
	public LinkedList<RangoDistancia>darListaRangosDistancia(String fecha, String horaInicial, String horaFinal);

	//1B
	/**
	 * Mostrar	la	informaci�n	de	las	compa��as	de	taxi	consistente	en:	El	total	de	compa��as	
	 * que	tienen	al	menos	un	taxi	inscrito	y	el	total	de	taxis	que	prestan	servicio	para	al	menos	
	 * una	 compa��a.	 Adicionalmente,	 generar	 la	 lista	 alfab�tica	 de	 compa��as	 a	 las	 cuales	
	 * aparecen	 inscritos	 los	 servicios	 de	 taxi	 de	 la	 fuente	 de	 datos	 de	 consulta.	 Por	 cada	
	 * compa��a	debe	informarse	su	nombre	y	el	n�mero	de	taxis	que	tiene	registrados
	 */
	public LinkedList<Compania> darCompaniasTaxisInscritos();

	//2B
	/**
	 * Buscar el taxi de una compa��a dada que mayor facturaci�n ha generado en un
	 * periodo de tiempo dado por una fecha/hora inicial y una fecha/hora final de consulta.
	 * @param rango
	 * @param nomCompania
	 * @return VOTaxi
	 */
	public Taxi darTaxiMayorFacturacion(RangoFechaHora rango, String nomCompania);

	//3B
	/**
	 * Buscar la informaci�n completa de una zona de la ciudad en un periodo de tiempo
	 * dado por una fecha/hora inicial y una fecha/hora final de consulta. El n�mero total de
	 * servicios que se recogieron en la zona de consulta y terminaron en otra zona y el valor
	 * total pagado por los usuarios; el n�mero total de servicios que se recogieron en otra zona
	 * y terminaron en la zona de consulta y el valor total pagado por los usuarios, y el total de
	 * servicios que iniciaron y terminaron en la misma zona de consulta y el valor total pagado
	 * por los usuarios.
	 * @param rango
	 * @param idZona
	 * @return VOServiciosValorPagado[]
	 */
	public ServiciosValorPagado[] darServiciosZonaValorTotal(RangoFechaHora rango, String idZona);

	//4B

	/**
	 * Retornar una lista con todas las zonas de la ciudad (ordenadas por su identificador).
	 * Cada zona debe tener el total de servicios iniciados en dicha zona en un rango de fechas.
	 * Por ejemplo, la primera posici�n de la lista tiene todos los servicios de la primer zona, en
	 * dicha posici�n, se tiene una lista de fechas (ordenadas cronol�gicamente) con el total de
	 * servicios asociados a dicha fecha.
	 */
	public 	LinkedList<ZonaServicios> darZonasServicios (RangoFechaHora rango);

	//1C
	/**
	 * Dada la direcci�n del json que se desea cargar, se generan VO's, estructuras y datos necesarias
	 * @param direccionJson, ubicaci�n del json a cargar
	 * @return true si se lo logr� cargar, false de lo contrario
	 */
	public boolean cargarSistema(String direccionJson);

	//2C
	/**
	 * Identificar el top X de compa��as que m�s servicios iniciaron en un periodo de tiempo
	 * dado por una fecha/hora inicial y una fecha/hora final. El valor X es un dato de consulta. El
	 * resultado debe mostrar el Top X de compa��as ordenadas por el n�mero de servicios de
	 * mayor a menor. Por cada compa��a debe informarse su nombre y su n�mero de servicios
	 * de respuesta.
	 */
	public LinkedList<CompaniaServicios> companiasMasServicios(RangoFechaHora rango, int n);

	//3C
	/**
	 * Buscar el taxi m�s rentable de cada compa��a. El taxi m�s rentable de una compa��a
	 * es aquel cuya relaci�n de plata ganada y distancia recorrida en los servicios prestados es mayor.
	 */
	public LinkedList<CompaniaTaxi> taxisMasRentables();

	//4C
	/**
	 * Dada la gran cantidad de datos que requiere el proyecto, se desea poder compactar
	 * informaci�n asociada a un taxi particular. Para ello usted debe guardar en una pila todos
	 * los servicios generados por el taxi en orden cronol�gico, entre una hora inicial y una hora
	 * final, en una fecha determinada.
	 */
	public IStack <Servicio> darServicioResumen(String taxiId, String horaInicial, String horaFinal, String fecha);








}
