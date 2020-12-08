package com.triplemovie.pjt.movie;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplemovie.pjt.SecurityUtils;
import com.triplemovie.pjt.api.model.kmdb.KmdbParam;
import com.triplemovie.pjt.movie.model.CinemaVO;
import com.triplemovie.pjt.movie.model.CmtDMI;
import com.triplemovie.pjt.movie.model.CmtVO;
import com.triplemovie.pjt.movie.model.MovieDTO;
import com.triplemovie.pjt.user.model.UserTicketVO;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	//메인 페이지
	@RequestMapping(value="/main")
	public String movieMain(Model model,HttpSession hs) {
		movieService.movieInfo(hs);
		model.addAttribute("movieList",movieService.movieMain(hs));
		model.addAttribute("view", "movie/main");
		return "templete/temp";
	}
	
	//검색페이지
	@RequestMapping(value="/movieSearch")
	public String movieSerach(Model model) {
		model.addAttribute("view", "movie/movieSearch");
		return "templete/temp";
	}
	
	//영화 티켓 페이지
	@RequestMapping(value="/movieTicket")
	public String movieTicekting(Model model,HttpSession hs) {
		model.addAttribute("movieList",movieService.movieMain(hs));
		model.addAttribute("movieDay", movieService.movieR_dt());
		model.addAttribute("view","movie/movieTicket");
		return "templete/temp";
	}
	
	//영화 검색 결과
	@RequestMapping(value="/searchResult")
	public String movieSearch(Model model, KmdbParam param) {
		model.addAttribute("movieList",movieService.moiveNmSearch(param));		
		model.addAttribute("view","movie/searchResult");
		return "templete/temp";
	}
	
	//디테일 페이지
	@RequestMapping(value="/detail")
	public String movieDetail(Model model,MovieDTO movieDTO, HttpSession hs) {
		
		int i_user = SecurityUtils.getLoginUserPk(hs);
		movieDTO.setI_user(i_user);
		model.addAttribute("movieCmt", movieService.movieSelCmt(movieDTO));
		model.addAttribute("movieDetail",movieService.movieDetail(movieDTO));
		model.addAttribute("title", "영화 상세정보");
		model.addAttribute("view","movie/movieDetail");
		return "templete/temp";
	}
	
	
	//댓글쓰기
	@RequestMapping(value="/ajaxCmt", method = RequestMethod.POST)
	@ResponseBody 
	public int ajaxCmt(@RequestBody CmtVO vo, HttpSession hs){ 
		int i_user = SecurityUtils.getLoginUserPk(hs); 
		vo.setI_user(i_user); 
		return movieService.insCmt(vo); 
	}
	
	//좋아요 취소
	@RequestMapping(value="/ajaxCmt_Notfavorite", method = RequestMethod.GET)
	@ResponseBody 
	public int ajaxCmt_Notfavorite(CmtDMI param,HttpSession hs) { 
		int i_user = SecurityUtils.getLoginUserPk(hs);
		param.setI_user(i_user);
		return movieService.delFavorite(param); 
	 }
	
	//좋아요 누름
	@RequestMapping(value="/ajaxCmt_favorite", method = RequestMethod.GET)
	@ResponseBody 
	public int ajaxCmt_favorite(CmtDMI param,HttpSession hs) { 
		int i_user = SecurityUtils.getLoginUserPk(hs);
		param.setI_user(i_user);
		return movieService.insFavorite(param); 
	 }
	
	
	//예매할 수 있는 영화이름 가져오기
	@RequestMapping(value="/selMovieNm", method = RequestMethod.GET)
	@ResponseBody
	public CinemaVO[] ajaxmovieNm(CinemaVO param) {
		return movieService.selMovieNm(param);
	}
	
	//티켓 시간 불러오기(ajax)
	@RequestMapping(value="/ajaxSelTime")
	@ResponseBody 
	public CinemaVO[] selTime(CinemaVO param){
		String movieNm = param.getMovieNm().replaceAll("\\p{Z}", "");
		param.setMovieNm(movieNm);
		return movieService.selTime(param); 
	}
	
	@RequestMapping(value="/ajaxSelRoom")
	@ResponseBody
	public CinemaVO[] ajaxSelRoom(CinemaVO param) {
		return movieService.selRoom(param);
	}
	
	
	//좌석고르기
	@RequestMapping(value="/movieSheets")
	public String movieSheets(Model model, UserTicketVO vo) {
		model.addAttribute("selSeats", movieService.selSeats(vo));
		model.addAttribute("userTicket", vo);
		model.addAttribute("title", "좌석고르기");
		model.addAttribute("view","movie/movieSeats");
		return "templete/temp";
	}
	
	//최종 예매하기
	@RequestMapping(value="/insTicket", method = RequestMethod.POST)
	public String insTicket(Model model, UserTicketVO vo, HttpSession hs,RedirectAttributes ra) {
		int i_user = SecurityUtils.getLoginUserPk(hs);
		vo.setI_user(i_user);
		int result = movieService.insTicketing(vo);
		if(result == 1) {
			ra.addFlashAttribute("successTicket", "예매가 완료되었습니다");
			return "redirect:/movie/main";
		}else {
			model.addAttribute("title", "영화 상세정보");
			model.addAttribute("view","movie/seatTest");
			model.addAttribute("errorTicket", "예매를 할수가 없습니다.");
			return "templete/temp";
		}
	}

}
