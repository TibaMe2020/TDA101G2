package com.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;


@WebFilter(
    urlPatterns={"/front-end/member/updateInfo.jsp","/front-end/notification/notiOverview.jsp",
    		"/front-end/notification/notification.jsp","/front-end/member/submitApplication.jsp", "/front-end/notification/chat.jsp"}, 
    dispatcherTypes={
        DispatcherType.FORWARD, DispatcherType.INCLUDE, 
        DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC
    },
    servletNames= {}
)
public class Authentication implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config){
		this.config = config;
	}
	
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
//		res.setHeader("Cache-Control", "no-store");
//		res.setHeader("Pragma", "no-cache");
//		res.setDateHeader("Expires", 0);
		
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		
		if(memberVO == null) {
			
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
			return; 
		} else {
			chain.doFilter(request, response);
		}	
	}
}
