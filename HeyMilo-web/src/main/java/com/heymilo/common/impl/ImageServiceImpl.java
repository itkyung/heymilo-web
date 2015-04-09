package com.heymilo.common.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.heymilo.common.ImageInfo;
import com.heymilo.common.FileService;
import com.heymilo.identity.entity.User;


public class ImageServiceImpl {
	
	private int thumbnailWidth = 200;
	
	@Value("${catalina.home}")
	private String opengardenHome;
	
	@Value("${uploadPath}")
	private String uploadPath;
	
	@Value("${fileAccessUrl}")
	private String fileAccessUrl;
	
	
	public ImageInfo updateImage(MultipartFile file, User user,String contextPath)  throws IOException{
		
		String accessUrl = "/heymilo" + fileAccessUrl;
		
		try{
			File dir = new File(opengardenHome + uploadPath + user.getId());
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			File tmpDir = new File(dir,"temp");
			if(!tmpDir.exists()){
				tmpDir.mkdirs();
			}
			
			File thumDir = new File(dir,"thumb");
			if(!thumDir.exists()){
				thumDir.mkdirs();
			}
			
			File orgDir = new File(dir,"org");
			if(!orgDir.exists()){
				orgDir.mkdirs();
			}
		
			String fileName = makeFileName();
			
			File orgImageFile = new File(orgDir,fileName);
			File tmpFile = new File(tmpDir,fileName);
			file.transferTo(tmpFile);
			
			ImageInfo info = makeImageResizeBig(orgImageFile, tmpFile, true,user,fileName,file.getContentType());
			
			return info;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			
		}
		
	}
	
	
	private ImageInfo makeImageResizeBig(File targetImageFile,File importedFile,boolean makeThumbnail,User user,String fileName,String contentType) throws Exception{
		ImageInfo info = new ImageInfo();
		
		File dir = new File(opengardenHome + uploadPath + user.getId());
		if(!dir.exists()){
			dir.mkdirs();
		}
		File thumDir = new File(dir,"thumb");
		if(!thumDir.exists()){
			thumDir.mkdirs();
		}
		String accessUrl = "/heymilo" + fileAccessUrl;
		
		//여기서 임시 파일의 해상도를 체크한다.
		//체크해서 450이상의 크기면 width를 450으로 줄인다.
		BufferedImage tmpImage = ImageIO.read(importedFile);
		int tmpWidth = tmpImage.getWidth();
		int tmpHeight = tmpImage.getHeight();
		
		if(tmpWidth > tmpHeight){
			//가로형이면 세로높이가 450보다 클경우에는 450으로 맞춘다. 450보다 작으면 업로드 불가하다.
			if(tmpHeight < 450){
				info.setSuccess(false);
				info.setMsg("이미지의 해상도의 넓이 또는 높이가 450이상 이어야합니다.");
				return info;
			}else{
				//세로 높이를 450으로 맞춘다.
				int orgNewWidth = (tmpWidth * 450) / tmpHeight;
				resizeImage(tmpImage, targetImageFile,orgNewWidth, 450);
			}
			
		}else if(tmpWidth < tmpHeight){
			//가로형이면 가로가 450보다 클경우에는 450으로 맞춘다. 450보다 작으면 업로드 불가하다.
			if(tmpWidth < 450){
				info.setSuccess(false);
				info.setMsg("이미지의 해상도의 넓이 또는 높이가 450이상 이어야합니다.");
				return info;
			}else{
				int orgNewHeight = (tmpHeight * 450) / tmpWidth;
				resizeImage(tmpImage, targetImageFile,450, orgNewHeight);
			}
		}else{
			//정사각형.
			if(tmpWidth > 450){
				//사이즈를 줄인다.
				resizeImage(tmpImage, targetImageFile,450, 450);
			}else{
				fileCopy(importedFile, targetImageFile);
			}
		}
		
		
		BufferedImage orgImage = ImageIO.read(targetImageFile);
		String type = contentType;
		
		String postfix = "";
		if(type != null){
			if(type.endsWith("jpeg") || type.endsWith("jpg")){
				postfix = ".jpg";
			}else if(type.endsWith("gif")){
				postfix = ".gif";
			}else{
				postfix = ".png";
			}
		}else{
			postfix = ".png";
		}
		
		info.setWidth(orgImage.getWidth());
		info.setHeight(orgImage.getHeight());
		info.setImagePath(accessUrl + user.getId() + "/org/" + fileName);
		
		
		int newHeight = (info.getHeight() * thumbnailWidth) / info.getWidth();
		
		if(makeThumbnail){
			//Thumbnail이미지를 만든다.
			File thumbFile = new File(thumDir,fileName+".png");
			resizeImage(orgImage, thumbFile,thumbnailWidth,newHeight);
			
			info.setThumbnailPath(accessUrl + user.getId() + "/thumb/" + fileName + ".png");
		}else{
			info.setThumbnailPath(info.getImagePath());
		}
		info.setSuccess(true);
		return info;
	}

