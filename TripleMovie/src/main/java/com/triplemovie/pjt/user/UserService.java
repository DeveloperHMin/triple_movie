package com.triplemovie.pjt.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triplemovie.pjt.SecurityUtils;
import com.triplemovie.pjt.user.model.UserTicketVO;
import com.triplemovie.pjt.user.model.UserVO;


@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public int login(UserVO param) {
		if(param.getUser_id().equals("")) { return 2; }
		
		UserVO dbUser = mapper.selUser(param);		
		if(dbUser == null) { return 2; }
		
		String cryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), dbUser.getSalt());
		if(!cryptPw.equals(dbUser.getUser_pw())) { return 3; }
		
		param.setI_user(dbUser.getI_user());
		param.setUser_pw(null);
		param.setUser_id(dbUser.getUser_id());
		param.setNick_nm(dbUser.getNick_nm());
		param.setAge(dbUser.getAge());
		param.setGender(dbUser.getGender());
		return 1;		
	}
	
	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String cryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setSalt(salt);
		param.setUser_pw(cryptPw);
		
		return mapper.insUser(param);
	}
	
	public int chkNick(UserVO param) {
		UserVO dbUser = mapper.selUser(param);
		if(dbUser == null) { return 2; }
		return 3;
	}
	
	public List<UserTicketVO> selTicketInfo(int i_user) {
		List<UserTicketVO> param = mapper.selTicketInfo(i_user);
		for(UserTicketVO vo : param) {
			List<String> seat = mapper.selSeatsInfo(vo.getI_ticket());
			String[] seats = new String[seat.size()];
			String[] arrRows = new String[seat.size()];
			String row = null;
			for(int i=0; i<seat.size(); i++) {
				String seatReplace = seat.get(i);
				String oldStr = "seat" + seatReplace.charAt(4);
				String reStr = seatReplace.substring(4,5);
				seatReplace = seatReplace.replace(oldStr, reStr);
				seatReplace = seatReplace.replace(seatReplace.substring(0,1), seatReplace.substring(0,1) + "열");
				seatReplace = seatReplace.replace(seatReplace.substring(2,3), seatReplace.substring(2,3));
				seats[i] = seatReplace;
				if(row == null || !row.equals(reStr)) {
					row = reStr;
					arrRows[i] = reStr;
				}
			}		
			String s_dt = String.format("%02d", (vo.getS_dt()/60)) + " : " + String.format("%02d", (vo.getS_dt()%60));
			String e_dt = String.format("%02d", (vo.getE_dt()/60)) + " : " + String.format("%02d", (vo.getE_dt()%60));
			
			vo.setS_Time(s_dt);
			vo.setE_Time(e_dt);
			vo.setSeats(seats);
			vo.setRows(arrRows);
		}
		return param;
	}
	
	@Transactional
	public int delTicket(UserTicketVO param) {
		mapper.delSeats(param);
		return mapper.delTicket(param);
	}
}
