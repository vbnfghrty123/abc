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
import com.tje.services.*;

@Controller
public class FileUploadControllerDown {
	
	@Autowired
	// 
	private FileUploadService service;
	@Autowired
	private FileDownloadService downloadService;
	
	@ModelAttribute("realPath")
	public String getRealPath(HttpServletRequest request) {
		// 실제 서버가 구동중인 경로를 반환    			// 서버 내에서 동작하는것이기 때문에 mvc:resources 를 여기서 적용하는 것이 아니다 클라이언트에서 적용
		String realPath = request.getRealPath("/WEB-INF/resources/upload/");
		return realPath;
	}
	
	// 파일전송폼을 불러오기 위한 컨트롤러
    @RequestMapping(value="/file/upload1", method=RequestMethod.GET)
    public String fileForm(){
        return "file/uploadForm";      
    }
    
    // 파일전송 요청을 처리하기 위한 컨트롤러
    @RequestMapping(value="/file/upload1", method=RequestMethod.POST)
    public String fileSubmit(Model model, FileVo file, @ModelAttribute("realPath") String realPath){
    	    	
    	service.saveFile(realPath, file);
    	
        String fileName = file.getFile().getOriginalFilename(); // 원본 파일명
        long fileSize = file.getFile().getSize(); // 원본 파일 크기
        
        // System.out.println("UPLOAD...FileName: "+fileName+", FileSize: "+fileSize);
 
        HashMap< String, Object > map = new HashMap< String, Object>();
        map.put("fileName", fileName);
        map.put("fileSize", fileSize);
        model.addAttribute("uploadFile", map);
         
        return "file/checkUpload";
    }
    
    @RequestMapping(value="/file/upload/multiple1", method=RequestMethod.GET)
    public String filesForm(){
        return "file/multipleUploadForm";      
    }
    
    // 파일전송 요청을 처리하기 위한 컨트롤러
    @RequestMapping(value="/file/upload/multiple1", method=RequestMethod.POST)
    public String filesSubmit(
    		Model model, 				
    		@RequestParam("files") ArrayList<MultipartFile> file1, 
    		@ModelAttribute("realPath") String realPath) {
    	
    	HashMap<String, Long> map = new HashMap<>();
    	
    	// 받아온 파일의 갯수만큼 처리
    	for( MultipartFile mf : file1 ) {
    		FileVo file = new FileVo();
    		// FileVo에 file 을 여기서 받아온 multipartfile 로 setter 처리 ( 해당 정보 하나를 준다 )
    		file.setFile(mf);
    						// 절대경로	multipartfile
    		System.out.println(service.saveFile(realPath, file));
    		// map 형식으로 받아온 file1에 대한 정보가 있는 multipartfile 의 (경로명 붙어있지않는 orinal)이름과 사이즈 불러오기
    		map.put(mf.getOriginalFilename(), mf.getSize());
    	}
    	
    	// map 을 model 에 attribute >> (파일의 이름과 사이즈 )
        model.addAttribute("uploadFiles", map);
         
        return "file/checkMultipleUpload";
    }   
    
    @RequestMapping(value="/file/download", method=RequestMethod.GET)
    public String filesSubmit(Model model, @ModelAttribute("realPath") String realPath) {
    	
        model.addAttribute("downloadFiles", downloadService.downloadFiles(realPath));
         
        return "file/download";
    }
}








