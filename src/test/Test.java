package test;

import model.vo.Company;
import model.vo.MyDateTime;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		Integer e = 3;
		System.out.println(e.hashCode());
		
		System.out.println((e.hashCode())%72);
		String s1= "hola";
		System.out.println(s1.hashCode());
		String s2 = "hola";
		System.out.println(s2.hashCode());
		
		System.out.println();
		Company c1 = new Company("Tappsi");
		System.out.println(c1.hashCode());
		Company c2 = new Company("Tappsi");
		System.out.println(c2.hashCode());
		
		if(c1==c2) {
			System.out.println("TRUE");
		}
		
		if(c1.compareTo(c2) == 0) {
			System.out.println("TRUE");
		}*/
		
		MyDateTime datetime = new MyDateTime(2015, 8, 10, 10, 0, 0, 0);
		
		MyDateTime datetime2 = new MyDateTime(2015, 8, 10, 10, 0, 0, 0);
		
		System.out.println(datetime.compareTo(datetime2));
		System.out.println(datetime.toString());
	}

}
