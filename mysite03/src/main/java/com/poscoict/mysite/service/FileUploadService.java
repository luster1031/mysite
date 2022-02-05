package com.poscoict.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class FileUploadService {
	@Autowired
	private SiteRepository siteRepository;
	
	private static String SAVE_PATH ="/upload-images";
	private static String URL_BASE = "/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = null;
		System.out.println("들어옴");
		try {
			//	파일이 있는ㄴ지 없는지 확인
			if(multipartFile.isEmpty()) {
				System.out.println("파일 없음 "  + multipartFile);
				SiteVo sitevo = siteRepository.find();
				return sitevo.getProfile();
			}
			
			System.out.println("파일 " + multipartFile);
			
			String originFileName = multipartFile.getOriginalFilename();
			String exName = originFileName.substring(originFileName.lastIndexOf('.')+1);	// 확장자
			String saveFilename  = genearteSaveFilename(exName);
			long fileSize = multipartFile.getSize();
			
			System.out.println("###############" + originFileName);
			System.out.println("###############" + fileSize);
			System.out.println("###############" + exName);
			System.out.println("###############" + saveFilename);
			
			byte[] data = multipartFile.getBytes();
			//	저장 위치
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			
			url = URL_BASE + "/" + saveFilename;
			
			
		}catch(IOException ex) {
			// fileuploadexception 으로 해주는게 좋다. 
			throw new RuntimeException("file upload error : "+ ex);
		}
		return url;
	}

	//	날짜
	private String genearteSaveFilename(String exName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + exName);
		return filename;
	}

}
