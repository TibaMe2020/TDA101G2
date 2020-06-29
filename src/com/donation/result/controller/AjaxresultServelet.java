package com.donation.result.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donation.donation_result.model.Donation_resultService;
import com.donation.donation_result.model.Donation_resultVO;
import com.donation.npo_info.model.Npo_infoService;
import com.google.gson.Gson;



@WebServlet("/Result/AjaxresultServelet")
public class AjaxresultServelet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String month = req.getParameter("month");
		PrintWriter out = res.getWriter();
		
		Donation_resultService resultSvc = new Donation_resultService();
//		System.out.println(action + month);

		if("getMonth".equals(action)) {
			System.out.println(month);
			Gson gson = new Gson();
			Integer result_month = Integer.valueOf(month);		
			Npo_infoService infoSvc = new Npo_infoService();
			List<Donation_resultVO> remonth = resultSvc.getMonth(result_month);
			for(Donation_resultVO rs:remonth) {
				String npoName = (infoSvc.getOneNpo_info(rs.getNpo_id())).getNpo_name();
				rs.setNpo_id(npoName);
			}
			out.print(gson.toJson(remonth));
			System.out.println("Gson:" + gson.toJson(resultSvc.getMonth(result_month)));
			List<Donation_resultVO> donation_resultVO = resultSvc.getMonth(result_month);
//			for(Donation_resultVO list : donation_resultVO) {
//				System.out.println(list +"");
//			}
//			System.out.println(donation_resultVO);

				}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}
}