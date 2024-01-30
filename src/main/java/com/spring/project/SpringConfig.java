package com.spring.project;

import com.spring.project.repository.JpaMemberRepository;
import com.spring.project.repository.MemberRepository;
import com.spring.project.repository.MemoryMemberRepository;
import com.spring.project.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
}
