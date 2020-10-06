package kr.or.connect.naverreservation.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String saveFile(MultipartFile file);
	public String saveFile(List<MultipartFile> files);
	public void downloadFile(HttpServletResponse response);
}
