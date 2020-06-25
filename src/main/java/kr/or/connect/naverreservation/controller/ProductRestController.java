package kr.or.connect.naverreservation.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.naverreservation.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@GetMapping
	public HashMap<String, Object> product(@RequestParam(name = "categoryId", required = false) Integer categoryId, @RequestParam(name="start", defaultValue = "0") Integer start) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println(categoryId);
		map.put("productResponse", productService.getProductResponse(categoryId, start));
		return map;
	}
	
	@GetMapping("/{displayInfoId}")
	public HashMap<String, Object> displayInfo(@PathVariable(name = "displayInfoId", required = true) Integer displayInfoId){
		HashMap<String,Object> map = new HashMap<String, Object>();
		System.out.println(map.toString());
		map.put("displayInfoResponse", productService.getDisplayInfoResponse(displayInfoId));
		return map;
	}
	
}
