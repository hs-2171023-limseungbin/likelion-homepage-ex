package com.example.account.service;

import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    // 회원가입
    ResponseEntity<CustomApiResponse<?>> signUp(MemberRegisterDto.Req req);

    // 로그인
    ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req);

    // 로그아웃
    ResponseEntity<CustomApiResponse<?>> logout();

    // 회원탈퇴
    ResponseEntity<CustomApiResponse<?>> deleteAccount();
}
