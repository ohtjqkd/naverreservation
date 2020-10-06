package kr.or.connect.naverreservation.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.connect.naverreservation.dao.ReservationDao;
import kr.or.connect.naverreservation.dto.ReservationInfoResponse;
import kr.or.connect.naverreservation.dto.ReservationResponse;
import kr.or.connect.naverreservation.service.FileService;
import kr.or.connect.naverreservation.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	FileService fileService; 
	
	@GetMapping
	public HashMap<String, Object> getMyReservation(@RequestParam(name="reservationEmail", required = true) String reservationEmail){
		HashMap<String, Object> map = new HashMap<String, Object>();
		ReservationInfoResponse reservationInfoResponse = reservationService.getReservationInfoResponse(reservationEmail);
		System.out.println(reservationInfoResponse);
		map.put("reservationInfoResponse", reservationInfoResponse);
		return map;
	}
	
	@PostMapping("/{reservationInfoId}/comments")
	public @ResponseBody String reservation(MultipartHttpServletRequest request) throws Exception{
		System.out.println("post request controller start");
		String commentStr = request.getParameter("reviewText");
		System.out.println("리뷰내용"+commentStr);
		String reviewScore = request.getParameter("score");
		System.out.println("점수"+reviewScore);
		Iterator<String> nameIter = request.getFileNames();
		while(nameIter.hasNext()) {
			String name = nameIter.next();
			System.out.println("file name = "+name);
			fileService.saveFile(request.getFile(name));
		}
		return "success";
	}
//	
	@PutMapping("/{reservationInfoId}")
	public HashMap<String, Object> removeReservation(@PathVariable(name = "reservationInfoId", required = true) Integer reservationInfoId){
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println(reservationInfoId);
		return map;
	}
//	public HashMap<String, Object> reservation() Integer categoryId, @RequestParam(name="start", defaultValue = "0") Integer start) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		System.out.println(categoryId);
//		map.put("reservationInfoResponse", reservationService.getReservationInfoResponse(categoryId, start));
//		return map;
//	}
}
