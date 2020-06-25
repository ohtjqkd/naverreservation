package kr.or.connect.naverreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.naverreservation.dto.DisplayInfoResponse;
import kr.or.connect.naverreservation.dto.ProductResponse;
import kr.or.connect.naverreservation.dto.PromotionResponse;
import kr.or.connect.naverreservation.service.ProductService;
import kr.or.connect.naverreservation.service.PromotionService;

@Controller
@RequestMapping(path = "/")
public class MainController {
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ModelAndView init() {
		System.out.println("init main service");
		ModelAndView modelAndView = new ModelAndView();
		PromotionResponse promotionResponse = promotionService.getPromotionResponse();
		ProductResponse productResponse = productService.getProductResponse(null, 0);
		
		modelAndView.addObject(promotionResponse);
		modelAndView.addObject(productResponse);
		modelAndView.setViewName("main");
		
		return modelAndView;
	}
	
	@GetMapping(path = "/detail")
	public ModelAndView detail(@RequestParam(name = "displayInfoId",required = true) Integer displayInfoId) {
		ModelAndView modelAndView = new ModelAndView();
		DisplayInfoResponse displayInfoResponse = productService.getDisplayInfoResponse(displayInfoId);
		modelAndView.addObject(displayInfoResponse);
		modelAndView.setViewName("detail");
		return modelAndView;
	}
}
