package com.example.account.controller;

import com.example.account.service.MemberService;
import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(
            @Valid @RequestBody MemberRegisterDto.Req req){
        return memberService.signUp(req);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(
            @Valid @RequestBody MemberLoginDto.Req req){
        return memberService.login(req);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<CustomApiResponse<?>> logout(){
        return memberService.logout();
    }

    // 회원탈퇴
    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> deleteAccount() {
        return memberService.deleteAccount();
    }
}
