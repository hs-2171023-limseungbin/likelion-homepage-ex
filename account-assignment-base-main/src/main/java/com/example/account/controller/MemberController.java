package com.example.account.controller;

import com.example.account.Service.MemberService;
import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(
            @Valid @RequestBody MemberRegisterDto.Req req){
        ResponseEntity<CustomApiResponse<?>> response = memberService.signUp(req);
        return response;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(
            @Valid @RequestBody MemberLoginDto.Req req){
        ResponseEntity<CustomApiResponse<?>> response = memberService.login(req);
        return response;
    }
}
