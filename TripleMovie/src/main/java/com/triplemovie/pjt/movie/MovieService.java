package com.triplemovie.pjt.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplemovie.pjt.Const;
import com.triplemovie.pjt.api.Kmdb;
import com.triplemovie.pjt.api.Kobis;
import com.triplemovie.pjt.api.Youtube;
import com.triplemovie.pjt.api.model.kmdb.KmdbDTO;
import com.triplemovie.pjt.api.model.kmdb.KmdbParam;
import com.triplemovie.pjt.api.model.kobis.KobisDTO;
import com.triplemovie.pjt.movie.model.CinemaVO;
import com.triplemovie.pjt.movie.model.CmtDMI;
import com.triplemovie.pjt.movie.model.CmtVO;
import com.triplemovie.pjt.movie.model.MovieDTO;
import com.triplemovie.pjt.user.model.UserTicketVO;

@Service
public class MovieService {
	
	@Autowired
	private MovieMapper mapper;
	
	// Kmdb 영화 이름으로 검색 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ끝
	public KmdbDTO moiveNmSearch(KmdbParam param) {
		KmdbDTO kdDTO = Kmdb.movieSearch(param);
		for(int i=0; i<kdDTO.getData()[0].getResult().length; i++) {
			String poster = kdDTO.getData()[0].getResult()[i].getPosters();
			if (poster.contains("|")) {
				poster = poster.substring(0, poster.indexOf("|"));
			}			
			kdDTO.getData()[0].getResult()[i].setPosters(poster);
		}
		
		return kdDTO;
	}
	
