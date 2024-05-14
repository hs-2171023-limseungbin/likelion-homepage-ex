package com.example.account.Service;

import com.example.account.Repository.MemberRepository;
import com.example.account.domain.Member;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(MemberRegisterDto.Req req){
        Member member = req.toEntity();

        Member savedMember = memberRepository.save(member);

        MemberRegisterDto.SignUp SignUpRes = new MemberRegisterDto.SignUp(savedMember.getId(),
                savedMember.getUpdatedAt());
        CustomApiResponse<MemberRegisterDto.SignUp> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),
                SignUpRes, "회원가입에 성공하셨습니다.");
        return ResponseEntity.ok(res);
    }
}
