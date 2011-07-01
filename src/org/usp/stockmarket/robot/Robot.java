package org.usp.stockmarket.robot;

import java.util.regex.*;
import java.net.*;
import java.io.*; 

public class Robot {

	public static String dqParser = "http://download.finance.yahoo.com/d/quotes.csv?([^\"]*).csv";
	public static String hpParser = "http://ichart.finance.yahoo.com/table.csv?([^\"]*).csv";

	public static String getSource(String path) throws Exception {
		String buffer = "";
		URL url = new URL(path);
		URLConnection uconn = url.openConnection();
		InputStream is = uconn.getInputStream();

		BufferedReader br = new BufferedReader(
					new InputStreamReader(
						is
					)
				);
		String str = null;
		while ((str = br.readLine()) != null) {
			buffer += str;
		}

		return buffer;
	}

	public static String getLink(String path, String parser) throws Exception {
			String page = Robot.getSource(path);
			Pattern pt = Pattern.compile(parser);
			Matcher m = pt.matcher(page);
				String out = null;
				if(m.find()){
					out = m.group();
				}
				return out;
	}


	// Teste de unidade
//	public static void main(String args[]) throws Exception {
//		Robot.getLink("http://finance.yahoo.com/q/hp?s=PETR4.SA", hpParser);
//	}
}