	// 메인
	public List<KmdbDTO> movieMain(HttpSession hs) {
		List<KmdbDTO> kdDTOList = new ArrayList<KmdbDTO>();
		List<KmdbParam> movieRankList = (List<KmdbParam>) hs.getAttribute(Const.MOVIERANK);

		for (KmdbParam param : movieRankList) {
			KmdbDTO kdDTO = new KmdbDTO();
			try {
				if(kdDTO != null) {
					kdDTO = moiveNmSearch(param);
					kdDTOList.add(kdDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		return kdDTOList;
	}
	
	//영화 날짜
	public CinemaVO[] movieR_dt() {
		return mapper.selR_dt();
	}
	
	//영화 시간
	public CinemaVO[] selTime(CinemaVO param) {
		CinemaVO[] vo = mapper.selTime(param);
		for(CinemaVO var : vo) {
			String sTime = String.format("%02d", (var.getS_dt()/60)) + " : " + String.format("%02d", (var.getS_dt()%60));
			String eTiem = String.format("%02d", (var.getE_dt()/60)) + " : " + String.format("%02d", (var.getE_dt()%60));
			var.setsTime(sTime);
			var.seteTime(eTiem);
		}
		 
		return vo;
	}
	
	//영화 검색 결과 [api]
	public KmdbDTO searchResult(KmdbParam param){
		KmdbDTO kdDTO = new KmdbDTO();		
		kdDTO = moiveNmSearch(param);
		
		return kdDTO;
	}	
	
	
	// 박스오피스 영화 정보 세션에 넣음 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ끝
		public void movieInfo(HttpSession hs) {
			List<KmdbParam> movieList = new ArrayList();
			KobisDTO kDTO = Kobis.boxOffice();
			loopOut:
			for (int i = 0; i < kDTO.getBoxOfficeResult().getDailyBoxOfficeList().length; i++) {
				KmdbParam param = new KmdbParam();
				String movieNm = kDTO.getBoxOfficeResult().getDailyBoxOfficeList()[i].getMovieNm();
				String openDt = kDTO.getBoxOfficeResult().getDailyBoxOfficeList()[i].getOpenDt().substring(0, 4);
				if (!openDt.equalsIgnoreCase("2020")) {
					continue;
				}
				
				
				param.setMovieNm(movieNm);
				
				KmdbDTO kdDTO = Kmdb.movieSearch(param);
				if(kdDTO == null) {
					continue;
				}
				if (kdDTO.getData()[0].getResult() != null) {
					for (int z = 0; z < kdDTO.getData()[0].getResult().length; z++) {
						String repRlsDate = kdDTO.getData()[0].getResult()[z].getRepRlsDate();
						if (repRlsDate.length() < 4 || !openDt.equals(repRlsDate.substring(0, 4))) {
							continue;
						}
						
						movieNm = movieNm.replaceAll("\\p{Z}", "");
						String k_nm = kdDTO.getData()[0].getResult()[z].getTitle();		
						k_nm = k_nm.replaceAll("!HS", "");
						k_nm = k_nm.replaceAll("!HE", "");
						k_nm = k_nm.replaceAll("\\p{Z}", "");						
						if(!movieNm.equals(k_nm)) {
							System.out.println("여기들어옴");
							if(z == kdDTO.getData()[0].getResult().length) {break loopOut;}
							continue;
						}
						String movieSeq = kdDTO.getData()[0].getResult()[z].getMovieSeq();
						String movieId = kdDTO.getData()[0].getResult()[z].getMovieId();
						param.setMovieSeq(movieSeq);
						param.setMovieId(movieId);
						if (movieList.size() < 5) {
							movieList.add(param);
						}
					}
				}

			}
			hs.setAttribute(Const.MOVIERANK, movieList);

		}

	
	//디테일 영화정보
	public KmdbDTO movieDetail(MovieDTO movieDTO) {
		
		//seq값과 id값을 KmdbParam에 넣고  movieSearch로 보내기
		KmdbParam info = new KmdbParam();
		info.setMovieId(movieDTO.getMovieId());
		info.setMovieSeq(movieDTO.getMovieSeq());
		KmdbDTO kdDTO = moiveNmSearch(info);
		
		
		//160번줄에 영화검색해서 가져온 결과값에 이름만 뺴내서 예고편 유튜브 api검색
		String movieNm = kdDTO.getKmaQuery();
		String videocode = null;
		try {
			videocode = Youtube.search(movieNm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if(videocode != null){kdDTO.setVideocode(videocode);}//iframe api를 위한 비디오코드
		kdDTO.setMovieId(movieDTO.getMovieId());
		kdDTO.setMovieSeq(movieDTO.getMovieSeq());
		return kdDTO;
	}
	
	//날짜에 영화등록이 되어있는지 체크
	public CinemaVO selCinema(CinemaVO param) {
		return mapper.selRoomTime(param);
	}
	
	public void insCinema(CinemaVO vo, HttpSession hs) {

		String[] arr = new String[20];
		String[] arrSeq = new String[20];
		String[] arrId = new String[20];
		List<KmdbParam> movieRank = (List<KmdbParam>) hs.getAttribute("movieRank");
		int num = 0;
		int num2 = 0;

		// 영화목록 6,5,4,3,2개씩 List에 넣기
		for (int i = 0; i < 6; i++) {
			if (i == 5) {
				num2 = 2;
			}
			int ran = (int) ((Math.random() * 20) + 1);
			for (int z = 6 - i; z > num2; z--) {
				String movieNm = movieRank.get(i).getMovieNm();
				String movieSeq = movieRank.get(i).getMovieSeq();
				String movieId = movieRank.get(i).getMovieId();
				arr[num] = movieNm;
				arrSeq[num] = movieSeq;
				arrId[num] = movieId;
				num++;
			}
		}
		// -------------------------------------------------------------------------------------------
		// 1~20까지 겹치지 않게 a배열에 담음
		int a[] = new int[20];
		for (int i = 0; i < a.length; i++) {
			int ran = (int) ((Math.random() * 20));
			a[i] = ran;
			for (int z = 0; z < i; z++) {
				if (a[i] == a[z]) {
					i--;
					break;
				}
			}
		}

		// ------------------------------------------------------------------------------------
		// db작업

		int ran = 0;
		for (int i = 0; i < arr.length; i++) {
			if (ran >= 4) {
				ran = 0;
			}
			ran++;
			CinemaVO param = new CinemaVO();
			KmdbParam kmdbParam = new KmdbParam();
			String movieNm = arr[a[i]];
			String movieSeq = arrSeq[a[i]];
			String movieId = arrId[a[i]];
			kmdbParam.setMovieNm(movieNm);

			KmdbDTO kdDTO = moiveNmSearch(kmdbParam);
			int runTime = 0;
			for (int z = 0; z < kdDTO.getData()[0].getResult().length; z++) {
				String getTitle = kdDTO.getData()[0].getResult()[z].getTitleEtc();
				String title = getTitle.substring(0, getTitle.indexOf("^"));
				title = title.replaceAll("\\p{Z}", "");
				movieNm = movieNm.replaceAll("\\p{Z}", "");
				if (title.equals(movieNm)) {
					runTime = Integer.parseInt(kdDTO.getData()[0].getResult()[z].getRuntime());
				}
			}

			param.setR_dt(vo.getR_dt());
			param.setRoom(ran);
			CinemaVO cinemaVo = mapper.selRoomTime(param);
			if (cinemaVo == null) {
				System.out.println("1번");
				param.setS_dt(540);
				int e_dt2 = 540 + runTime;
				param.setE_dt(e_dt2);
			} else if (cinemaVo.getS_dt() + runTime >= 1380) {
				System.out.println("2번");
				i--;
				continue;
			} else {
				System.out.println("3번");
				int s_dt = cinemaVo.getE_dt() + 10;
				int e_dt2 = runTime + s_dt;
				param.setS_dt(s_dt);
				param.setE_dt(e_dt2);
			}
			param.setMovieNm(movieNm);
			param.setMovieId(movieId);
			param.setMovieSeq(movieSeq);
			param.setRoom(ran);
			System.out.println("e_dt: " + param.getE_dt());
			mapper.insCinema(param);

		}
	}

	//댓글쓰기
	public int insCmt(CmtVO vo) { 
		return mapper.insCmt(vo); 
	}

	//댓글불러오기
	public CmtDMI movieSelCmt(MovieDTO param) {
		CmtDMI cmt_combine = new CmtDMI();
		List<CmtDMI> cmt_info = mapper.selCmt(param);
		if(param.getI_user() != 0) {
			List<CmtVO> cmt_favorite = mapper.selCmtFavorite(param);
			cmt_combine.setCmt_favorite(cmt_favorite);
		}
		cmt_combine.setCmt_info(cmt_info);
		return cmt_combine;
	}
	
	//좋아요 취소
	public int delFavorite(CmtDMI param) {
		return mapper.delFavorite(param);
	}
	
	//좋아요 누름
	public int insFavorite(CmtDMI param) {
		return mapper.insFavorite(param);
	}
	
	//이미 예약된 좌석 select
	public List<UserTicketVO> selSeats(UserTicketVO vo) {
		return mapper.selSeats(vo);
	}
	
	//예매하기
	@Transactional
	public int insTicketing(UserTicketVO vo) {
		//총 예매할 좌석수
		int seatCnt = vo.getAdultCnt() + vo.getSeatCnt();
		
		if(seatCnt > 8) {return 0;}
	
		vo.setSeatCnt(seatCnt);
		
		
		//남은좌석 업데이트
		mapper.updTotalSeat(vo);
		
		//영화정보 및 유저 정보  insert
		int i_ticket = mapper.insTicketing(vo);
		int result = 0;
		vo.setI_ticket(i_ticket);
		
		//좌석정보 insert
		for(String seat : vo.getSeats()) {
			vo.setSeat(seat);
			result = mapper.insSeats(vo);
		}
		
		return result;
		
	}

	public CinemaVO[] selMovieNm(CinemaVO param) {
		return mapper.selMovieNm(param);
	}

	public CinemaVO[] selRoom(CinemaVO param) {
		return mapper.selRoom(param);
	}


}
