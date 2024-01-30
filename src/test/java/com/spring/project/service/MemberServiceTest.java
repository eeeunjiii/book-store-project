package com.spring.project.service;

import com.spring.project.constant.Role;
import com.spring.project.entity.Member;
import com.spring.project.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    @DisplayName("회원가입")
    void join(){
        Member member=new Member();
        member.setName("spring");
        member.setPassword("12345");
        member.setAddress("Incheon");
        member.setEmail("123@naver.com");
        member.setPhone_number("010-0101-1010");
        member.setPoint(0);
        member.setRole(Role.USER);

        memberService.join(member);

        Member result=memberService.findMemberOne(member.getId()).get();
//        System.out.println(member.toString());
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    @DisplayName("중복 회원 예외")
    void duplicationMember(){
        Member member1=new Member();
        member1.setName("spring");
        member1.setPassword("12345");
        member1.setAddress("Incheon");
        member1.setEmail("123@naver.com");
        member1.setPhone_number("010-0101-1010");
        member1.setPoint(0);
        member1.setRole(Role.USER);

        memberService.join(member1);

        Member member2=new Member();
        member2.setName("spring");
        member2.setPassword("12345");
        member2.setAddress("Incheon");
        member2.setEmail("123@naver.com");
        member2.setPhone_number("010-0101-1010");
        member2.setPoint(0);
        member2.setRole(Role.USER);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("전체 회원 조회")
    void findMemberList(){
        Member member1=new Member();
        member1.setName("spring1");
        member1.setPassword("123456");
        member1.setAddress("Incheon");
        member1.setEmail("123@naver.com");
        member1.setPhone_number("010-0101-1010");
        member1.setPoint(0);
        member1.setRole(Role.USER);
        memberService.join(member1);

        Member member2=new Member();
        member2.setName("spring2");
        member2.setPassword("12345");
        member2.setAddress("Incheon");
        member2.setEmail("123@daum.com");
        member2.setPhone_number("012-0101-1010");
        member2.setPoint(0);
        member2.setRole(Role.ADMIN);
        memberService.join(member2);

        List<Member> memberList = memberService.findMemberList();
        Assertions.assertThat(memberList.contains(member1)).isTrue();
        Assertions.assertThat(memberList.contains(member2)).isTrue();
    }

    @Test
    @DisplayName("회원 조회")
    void findMemberOne(){
        Member member=new Member();
        member.setName("spring");
        member.setPassword("12345");
        member.setAddress("Incheon");
        member.setEmail("123@naver.com");
        member.setPhone_number("010-0101-1010");
        member.setPoint(0);
        member.setRole(Role.USER);
        memberService.join(member);

        Member findMember = memberService.findMemberOne(member.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }
}
