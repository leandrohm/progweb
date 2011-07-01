package org.usp.stockmarket.core;

import java.util.regex.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;
import org.usp.stockmarket.utils.*;

@Entity
@Table(name="tb_loan")
public class Loan implements Serializable {
	@Id
	@Column(name="lid", unique=true, nullable=false)
	@SequenceGenerator(name="seq_lid", sequenceName="seq_lid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_lid")
	private Integer lid;

	@Column(name="email", nullable=false)
	private String email;

	@Column(name="bank", nullable=false)
	private Integer bank;

	@Column(name="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="val", nullable=false)
	private Double val;  //valor do emprestimo

	@Column(name="juros", nullable=false)
	private Double juros;	//juros associado ao emprestimo

	@Column(name="payed", nullable=false)
	private Boolean payed;	//indica se o emprestimo foi pago ou nao
	
	public Loan(){}

	public void setLid(Integer lid){ this.lid = lid; }
	public void setEmail(String email){ this.email = email; }
	public void setBank(Integer bank){ this.bank = bank; }
	public void setDate(Date date){ this.date = date; }
	public void setVal(Double val){ this.val = val; }
	public void setJuros(Double juros){ this.juros = juros; }
	public void isPayed(Boolean payed){ this.payed = payed; }

	public Integer getLid(){ return this.lid; }
	public String getEmail(){ return this.email; }
	public Integer getBank(){ return this.bank; }
	public Date getDate(){ return this.date; }
	public Double getVal(){ return this.val; }
	public Double getJuros(){ return this.juros; }
	public Boolean getPayed(){ return this.payed; }

	@ManyToOne
	@JoinColumn (name="email", nullable = false, updatable = false, insertable = false)
	private User user;
	public User getUser() { return this.user; }
	public void setUser(User user) { this.user = user; }

}
