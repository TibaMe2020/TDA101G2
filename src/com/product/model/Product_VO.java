package com.product.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import com.product_score.model.Score_VO;
import com.product_version.model.Version_VO;

public class Product_VO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String product_id;
	private String member_id;
	private String name;
	private String product_class;
	private byte[] description;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private byte[] image4;
	private Integer product_state;
	private Date create_time;
	private Integer price;
	private Double score;
	private List<Version_VO> versions;
	
	private Integer memberScore; 
	
	public Integer getMemberScore() {
		return memberScore;
	}

	public void setMemberScore(Integer memberScore) {
		this.memberScore = memberScore;
	}

	private List<Score_VO> scores;
	
	
	public List<Score_VO> getScores() {
		return scores;
	}

	public void setScores(List<Score_VO> scores) {
		this.scores = scores;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<Version_VO> getVersions() {
		return versions;
	}

	public void setVersions(List<Version_VO> versions) {
		this.versions = versions;
	}

	

	@Override
	public String toString() {
		return "Product_VO [product_id=" + product_id + ", member_id=" + member_id + ", name=" + name
				+ ", product_class=" + product_class + ", description=" + Arrays.toString(description) + ", image1="
				+ Arrays.toString(image1) + ", image2=" + Arrays.toString(image2) + ", image3="
				+ Arrays.toString(image3) + ", image4=" + Arrays.toString(image4) + ", product_state=" + product_state
				+ ", create_time=" + create_time + ", price=" + price + ", score=" + score + ", versions=" + versions
				+ "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct_class() {
		return product_class;
	}

	public void setProduct_class(String product_class) {
		this.product_class = product_class;
	}

	public byte[] getDescription() {
		return description;
	}

	public void setDescription(byte[] description) {
		this.description = description;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public byte[] getImage4() {
		return image4;
	}

	public void setImage4(byte[] image4) {
		this.image4 = image4;
	}

	public Integer getProduct_state() {
		return product_state;
	}

	public void setProduct_state(Integer product_state) {
		this.product_state = product_state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}
