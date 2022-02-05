package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscoict.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		System.out.println("resolveArgument");
		if(!supportsParameter(parameter)) {
			System.out.println("!supportsParameter(parameter)");
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			System.out.println("[resovler]세션 없다");
			return null;
		}
		
		System.out.println("세션 있다.");
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser 가 안 붙어 있음
		if(authUser == null) {
			System.out.println("@AuthUser 가 안 붙어 있음");
			return false;
		}
		
		//파라미터 타입이 UserVo가 아님
		if(parameter.getParameterType().equals(UserVo.class) == false) {
			System.out.println("파라미터 타입이 UserVo가 아님");
			return false;
		}
		
		return true;
	}	
}