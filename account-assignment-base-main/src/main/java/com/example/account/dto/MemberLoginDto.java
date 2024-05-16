package com.example.account.dto;

import com.example.account.domain.Member;
import lombok.*;

public class MemberLoginDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        private String userId;
        private String password;

        public Member toEntity() {
            return Member.builder()
                    .userId(userId)
                    .password(password)
                    .build();
        }
    }
}
