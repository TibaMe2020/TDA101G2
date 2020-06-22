package com.product_score.model;

import java.sql.Date;

public class Score_VO implements java.io.Serializable{

	private String product_score_id;
	private String product_id;
	private String member_id;
	private Integer score;
	private Date create_time;
	@Override
	public String toString() {
		return "Score_VO [Product_score_id=" + product_score_id + ", Product_id=" + product_id + ", Member_id="
				+ member_id + ", score=" + score + ", Create_time=" + create_time + "]";
	}
	public String getProduct_score_id() {
		return product_score_id;
	}
	public void setProduct_score_id(String product_score_id) {
		this.product_score_id = product_score_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
	
	
	
	
	
}
