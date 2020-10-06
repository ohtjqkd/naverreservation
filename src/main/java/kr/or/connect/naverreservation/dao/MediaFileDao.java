package kr.or.connect.naverreservation.dao;

import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
@Repository
public class MediaFileDao {
	
	private final String directoryPath = "c:/tmp/";
	
	public String saveFile(MultipartFile file) throws IOException{
		String filePath = "";
		String exe = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
		Calendar cal = Calendar.getInstance();
		String dateString = sdf.format(cal.getTime());
		switch(file.getContentType()) {
			case "image/jpeg":
				exe = ".jpg";
				break;
			case "image/png":
				exe = ".png";
				break;
			default:
				exe = ".tmp";
		}
		int random = new Random().nextInt(100000000);
		filePath = dateString + random + exe;
		System.out.println("------------------------Original File Info-------------------");
		System.out.println("File name:" + file.getOriginalFilename());
		System.out.println("File size:" + file.getSize());
		System.out.println("File exe:" +file.getContentType());
		System.out.println("------------------------Original File Info end-------------------");
		System.out.println("------------------------New File Info---------------------------");
		System.out.println("New file name:" + filePath);
		System.out.println("------------------------New File Info end---------------------------");
		
		File newFile = new File(directoryPath + filePath);
		if(newFile.exists()) {
			System.out.println("File already exists");
			throw new RuntimeException();
		}
		try (FileOutputStream fos = new FileOutputStream(newFile);
				InputStream is = file.getInputStream();){
			int readCount = 0;
			byte buffer[] = new byte[1024];
			while((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception e) {
			throw new RuntimeException("File Save Error");
		}
		return "File save OK";
	}
	
}
