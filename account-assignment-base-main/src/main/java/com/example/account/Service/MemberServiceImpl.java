package com.example.account.Service;

import com.example.account.Repository.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
}
