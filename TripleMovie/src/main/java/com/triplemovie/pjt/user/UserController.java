package com.triplemovie.pjt.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplemovie.pjt.Const;
import com.triplemovie.pjt.SecurityUtils;
import com.triplemovie.pjt.user.model.UserTicketVO;
import com.triplemovie.pjt.user.model.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value ="/join",method = RequestMethod.GET)
	public String join(Model model) {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinProc(Model model, UserVO param) {
		int result = service.join(param);
		return "redirect:/movie/main";
	}

	
	@RequestMapping(value="/login", method = RequestMethod.POST) 
	public String loginProc(UserVO param, HttpSession hs,RedirectAttributes ra, HttpServletRequest request) {
		int result = service.login(param);
		if(result == 1) {
			hs.setAttribute(Const.LOGIN_USER, param);
		    String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		}
		
		String msg = null;
		if(result == 2) {
			msg = "아이디를 확인해 주세요.";
		} else if(result == 3) {
			msg = "비밀번호를 확인해 주세요.";
		}
		
		param.setMsg(msg);
		ra.addFlashAttribute("data", param);
	    String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession hs, HttpServletRequest request) {
		hs.invalidate();
	    String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value="/ajaxIdChk", method=RequestMethod.POST)
	@ResponseBody
	public String ajaxIdChk(@RequestBody UserVO param) {
		System.out.println("user_id : " + param.getUser_id());
		int result = service.login(param);
		return String.valueOf(result);
	}
	
	@RequestMapping(value="/ajaxChkNick", method=RequestMethod.POST)
	@ResponseBody
	public int ajaxChkNick(@RequestBody UserVO param) {
		int result = service.chkNick(param);
		return result;
	}
	
	@RequestMapping(value="/myPage")
	public String ticketInfo(Model model, HttpSession hs) {
		
		int i_user = SecurityUtils.getLoginUserPk(hs);
		
		model.addAttribute("ticketInfo", service.selTicketInfo(i_user));
		model.addAttribute("title", "예매정보");
		model.addAttribute("view", "user/myPage");
		return "templete/temp";
	}
	
	//예매취소
	@RequestMapping(value="/delTicket", method=RequestMethod.POST)
	@ResponseBody
	public int delTicket(@RequestBody UserTicketVO param, HttpSession hs) 
	{
		int i_user = SecurityUtils.getLoginUserPk(hs);
		param.setI_user(i_user);
		return service.delTicket(param);
	}
}
