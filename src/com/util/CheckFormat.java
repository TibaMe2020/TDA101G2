package com.util;

import java.util.Map;
import java.util.function.Predicate;

public class CheckFormat {
	
	private String emailReg = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
	private String phoneReg = "^09[0-9]{8}$";
	private String nameReg = "^[(\u4e00-\u9fa5)\\s(a-zA-Z)]{1,}$";
	private String addressReg = "^[(\u4e00-\u9fa5) (0-9a-zA-Z).,]+";
	private String idNumReg = "^[A-Za-z]{1}[1-2]{1}[0-9]{8}$";
	private String bankAccReg = "^[0-9]{13,20}$";
	
	public Predicate<Map<String, String>> isErrorMsgsEmpty = p -> p.isEmpty();
	
	public Predicate<String> isEmptyOrNull = p -> "".equals(p);
	
	public Predicate<String> checkEmailFormat = p -> p.matches(emailReg);

	public Predicate<String> checkEmailLength = p -> p.getBytes().length > 50;
	
	public Predicate<String> checkPhoneFormat = p -> p.matches(phoneReg);
	
	public Predicate<String> checkNameFormat = p -> p.matches(nameReg);
	
	public Predicate<String> checkNameLength = p -> p.getBytes().length > 20;
	
	public Predicate<String> checkBlogNameLength = p -> p.getBytes().length > 50;
	
	public Predicate<String> checkAddressFormat = p -> p.matches(addressReg);

	public Predicate<String> checkIdNumFormat = p -> p.matches(idNumReg);
	
	public Predicate<String> checkBankAccFormat = p -> p.matches(bankAccReg);
}
