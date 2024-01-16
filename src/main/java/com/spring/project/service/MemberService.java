package com.spring.project.service;

import com.spring.project.entity.Member;
import com.spring.project.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


}
