package com.example.account.controller;

import com.example.account.Service.MemberService;
import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session;

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
        if(response.getStatusCode().is2xxSuccessful()){
            session.setAttribute("loginUser", req.getUserId());
        }
        return response;
    }

    //회원탈퇴
    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> deleteAccount() {
        String userId = (String) session.getAttribute("loginUser");
        if(userId == null){
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(401, "로그인이 필요합니다.");
            return ResponseEntity.status(401).body(res);
        }
        return memberService.deleteAccount(userId);
    }
}