package com.spring.project.service;

import com.spring.project.entity.Member;
import com.spring.project.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member){
        validateDuplicationMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member){
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMemberList(){
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
