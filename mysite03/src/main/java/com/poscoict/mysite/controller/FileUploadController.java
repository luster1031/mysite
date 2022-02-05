package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.service.FileUploadService;


@Controller
public class FileUploadController {
	@Autowired
	private FileUploadService fileUploadService;
	
//	@RequestMapping({"","/form"})
//	public  String form() {
//		return "form";
//	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(
			@RequestParam(value="email",required=true,defaultValue="")String email,
			@RequestParam(value="upload-file") MultipartFile multipartFile
			, Model model) {
		
		System.out.println("email : " + email);
		System.out.println("multipartFile : " + multipartFile);
		
		
		String url = fileUploadService.restore(multipartFile);
		model.addAttribute("url",url);
		
		return "result";
	}
}
