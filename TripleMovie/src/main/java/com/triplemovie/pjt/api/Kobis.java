package com.triplemovie.pjt.api;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.web.client.RestTemplate;

import com.triplemovie.pjt.api.model.kobis.KobisDTO;
import com.triplemovie.pjt.api.model.kobis.NameToCdDTO;
import com.triplemovie.pjt.api.model.kobis.info.MovieInfoDTO;
import com.triplemovie.pjt.movie.model.MovieDTO;

public class Kobis {
	
	private static String apiKey = "430156241533f1d058c603178cc3ca0e";
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	public static KobisDTO boxOffice() {
		//어제날짜 가져오기
		Date dDate = new Date();
		dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
		SimpleDateFormat dSdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String yesterday = dSdf.format(dDate);
		KobisDTO mDTO = new KobisDTO();
		URI url = URI.create("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="+apiKey+"&targetDt="+yesterday);
		mDTO = restTemplate.getForObject(url,KobisDTO.class);
		return mDTO;
	}
	
	
	
}
