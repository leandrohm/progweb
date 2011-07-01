package org.usp.stockmarket.core;

import java.util.regex.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;
import org.usp.stockmarket.utils.*;

@Entity
@Table(name="tb_user")
public class User implements Serializable {
	@Id
	@Column(name="email", unique=true, nullable=false, length=80)
	private String email;
	@Column(name="name", nullable=false, length=80)
	private String name;
	@Column(name="passwd", nullable=false)
	private String passwd;
	@Column(name="photo") //nullable=false)
	private Byte[] photo;
	@Column(name="money", nullable=false)
	private Double money;
	@Column(name="loantopay", nullable=false)
	private Double loanToPay;							//valor de emprestimo que o usuario tem para pagar

	public User(){}

	public void setName(String name){ this.name = name; }
	public void setEmail(String email) throws Exception {
		String expr = "[a-z0-9]{3,20}@[a-z]{2,10}\\..[a-z]{2,10}";  
		Pattern pt = Pattern.compile(expr);
		Matcher m = pt.matcher(email);
		if (m.find()) {
			this.email = email;
		} else {
			throw new Exception("Invalid Email!");
		}	 
	}
	public void setPasswd(String passwd) throws Exception { 
		if (passwd.length() >= 6 && passwd.length() < 17) {
			this.passwd = MD5.encode(passwd);
		} else {
			throw new Exception("Password out of bounds!");
		}	
	}
	public void setPhoto(Byte[] photo){ this.photo = photo; }
	public void setMoney(Double money){ this.money = money; }
	public void setLoanToPay(Double loan){ this.loanToPay = loan; }

	public String getName(){ return this.name; }
	public String getEmail(){ return this.email; }
	public String getPasswd(){ return this.passwd; }
	public Byte[] getPhoto(){ return this.photo; }
	public Double getMoney(){ return this.money; }
	public Double getLoanToPay(){ return this.loanToPay; }


	@OneToMany(mappedBy="user")
	private Set<Investment> investment = new HashSet<Investment>();
	public Set<Investment> getInvestment() { return this.investment; }
	public void setInvestment(Set<Investment> investment) { this.investment = investment; }

	@OneToMany(mappedBy="user")
	private Set<Loan> loan = new HashSet<Loan>();
	public Set<Loan> getLoan() { return this.loan; }
	public void setLoan(Set<Loan> loan) { this.loan = loan; }

}
