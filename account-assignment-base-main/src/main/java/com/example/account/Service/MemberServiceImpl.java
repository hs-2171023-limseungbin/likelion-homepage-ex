package com.example.account.Service;

import com.example.account.Repository.MemberRepository;
import com.example.account.domain.Member;
import com.example.account.dto.MemberLoginDto;
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
                savedMember.getCreatedAt());
        CustomApiResponse<MemberRegisterDto.SignUp> res = CustomApiResponse.createSuccess(HttpStatus.CREATED.value(),
                SignUpRes, "회원가입에 성공하셨습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req) {

        //사용자 ID로 회원 조회(회원 정보 확인)
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElse(null);

        //회원 여부 확인
        if(member == null){
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value()
                    , "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        //비밀번호 일치 확인
        if(!req.getPassword().equals(member.getPassword())){
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value()
                    , "비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        //로그인 성공
        CustomApiResponse<String> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),
                null ,"로그인에 성공하였습니다.");
        return ResponseEntity.ok(res);
    }
}
