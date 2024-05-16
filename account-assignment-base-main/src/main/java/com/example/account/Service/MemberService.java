package com.example.account.Service;

import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    //회원가입
    ResponseEntity<CustomApiResponse<?>> signUp(MemberRegisterDto.Req req);

    //로그인
    ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req);

    //회원탈퇴
    ResponseEntity<CustomApiResponse<?>> deleteAccount(String userId);
}