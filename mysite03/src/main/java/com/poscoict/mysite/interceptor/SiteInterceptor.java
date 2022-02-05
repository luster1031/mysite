package com.poscoict.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.repository.UserRepository;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;
import com.poscoict.mysite.vo.UserVo;

public class SiteInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private SiteRepository siteRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("================prehandle -- site===========");
		
		SiteVo sitevo = (SiteVo) request.getServletContext().getAttribute("site");
		if (sitevo == null) {
			sitevo = siteRepository.find();
			request.getServletContext().setAttribute("site", sitevo);
		}
	
		return true;
	}

}
