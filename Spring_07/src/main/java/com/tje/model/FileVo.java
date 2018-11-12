package com.tje.model;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class FileVo {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;		
	}
	
	public boolean saveFile(String path) {
		boolean flag = true;
		
		File dir = new File(path);
		
		// 상위 경로까지 생성
		if( !dir.exists() )
			dir.mkdirs();
		// 해당 절대 경로에 이런 이름의 파일을 저장
						// 절대경로			// 파일의 이름
		File f = new File(dir, file.getOriginalFilename());
		
		System.out.println(file.getOriginalFilename());
		
		
		try {
			// 받아온 (multipart)file 을 (dir, file.getOriginalFilename()) 형식으로 전송
			file.transferTo(f);
		} catch (Exception e) {	
			flag = false;
			e.printStackTrace();
		}
		
		return flag;
	}
}








