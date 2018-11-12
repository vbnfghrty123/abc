package com.tje.services;

import org.springframework.stereotype.Service;

import com.tje.model.FileVo;

@Service
public class FileUploadService {
								// 절대경로 // 받아온 파일의 정보
	public boolean saveFile(String path,FileVo file) {
		boolean flag = file.saveFile(path);
		return flag;
	}
}
