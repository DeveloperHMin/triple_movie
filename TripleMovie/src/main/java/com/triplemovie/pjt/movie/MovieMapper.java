package com.triplemovie.pjt.movie;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triplemovie.pjt.movie.model.CinemaVO;
import com.triplemovie.pjt.movie.model.CmtDMI;
import com.triplemovie.pjt.movie.model.CmtVO;
import com.triplemovie.pjt.movie.model.MovieDTO;
import com.triplemovie.pjt.user.model.UserTicketVO;

@Mapper
public interface MovieMapper {
	// ------------------ insert -----------------------------
	
	int insCmt(CmtVO vo);
	void insCinema(CinemaVO param);
	int insFavorite(CmtDMI param);
	int insTicketing(UserTicketVO vo);
	int insSeats(UserTicketVO vo);
	
	// ------------------ update -----------------------------
	void updTotalSeat(UserTicketVO vo);
	// ------------------ select -----------------------------
	
	CinemaVO selRoomTime(CinemaVO param);
	List<CmtDMI> selCmt(MovieDTO param);
	List<CmtVO> selCmtFavorite(MovieDTO param);
	CinemaVO[] selR_dt();
	CinemaVO[] selTime(CinemaVO param);
	CinemaVO[] selRoom(CinemaVO param);
	List<UserTicketVO> selSeats(UserTicketVO vo);
	CinemaVO[] selMovieNm(CinemaVO param);

	// ------------------ delete -----------------------------
	
	int delFavorite(CmtDMI param);


}