	private ImageInfo makeImageResizeSmall(File targetImageFile,File importedFile,boolean makeThumbnail,User user,String fileName,String contentType) throws Exception{
		File dir = new File(opengardenHome + uploadPath + user.getId());
		if(!dir.exists()){
			dir.mkdirs();
		}
		File thumDir = new File(dir,"thumb");
		if(!thumDir.exists()){
			thumDir.mkdirs();
		}
		
		String accessUrl = "/heymilo" + fileAccessUrl;
		ImageInfo info = new ImageInfo();
		//여기서 임시 파일의 해상도를 체크한다.
		//체크해서 160이상의 크기면 width를 160으로 줄인다.
		BufferedImage tmpImage = ImageIO.read(importedFile);
		int tmpWidth = tmpImage.getWidth();
		int tmpHeight = tmpImage.getHeight();
		if(tmpWidth > 180){
			//사이즈를 줄인다.
			int orgNewHeight = (tmpHeight * 180) / tmpWidth;
			resizeImage(tmpImage, targetImageFile,180, orgNewHeight);
			
			
		}else if(tmpWidth < 180){
			//width가 160보다 작으면 이미지 업로드 불가.
			info.setSuccess(false);
			info.setMsg("이미지의 해상도의 넓이가 160이상이어야합니다.");
			return info;
		}else{
			//tmpFile의 내용을 orgImageFile로 그대로 복사한다.
			fileCopy(importedFile, targetImageFile);
		}
		
		
		BufferedImage orgImage = ImageIO.read(targetImageFile);
		String type = contentType;
		
		String postfix = "";
		if(type != null){
			if(type.endsWith("jpeg") || type.endsWith("jpg")){
				postfix = ".jpg";
			}else if(type.endsWith("gif")){
				postfix = ".gif";
			}else{
				postfix = ".png";
			}
		}else{
			postfix = ".png";
		}
		
		info.setWidth(orgImage.getWidth());
		info.setHeight(orgImage.getHeight());
		if(makeThumbnail){
			info.setImagePath(accessUrl + user.getId() + "/org/" + fileName);
		}else{
			info.setImagePath(accessUrl + user.getId() + "/org/" + fileName);
		}
		
		int newHeight = (info.getHeight() * thumbnailWidth) / info.getWidth();
		
		if(makeThumbnail){
			//Thumbnail이미지를 만든다.
			File thumbFile = new File(thumDir,fileName+".png");
			resizeImage(orgImage, thumbFile,thumbnailWidth,newHeight);
			
			info.setThumbnailPath(accessUrl + user.getId() + "/thumb/" + fileName + ".png");
		}else{
			info.setThumbnailPath(info.getImagePath());
		}
		
		info.setSuccess(true);
		
		return info;
	}

	private void resizeImage(BufferedImage orgImage,File thumbFile,int width,int height) throws IOException{
		int type = orgImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : orgImage.getType();
		
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(orgImage, 0,0,width,height,null);
		g.dispose();
		
		ImageIO.write(resizedImage, "png", thumbFile);
	}
	
	
	private String makeFileName(){
		UUID uid = UUID.randomUUID();
		return uid.toString();
		
	}
	
	private void fileCopy(File srcFile,File targetFile) throws Exception{
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		
		int data = 0;
		while((data = fis.read()) != -1){
			fos.write(data);
		}
		fis.close();
		fos.close();
	}

	

}
