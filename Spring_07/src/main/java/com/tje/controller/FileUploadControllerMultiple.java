package com.tje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.util.*;
import com.tje.services.FileUploadService;
import com.tje.model.*;

@Controller
public class FileUploadControllerMultiple {
	
	@Autowired
	private FileUploadService service;
	
	// 파일전송폼을 불러오기 위한 컨트롤러
    @RequestMapping(value="/file/upload/multiple", method=RequestMethod.GET)
    public String fileForm(){
        return "file/uploadForm";      
    }
    @ModelAttribute("realPath")
    public String realPath(HttpServletRequest request) {							//resources
    	String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
    	return realPath;
    }
    // 파일전송 요청을 처리하기 위한 컨트롤러
    @RequestMapping(value="/file/upload/multiple", method=RequestMethod.POST)
    public String getFile(Model model, @RequestParam ArrayList<MultipartFile> files, @ModelAttribute("realPath") String realPath){
    	// 실제 서버가 구동중인 경로를 반환
    	HashMap<String,Long> map = new HashMap<>();
    	
    	for( MultipartFile mf : files) {
    		FileVo file = new FileVo();
    		file.setFile(mf);
    		service.saveFile(realPath, file);
    	map.put(mf.getOriginalFilename(),mf.getSize());
    	}
    	
    	model.addAttribute("uploadFiles",map);
        return "file/checkMultipleUpload";
    }
}








