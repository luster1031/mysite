package com.poscoict.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String board(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestbookVo vo) {
		System.out.println("guestbookvo befor : " + vo);
		guestbookService.addMessage(vo);
		System.out.println("guestbookvo after : " + vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam(value="no", required = true, defaultValue = "") Long no
			, Model model){
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}

	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			@RequestParam(value="no", required = true, defaultValue = "") Long no
			, @RequestParam(value="password", required = true, defaultValue = "") String passwd
			, Model model)
	{
		System.out.println(no);
		model.addAttribute("no",no);
		guestbookService.deleteMessage(no, passwd);
		System.out.println("Controller");
		return "redirect:/guestbook";
	}
}
