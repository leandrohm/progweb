package org.usp.stockmarket.config;

public interface Default {
	
	//User servlet
	public int LOGIN = 0;	
	public int LOGOUT = 1;
	public int REGISTER = 2;

	//WebQuoteServlet
	public int GETQUOTE = 0;

	//TransactionServlet
	public int LOAN = 0;
	public int BUY = 1;
	public int SALE = 2;
	public int SALDO = 3;
	public int LIST_LOAN = 3;
	public int LIST_QUOTE = 4;

	//BankServlet
	public int REGISTER_BANK = 0;
}
