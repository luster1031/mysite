package com.poscoict.mysite.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.UserRepository;
import com.poscoict.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
	}

	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVo getUser(Long userNO) {
		return userRepository.fidByNo(userNO);
	}

	public void updateUser(UserVo userVo) {
		userRepository.updateByNo(userVo);
	}
}
