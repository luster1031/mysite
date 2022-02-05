package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class commenAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int g_no = Integer.parseInt(request.getParameter("g_no"));
		int o_no = Integer.parseInt(request.getParameter("o_no"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		BoardVo vo = new BoardVo();
		vo.setGroupNo(g_no);
		vo.setOrderNo(o_no);
		vo.setDepth(depth);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserName(authUser.getName());
		vo.setUserNo(authUser.getNo());
		
		new BoardDao().comment(vo);
		System.out.println(vo.toString());
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}
