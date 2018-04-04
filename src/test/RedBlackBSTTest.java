package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.IRedBlackBST;
import model.data_structures.RedBlackBST;

public class RedBlackBSTTest {

	@Test
	public void testSize() {
		IRedBlackBST<Integer,String> arbolBalanceado = new RedBlackBST<Integer,String>();
		int result = arbolBalanceado.size();
		assertEquals(0,result);
		arbolBalanceado.put(1, "B");
		arbolBalanceado.put(6, "I");
		result = arbolBalanceado.size();
		assertEquals(2, result);
	}

	@Test
	public void testIsEmpty() {
		IRedBlackBST<Integer,String> arbolBalanceado = new RedBlackBST<Integer,String>();
		boolean result = arbolBalanceado.isEmpty();
		assertEquals(true,result);
		arbolBalanceado.put(1, "B");
		arbolBalanceado.put(6, "I");
		result = arbolBalanceado.isEmpty();
		assertEquals(false, result);
	}

	@Test
	public void testGet() {
		IRedBlackBST<Integer,String> arbolBalanceado = new RedBlackBST<Integer,String>();
		arbolBalanceado.put(1, "B");
		arbolBalanceado.put(6, "I");
		arbolBalanceado.put(3, "U");
		String result = arbolBalanceado.get(6);
		assertEquals("I",result);
		result = arbolBalanceado.get(1);
		assertEquals("B",result);
	}

	@Test
	public void testContains() {
		IRedBlackBST<Integer,String> arbolBalanceado = new RedBlackBST<Integer,String>();
		arbolBalanceado.put(1, "B");
		arbolBalanceado.put(6, "I");
		arbolBalanceado.put(3, "U");
		boolean result = arbolBalanceado.contains(6);
		assertEquals(true,result);
		result = arbolBalanceado.contains(10);
		assertEquals(false,result);
		
	}

	@Test
	public void testPut() {
		IRedBlackBST<Integer,String> arbolBalanceado = new RedBlackBST<Integer,String>();
		arbolBalanceado.put(1, "B");
		arbolBalanceado.put(2, "I");
		arbolBalanceado.put(3, "U");
		Iterable<Integer> it = arbolBalanceado.keys();
		boolean result = true;
		for(Integer i:it) {
			if((i<1) || (i>3)) {
				result = false;
			}
			
		}
		assertEquals(true,result);
	}

	@Test
	public void testDelete() {
		IRedBlackBST<Integer,String> arbolBalanceado = new RedBlackBST<Integer,String>();
		arbolBalanceado.put(1, "B");
		arbolBalanceado.put(6, "I");
		arbolBalanceado.put(3, "U");
		boolean result = arbolBalanceado.contains(6);
		assertEquals(true,result);
		arbolBalanceado.delete(6);
		result = arbolBalanceado.contains(6);
		assertEquals(false,result);
	}

}
