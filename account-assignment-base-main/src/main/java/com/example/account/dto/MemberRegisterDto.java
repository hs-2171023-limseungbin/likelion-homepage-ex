package com.example.account.dto;

import com.example.account.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

public class MemberRegisterDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{

        @NotBlank(message = "아이디를 입력하세요")
        //@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문자와 숫자만 포함할 수 있습니다")
        private String userId;

        @NotBlank(message = "비밀번호를 작성하세요")
        private String password;

        @NotBlank(message = "이메일을 작성해주세요")
        @Email(message = "이메일 형식으로 작성하세요")
        private String email;

        @NotBlank(message = "휴대폰 번호를 입력하세요")
        //@Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호 형식을 맞춰주세요")
        private String phone;

        public Member toEntity(){
            return Member.builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .phone(phone)
                    .build();
        }

        // 회원 가입: id, createdAt
        @Getter
        @Builder
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class SignUp{
            private Long Id;
            private LocalDateTime createdAt;

            public SignUp(Long Id, LocalDateTime createdAt) {
                this.Id = Id;
                this.createdAt = createdAt;
            }
        }
    }
}
