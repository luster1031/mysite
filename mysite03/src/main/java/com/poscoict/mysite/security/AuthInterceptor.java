package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 정보 - handler
		// handler 종류 확인 - 이미지 파일,, 등등
		if (handler instanceof HandlerMethod == false) {
			System.out.println("1번");
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Handler Method @Auth가 없다면 Type에 있는 지 확인(과제)
		if (auth == null) {
			// 클래스로 올라가서 찾아봐야 한다.
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
//			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 5. type과 method에 @Auth가 적용이 안 되어 있는 경우
		// 인증이 필요없는 것이라면
		if (auth == null) { 
			return true;
		}

//	--------------인증이 필요함 ---------------------------
		
		//	6. @Auth가 적용이 되어 있기 때문에, 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 세션이 있는 것
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		//	7. 권한(Authorization) 체크를 위해서 @Auth의 role가져오기 ("USER", "ADMIN")
		String role = authUser.getRole();

		//	8. @Auth의 role이 "USER"인 경우, authUser의 role은 상관이 없다. 
		// authInterceptor role처리
		if ("USER".equals(role)) {
			return true;
		}

		//	9. @Auth의 roler이 "ADMIN"인 경우, authUser은 "ADMIN"이어야 한다. 
		if ("ADMIN".equals(authUser.getRole())==false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		// 	10. 옳은 관리자
		//	@Auth의 role: "ADMIN"
		//	authUser의 role : "ADMIN"
		return true;
	}

}
