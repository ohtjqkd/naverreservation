package kr.or.connect.naverreservation.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.naverreservation.service.PromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionRestController {
	
	@Autowired
	PromotionService promotionService;
	
	@GetMapping
	public HashMap<String, Object> promotion(){
		System.out.println("create promotionResponse");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("promotionResponse", promotionService.getPromotionResponse());
		return map;
	}
}
