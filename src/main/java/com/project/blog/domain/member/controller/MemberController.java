package com.project.blog.domain.member.controller;

import com.project.blog.domain.email.service.EmailService;
import com.project.blog.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("join")
    public String join (@Valid JoinForm joinForm) {

        memberService.join(joinForm.getUsername(), joinForm.getNickname(), joinForm.getPassword(), joinForm.email);

        emailService.send(joinForm.email, "서비스 가입을 환영합니다!", "회원가입 환영 메일");

        return "redirect:/member/login";
    }


    @Getter
    @ToString
    @AllArgsConstructor
    public static class JoinForm {
        @NotBlank
        @Length(min = 4)
        private String username;
        @NotBlank
        @Length(min = 4)
        private String nickname;
        @NotBlank
        @Length(min = 4)
        private String password;
        @NotBlank
        @Length(min = 4)
        private String password_confirm;
        @NotBlank
        @Length(min = 4 )
        private String email;
    }

}
