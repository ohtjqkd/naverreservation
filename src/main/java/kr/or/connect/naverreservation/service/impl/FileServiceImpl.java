package kr.or.connect.naverreservation.service.impl;

import java.util.List;

import javax.annotation.processing.FilerException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.naverreservation.dao.MediaFileDao;
import kr.or.connect.naverreservation.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	MediaFileDao mediaFileDao;

	@Transactional
	@Override
	public String saveFile(MultipartFile file){
		try{
			return mediaFileDao.saveFile(file);
		} catch (Exception e){
			System.out.println(e.getStackTrace());
			return e.toString();
		}
		
	}
	
	@Override
	public String saveFile(List<MultipartFile> files) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void downloadFile(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
