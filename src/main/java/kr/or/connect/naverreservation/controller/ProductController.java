package kr.or.connect.naverreservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.naverreservation.dto.ProductResponse;
import kr.or.connect.naverreservation.service.ProductService;

@Controller
@RequestMapping(path = "")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ModelAndView main(){
		productService.init();
		HashMap<String, Object> map = new HashMap<String, Object>();
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("main controller 시작");
		
		ModelAndView modelView = new ModelAndView();
//		map.put("list", list);
//		map.put("count",count);
//		map.put("categories",categories);
//		map.put("promotion", promotion);
		
		modelView.addAllObjects(map);
		modelView.setViewName("main");
		System.out.println("main controller완료 main를 return합니다.");
		modelAndView.setViewName("main");
		System.out.println(modelAndView.toString());
		return modelAndView;
	}
	
	@GetMapping("/product/{displayId}")
	public String display() {
		
		return "displayInfo";
	}
	
}
