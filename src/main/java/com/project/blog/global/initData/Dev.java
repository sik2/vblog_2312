package com.project.blog.global.initData;

import com.project.blog.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class Dev {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner init(MemberService memberService) {
        return args -> {
            memberService.join("admin", passwordEncoder.encode("1234"), "admin", "");
            memberService.join("member1", passwordEncoder.encode("1234"), "member1", "member1@test.com");
            memberService.join("member2", passwordEncoder.encode("1234"), "member2", "member2@test.com");
        };
    }
}
