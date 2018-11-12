package com.tje.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FileDownloadService {

	public HashMap<String, Long> downloadFiles(String path) {
		HashMap<String, Long> map = new HashMap<>();
    	
		File dir = new File(path);
		if( !dir.exists() ) {
			dir.mkdirs();
			return null;
		}
		
		String [] list = dir.list();
		
    	for( String fileName : list ) {
    		File file = new File(dir, fileName);
    		
    		if( file.isFile() )
    			map.put(fileName, file.length());
    	}
    	
    	return map;
	}
	
}
