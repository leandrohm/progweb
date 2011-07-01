package org.usp.stockmarket.core;

import java.util.regex.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name="tb_bank")
public class Bank implements Serializable {
	@Id
	@Column(name="bid", unique=true, nullable=false)
	@SequenceGenerator(name="seq_bid", sequenceName="seq_bid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_bid")
	private Integer bid;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="juros", nullable=false)
	private Double juros;

	@Column(name="tradVol", nullable=false)
	private Double tradVol;     //Volume Negociado

	@Column(name="expVol", nullable=false)
	private Double expVol;     //Volume experado para negociar

	@Column(name="alfa", nullable=false)
	private Double alpha;     //incrementaremos alfa em 0.02 a cada pedido de emprestimo  a cada pedido de 	

	public Bank(){}

	public void setBid(Integer bid){ this.bid = bid; }
	public void setName(String name){ this.name = name; }
	public void setJuros(Double juros) { this.juros = juros; }
	public void setTradVol(Double tradVol) { this.tradVol = tradVol; }
	public void setExpVol(Double expVol) { this.expVol = expVol; }
	public void setAlpha(Double alpha) { this.alpha = alpha; }

	public Integer getBid(){ return this.bid; }
	public String getName(){ return this.name; }
	public Double getJuros(Double juros){return this.juros; }
	public Double getTradVol(Double tradVol){return this.tradVol; }
	public Double getExpVol(Double expVol){return this.expVol; }
	public Double getalpha(Double alpha){return this.alpha; }

	public Double calcJuros(){
		 juros = juros*(1-alpha) + (tradVol/expVol)*alpha;
		 if(alpha <= 1) alpha += 0.09;    //incrementa o valor de alpha para aumentar o valor dos juros posteriormente
		 return juros;
	}

		
}
