package com.example.account.service;

import com.example.account.repository.MemberRepository;
import com.example.account.domain.Member;
import com.example.account.dto.MemberLoginDto;
import com.example.account.dto.MemberRegisterDto;
import com.example.account.util.response.CustomApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    // 회원가입
    @Override
    public ResponseEntity<CustomApiResponse<?>> signUp(MemberRegisterDto.Req req) {
        Member member = req.toEntity();
        Member savedMember = memberRepository.save(member);
        CustomApiResponse<String> res = CustomApiResponse.createSuccess(HttpStatus.CREATED.value(),
                null, "회원가입에 성공하셨습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // 로그인
    @Override
    public ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto.Req req) {
        // 사용자 ID로 회원 조회(회원 정보 확인)
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElse(null);

        // 회원 여부 확인
        if (member == null) {
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        // 비밀번호 일치 확인
        if (!req.getPassword().equals(member.getPassword())) {
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value(), "비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        // 로그인 성공
        session.setAttribute("loginUser", member);
        CustomApiResponse<String> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다.");
        return ResponseEntity.ok(res);
    }

    // 로그아웃
    @Override
    public ResponseEntity<CustomApiResponse<?>> logout() {
        session.invalidate();
        CustomApiResponse<String> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그아웃에 성공하였습니다.");
        return ResponseEntity.ok(res);
    }

    // 회원탈퇴
    @Override
    public ResponseEntity<CustomApiResponse<?>> deleteAccount() {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        Member member = memberRepository.findByUserId(loginUser.getUserId()).orElse(null);
        if (member == null) {
            CustomApiResponse<String> res = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "id가 " + loginUser.getUserId() + "인 회원은 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        memberRepository.deleteById(member.getId());
        session.invalidate();

        CustomApiResponse<String> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원탈퇴에 성공하였습니다.");
        return ResponseEntity.ok(res);
    }
}
