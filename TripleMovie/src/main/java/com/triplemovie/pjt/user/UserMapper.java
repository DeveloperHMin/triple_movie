package com.triplemovie.pjt.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triplemovie.pjt.user.model.UserTicketVO;
import com.triplemovie.pjt.user.model.UserVO;

@Mapper
public interface UserMapper {
	UserVO selUser(UserVO param);
	int insUser(UserVO param);
	List<UserTicketVO> selTicketInfo(int i_user);
	List<String> selSeatsInfo(int i_ticket);
	int delSeats(UserTicketVO param);
	int delTicket(UserTicketVO param);

}
