package org.usp.stockmarket.config;

import java.util.*;

/**
* Define os principais empresas a terem suas
* ações analisadas
*/
public interface Symbol {

	public String PETR4 = "petr4.sa";	//Petrobras - PN	
	public String OGXP3 = "ogxp3.sa";	//OGX Petroleo-ON NM
	public String VALE5 = "vale5.sa";	//Vale -PNA N1
	public String USIM5 = "usim5.sa";	//Usiminas -PNA N1
	public String GGBR4 = "ggbr4.sa";	//Gerdau -PNA N1
	public String CSNA3 = "csna3.sa";	//Siderurgica Nacional
	public String PCAR4 = "pcar4.sa";	//Pao de Acucar
	public String ITSA4 = "itsa4.sa";	//Itausa
	public String BBDC4 = "bbdc4.sa";	//Bradesco
	public String VALE3 = "vale3.sa";	//Vale -ON N1
	public Vector<String> QUOTES = new Vector<String>();
/*	QUOTES.add(PETR4);
	QUOTES.add(OGXP3);
	QUOTES.add(VALE5);
	QUOTES.add(USIM5);
	QUOTES.add(GGBR4);
	QUOTES.add(CSNA3);
	QUOTES.add(PCAR4);
	QUOTES.add(ITSA4);
	QUOTES.add(BBDC4);
	QUOTES.add(VALE3);
*/
	public String QUOTEDAY = "http://br.finance.yahoo.com/q?s=";
	public String HISTPRICES = "http://br.finance.yahoo.com/q/hp?s=";
}
