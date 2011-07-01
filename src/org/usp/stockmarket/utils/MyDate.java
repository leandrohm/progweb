package org.usp.stockmarket.utils;


import java.util.*; 
import java.text.*; 

public class MyDate { 

	public static Date format(String date) throws Exception {   
		Date newDate = null;  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		newDate = dateFormat.parse(date);  
		return newDate;  
	}

	/*Retorna a data atual do sistema */
	public static Date now() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();
		return MyDate.format(dateFormat.format(date));  
	}

	/*TESTE DE UNIDADE*/
	public static void main(String args[]) throws Exception {
		Date date = MyDate.now();
		System.out.println(date);
		
	}
}
