package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private String userId;               //유저 아이디
	private String userPassword;         //유저 패스워드
	private String userName;             //유저 이름
	private List<History> histories;     //유저 개인의 검색 히스토리
	


}
