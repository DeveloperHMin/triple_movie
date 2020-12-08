package com.triplemovie.pjt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triplemovie.pjt.movie.MovieService;
import com.triplemovie.pjt.movie.model.CinemaVO;


@Controller
public class HomeController {
	
	@Autowired
	private MovieService service;

	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, HttpSession hs) {
		 if(Const.main == null) { 
			 service.movieInfo(hs);
			 Const.main = "1";
		}
		
		for(int i = 0; i<3; i++) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, i);
		
		Date dDate = cal.getTime();
		SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String day = dSdf.format(dDate);
		CinemaVO param = new CinemaVO();
		param.setR_dt(Integer.parseInt(day));
		CinemaVO vo = service.selCinema(param);
		if(vo == null) {
			service.insCinema(param,hs);
		}
		}
		System.out.println("start");
		return "redirect:/movie/main";
	}
	
}
