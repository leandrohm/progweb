package org.usp.stockmarket.core;

import java.util.regex.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;
import org.usp.stockmarket.utils.*;

@Entity
@Table(name="tb_quote")
public class Quote implements Serializable {
	@Id
	@Column(name="qid", unique=true, nullable=false)
	@SequenceGenerator(name="seq_qid", sequenceName="seq_qid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_qid")
	private Integer qid;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="close", nullable=false)
	private Double close;

	public Quote(){}

	public void setQid(Integer qid){ this.qid = qid; }
	public void setName(String name){ this.name = name; }
	public void setDate(Date date){ this.date = date; }
	public void setClose(Double close){ this.close = close; }

	public Integer getQid(){ return this.qid; }
	public String getName(){ return this.name; }
	public Date getDate(){ return this.date; }
	public Double getClose(){ return this.close; }

}
