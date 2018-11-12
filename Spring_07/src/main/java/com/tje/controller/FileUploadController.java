package com.tje.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tje.model.FileVo;
import com.tje.services.FileUploadService;

@Controller
public class FileUploadController {

	@Autowired
	private FileUploadService service;
	
	@RequestMapping(value="/file/upload", method=RequestMethod.GET)
	public String fileForm() {
		return "file/uploadForm";
	}

	@RequestMapping(value="/file/upload", method=RequestMethod.POST)
	public String getFile(Model model, FileVo file , HttpServletRequest request) {
								// request.getRealPath("/WEB-INF/upload/");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		System.out.println(realPath);
		service.saveFile(realPath,file);
		String fileName = file.getFile().getOriginalFilename();
		long fileSize = file.getFile().getSize();
		System.out.println("fileName :"+fileName+"fileSize:"+fileSize);
		
		HashMap<String, Object> map = new HashMap<String , Object>();
		map.put("fileName", fileName);
		map.put("fileSize", fileSize);
		model.addAttribute("uploadFile",map);
		return "file/checkUpload";
	}
	
	
}
