package kr.or.connect.naverreservation.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.connect.naverreservation.dto.Member;
import kr.or.connect.naverreservation.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(path = "/loginform")
	public String logInForm() {
		return "/loginform";
	}
	@RequestMapping(path = "login",method = RequestMethod.POST)
	public String login(@ModelAttribute Member member, HttpSession session) {
		System.out.println("login process 시작");
		System.out.println(member.getMemberEmail());
		memberService.logIn(member.getMemberEmail(), session);
		System.out.println(session.getAttribute("memberEmail"));
		return "redirect:/";
	}
}
