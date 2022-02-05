package com.poscoict.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		System.out.println("join get");
		return "user/join";
	}
	
	/*
	 * // 회원 가입
	 * 
	 * @RequestMapping(value="/join", method=RequestMethod.POST) public String
	 * join(UserVo vo) { userService.join(vo); return "redirect:/user/joinsuccess";
	 * }
	 */
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
//			List<ObjectError> list= result.getAllErrors();
//			for(ObjectError error : list) {
//				System.out.println(error);
//			}
			
			//model.addAttribute("userVo", userVo); -> ModelAttribute
			System.out.println(result.toString());
			System.out.println("join error: "+ vo.toString());
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		System.out.println("join error 아님: "+ vo.toString());
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		
		Long userNO = authUser.getNo();
		UserVo userVo = userService.getUser(userNO);
		model.addAttribute("userVo", userVo);
		return "/user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		authUser = userService.getUser(authUser.getNo());
		System.out.println(userVo);
		return "redirect:/";
	}
	
	/*
	 * 컨트롤마다 에러 다르게 처리하고 싶을 때,
	@ExceptionHandler(Exception.class)
	public String UserControllerExceptionHandler() {
		return "error/exception";
	}
	*/
}