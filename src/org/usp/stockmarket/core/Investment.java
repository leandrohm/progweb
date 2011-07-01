package org.usp.stockmarket.core;

import java.util.regex.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name="tb_investment")
public class Investment implements Serializable {
	@Id
	@Column(name="inid", unique=true, nullable=false)
	@SequenceGenerator(name="seq_inid", sequenceName="seq_inid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_inid")
	private Integer inid;

	@Column(name="email", nullable=false)
	private String email;

	@Column(name="quote", nullable=false)
	private String quote;

	@Column(name="val", nullable=false)
	private Double val;  //valor da acao

	@Column(name="quantity", nullable=false)
	private Integer quantity;  //quantidade de acoes compradas

	@Column(name="buy", nullable=false)
	private Boolean buy;  //true - compra, false - venda

	@Column(name="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;

	public Investment(){}

	public void setInId(Integer inid){ this.inid = inid; }
	public void setEmail(String email){ this.email = email; }
	public void setQuote(String quote){ this.quote = quote; }
	public void setVal(Double val){ this.val = val; }
	public void setQuantity(Integer quantity){ this.quantity = quantity; }
	public void isBuy(Boolean buy){ this.buy = buy; }
	public void setDate(Date date){ this.date = date; }

	public Integer getInId(){ return this.inid; }
	public String getEmail(){ return this.email; }
	public String getQuote(){ return this.quote; }
	public Double getVal(){ return this.val; }
	public Integer getQuantity(){ return this.quantity; }
	public Boolean getBuy(){ return this.buy; }
	public Date getDate(){ return this.date; }

	@ManyToOne
	@JoinColumn (name="email", nullable = false, updatable = false, insertable = false)
	private User user;
	public User getUser() { return this.user; }
	public void setUser(User user) { this.user = user; }

}
