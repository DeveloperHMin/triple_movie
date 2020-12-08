package com.triplemovie.pjt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.triplemovie.pjt.Const;
import com.triplemovie.pjt.user.model.UserVO;


public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		//바로통과
		if(uri.equals("/")) {
			return true;
		} else if(uriArr[1].equals("res")) {
			return true;
		}
		String[] checkKeywords = {"ajax","movieTicket","movieSheets"};
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		switch(uriArr[1]) {
			case "user":
				switch (uriArr[2]) {
				case "join": case "login":
					if(loginUser != null) {
						response.sendRedirect("/");
						return false;
					}
					
				}
				break;
			case "movie":
				for(String keyword: checkKeywords) {
					if(uriArr[2].contains(keyword)) {
						if(loginUser == null) {
							response.sendRedirect("/");
							return false;
						}
					}
				}
				break;
		}
		return true;
	}

}
