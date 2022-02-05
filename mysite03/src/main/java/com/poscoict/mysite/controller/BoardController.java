package com.poscoict.mysite.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String board(@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
			@RequestParam(value = "kwd2", required = true, defaultValue = "") String kwd2, Model model) {
		Map<String, Object> map = boardService.getContentsList(page, kwd, kwd2);
		model.addAttribute("m", map);
		return "board/list";
	}

	@Auth
	@RequestMapping("/writeform")
	public String writeform(@AuthUser UserVo  authUser) {
		System.out.println("[writeform: ] " + authUser.toString());
		return "board/write";
	}

	@Auth
	@RequestMapping("/deletepost")
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "") Long no
			, @AuthUser UserVo authUser) {
		
		boardService.deleteContents(no, authUser.getNo());
		
		return "redirect:/board";
	}

	@Auth
	@RequestMapping("/write")
	public String write(@AuthUser UserVo  authUser, BoardVo vo) {
		System.out.println("[write: ] " + authUser.toString());
		vo.setUserNo(authUser.getNo());
		boardService.addContents(vo);
		return "redirect:/board";
	}
	
	
	@RequestMapping("/view")
	public String viewform(@RequestParam(value = "no", required = true, defaultValue = "") Long no
			, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	@Auth
	@RequestMapping("/updateform")
	public String updateform(@RequestParam(value = "no", required = true, defaultValue = "") Long no,
			 @AuthUser UserVo authUser, Model model) {
		if(boardService.getTotalCount() != 0) {
			BoardVo vo = boardService.getContents(no, authUser.getNo());
			model.addAttribute("vo", vo);
		}
		return "board/modify";
	}
	@Auth
	@RequestMapping("/update")
	public String update(@RequestParam(value = "no", required = true, defaultValue = "") Long no, BoardVo vo,
			 @AuthUser UserVo authUser) {
		
		vo.setNo(no);
		boardService.updateContents(vo);
		return "redirect:/board/view?no=" + no;
	}
	@Auth
	@RequestMapping("/comment")
	public String commnet(BoardVo vo,  @AuthUser UserVo authUser,
			@RequestParam(value = "g_no", required = true, defaultValue = "") Integer g_no,
			@RequestParam(value = "o_no", required = true, defaultValue = "") Integer o_no,
			@RequestParam(value = "depth", required = true, defaultValue = "") Integer depth) {

		vo.setUserNo(authUser.getNo());
		vo.setDepth(depth);
		vo.setGroupNo(g_no);
		vo.setOrderNo(o_no);
		boardService.addContents(vo);
		return "board/write";
	}
	@Auth
	@RequestMapping("/commentform")
	public String commentform(@RequestParam(value = "no", required = true, defaultValue = "") Long no, Model model,
			 @AuthUser UserVo authUser) {
		
		BoardVo vo = boardService.getComment(no);
		vo.setNo(no);
		vo.setUserNo(authUser.getNo());
		model.addAttribute("vo", vo);
		return "board/write";
	}

}
