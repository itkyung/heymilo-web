package com.heymilo.ui.param;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

public class ProductImageModel {
	private MultipartFile mainImageUpload;
	private MultipartFile subImageUpload0;
	private MultipartFile subImageUpload1;
	private MultipartFile subImageUpload2;
	private MultipartFile subImageUpload3;
	private MultipartFile subImageUpload4;
	private MultipartFile subImageUpload5;
	private MultipartFile subImageUpload6;
	private MultipartFile subImageUpload7;
	private MultipartFile subImageUpload8;
	private int uploadNumber;	// -1이면 main, 나머지는 sub의 이름.
	
	public MultipartFile getUploadedFile() {
		//현재 시점에 업로드된 파일만 리턴한다.
		switch (uploadNumber) {
		case -1:
			return mainImageUpload;
		case 0 :
			return subImageUpload0;
		case 1 :
			return subImageUpload1;
		case 2 :
			return subImageUpload2;
		case 3 :
			return subImageUpload3;
		case 4 :
			return subImageUpload4;
		case 5 :
			return subImageUpload5;
		case 6 :
			return subImageUpload6;
		case 7 :
			return subImageUpload7;
		case 8 :
			return subImageUpload8;
		default:
			return null;
		}
	}
	
	public MultipartFile getMainImageUpload() {
		return mainImageUpload;
	}
	public void setMainImageUpload(MultipartFile mainImageUpload) {
		this.mainImageUpload = mainImageUpload;
	}
	public MultipartFile getSubImageUpload0() {
		return subImageUpload0;
	}
	public void setSubImageUpload0(MultipartFile subImageUpload0) {
		this.subImageUpload0 = subImageUpload0;
	}
	public MultipartFile getSubImageUpload1() {
		return subImageUpload1;
	}
	public void setSubImageUpload1(MultipartFile subImageUpload1) {
		this.subImageUpload1 = subImageUpload1;
	}
	public MultipartFile getSubImageUpload2() {
		return subImageUpload2;
	}
	public void setSubImageUpload2(MultipartFile subImageUpload2) {
		this.subImageUpload2 = subImageUpload2;
	}
	public MultipartFile getSubImageUpload3() {
		return subImageUpload3;
	}
	public void setSubImageUpload3(MultipartFile subImageUpload3) {
		this.subImageUpload3 = subImageUpload3;
	}
	public MultipartFile getSubImageUpload4() {
		return subImageUpload4;
	}
	public void setSubImageUpload4(MultipartFile subImageUpload4) {
		this.subImageUpload4 = subImageUpload4;
	}
	public MultipartFile getSubImageUpload5() {
		return subImageUpload5;
	}
	public void setSubImageUpload5(MultipartFile subImageUpload5) {
		this.subImageUpload5 = subImageUpload5;
	}
	public MultipartFile getSubImageUpload6() {
		return subImageUpload6;
	}
	public void setSubImageUpload6(MultipartFile subImageUpload6) {
		this.subImageUpload6 = subImageUpload6;
	}
	public MultipartFile getSubImageUpload7() {
		return subImageUpload7;
	}
	public void setSubImageUpload7(MultipartFile subImageUpload7) {
		this.subImageUpload7 = subImageUpload7;
	}
	public MultipartFile getSubImageUpload8() {
		return subImageUpload8;
	}
	public void setSubImageUpload8(MultipartFile subImageUpload8) {
		this.subImageUpload8 = subImageUpload8;
	}

	public int getUploadNumber() {
		return uploadNumber;
	}

	public void setUploadNumber(int uploadNumber) {
		this.uploadNumber = uploadNumber;
	}
	
	
}
