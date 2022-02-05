package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class viewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		boolean check =true;
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (no.toString().equals(cookie.getName())) {
					check =false;
					break;
				}
			}
		}
		
		if(check) {
			new BoardDao().setHit(no);
		}

		// 쿠키 쓰기(굽기)
		Cookie cookie = new Cookie(no.toString(), request.getParameter("no"));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24 * 60 * 60); // 1 day
		System.out.println(cookie.toString());

		response.addCookie(cookie);
		
		BoardVo vo = new BoardDao().view(no);
		request.setAttribute("vo", vo);
		MvcUtil.forward("board/view", request, response);
	}

}
