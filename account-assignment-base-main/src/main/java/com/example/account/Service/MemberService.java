package com.example.account.Service;

import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signUp(MemberRegisterDto.Req req);
}
