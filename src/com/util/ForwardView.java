package com.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ForwardView {
	
	private HttpServletRequest req;
	private HttpServletResponse res;
	
	public ForwardView(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	public void forward(String path) {
		try {
			RequestDispatcher view = req.getRequestDispatcher(path);
			view.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
