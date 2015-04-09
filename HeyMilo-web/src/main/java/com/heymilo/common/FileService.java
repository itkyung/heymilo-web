package com.heymilo.common;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.heymilo.identity.entity.User;

public interface FileService {
	ImageInfo updateImage(MultipartFile file,String fileGroup) throws IOException;
	
}
