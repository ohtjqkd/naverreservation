package kr.or.connect.naverreservation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.naverreservation.dto.DisplayInfoResponse;
import kr.or.connect.naverreservation.dto.Member;
import kr.or.connect.naverreservation.dto.ReservationInfo;
import kr.or.connect.naverreservation.service.MemberService;
import kr.or.connect.naverreservation.service.ProductService;
import kr.or.connect.naverreservation.service.PromotionService;
import kr.or.connect.naverreservation.service.ReservationService;

@Controller
@RequestMapping(path = "/")
public class MainController {
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/")
	public ModelAndView init() {
		System.out.println("init main service");
		ModelAndView modelAndView = new ModelAndView();
//		PromotionResponse promotionResponse = promotionService.getPromotionResponse();
//		ProductResponse productResponse = productService.getProductResponse(null, 0);
//		
//		modelAndView.addObject(promotionResponse);
//		modelAndView.addObject(productResponse);
		modelAndView.setViewName("mainpage");
		
		return modelAndView;
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(@RequestParam(name = "displayInfoId",required = true) Integer displayInfoId) {
		System.out.println("go detail");
		ModelAndView modelAndView = new ModelAndView();
		DisplayInfoResponse displayInfoResponse = productService.getDisplayInfoResponse(displayInfoId);
		modelAndView.addObject(displayInfoResponse);
		modelAndView.setViewName("detail");
		System.out.println(displayInfoResponse.getDisplayInfo().getId());
		return modelAndView;
	}
	
	@GetMapping("/review")
	public ModelAndView comments(@RequestParam(name = "displayInfoId",required = true) Integer displayInfoId) {
		System.out.println("go comments");
		ModelAndView modelAndView = new ModelAndView();
		DisplayInfoResponse displayInfoResponse = productService.getDisplayInfoResponse(displayInfoId);
		modelAndView.addObject(displayInfoResponse);
		modelAndView.setViewName("review");
		return modelAndView;
	}
	
	@GetMapping("/reserve")
	public ModelAndView reservation(@RequestParam(name = "displayInfoId",required = true) Integer displayInfoId) {
		ModelAndView modelAndView = new ModelAndView();
		DisplayInfoResponse displayInfoResponse = productService.getDisplayInfoResponse(displayInfoId);
		modelAndView.addObject(displayInfoResponse);
		long days = (long) (Math.random() * 5);
		LocalDate ld = LocalDate.now();
		modelAndView.addObject("reserveDate", ld.plusDays(days).format(DateTimeFormatter.ofPattern("yyyy.M.d")));
		modelAndView.setViewName("reserve");
		return modelAndView;
	}
	
	@GetMapping("/myreservation")
	public ModelAndView myReservation(@RequestParam(name = "reservationEmail",required = false) String reservationEmail) {
		ModelAndView modelAndView = new ModelAndView();
//		DisplayInfoResponse displayInfoResponse = productService.getDisplayInfoResponse(displayInfoId);
//		modelAndView.addObject(displayInfoResponse);
		modelAndView.setViewName("myreservation");
		return modelAndView;
	}
	
	@GetMapping("/reviewWrite")
	public ModelAndView reviewWrite(@RequestParam(name = "reservationInfoId", required = true) Integer reservationInfoId) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("reviewWrite");
		System.out.println(reservationService.getProductInfo(reservationInfoId));
		modelAndView.addObject("response", reservationService.getProductInfo(reservationInfoId));
		modelAndView.setViewName("reviewWrite");
		return modelAndView;
	}
	
	@RequestMapping(path = "/bookinglogin")
	public String logInForm() {
		return "/bookinglogin";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		System.out.println("login process 시작");
		System.out.println(member.getMemberEmail());
		memberService.logIn(member.getMemberEmail(), session);
		System.out.println(session.getAttribute("memberEmail"));
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(@ModelAttribute Member member, HttpSession session) {
		System.out.println("logout process 시작");
		session.removeAttribute("memberEmail");
		return "redirect:/";
	}
	
	
}
