package model.data_structures;

public interface IQueue <T> 
{
	/**
	 * Agregar un item al tope de la cola
	 */
	public void enqueue(T item);

	/**
	 * Elimina el elemento al tope de la cola
	 */
	public T dequeue();

	/**
	 * Indica si la cola esta vacia
	 */
	public boolean isEmpty();

	/**
	 * Numeros de elementos en la lista
	 */
	public int size();
}