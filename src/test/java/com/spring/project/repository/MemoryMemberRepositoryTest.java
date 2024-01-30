package com.spring.project.repository;

import com.spring.project.constant.Role;
import com.spring.project.entity.Member;
import com.spring.project.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository=new MemoryMemberRepository();

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void save(){
        Member member=new Member();
        member.setName("spring");
        member.setPassword("12345");
        member.setAddress("Incheon");
        member.setEmail("123@naver.com");
        member.setPhone_number("010-0101-1010");
        member.setPoint(0);
        member.setRole(Role.USER);

        memberRepository.save(member);

        Member result=memberRepository.findByEmail(member.getEmail()).get();
//        System.out.println(member.toString());
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    void findByEmail(){
        Member member1=new Member();
        member1.setName("spring1");
        member1.setPassword("12345");
        member1.setAddress("Incheon");
        member1.setEmail("123@naver.com");
        member1.setPhone_number("010-0101-1010");
        member1.setPoint(0);
        member1.setRole(Role.USER);
        memberRepository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        member2.setPassword("123456");
        member2.setAddress("Incheon");
        member2.setEmail("123@daum.com");
        member2.setPhone_number("012-0101-1010");
        member2.setPoint(0);
        member2.setRole(Role.ADMIN);
        memberRepository.save(member2);

        Member findMember = memberRepository.findByEmail("123@daum.com").get();

        Assertions.assertThat(findMember).isEqualTo(member2);
    }

    @Test
    void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        member1.setPassword("12345");
        member1.setAddress("Incheon");
        member1.setEmail("123@naver.com");
        member1.setPhone_number("010-0101-1010");
        member1.setPoint(0);
        member1.setRole(Role.USER);
        memberRepository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        member2.setPassword("123456");
        member2.setAddress("Incheon");
        member2.setEmail("123@daum.com");
        member2.setPhone_number("012-0101-1010");
        member2.setPoint(0);
        member2.setRole(Role.ADMIN);
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();
        Assertions.assertThat(memberList.size()).isEqualTo(2);
    }
}
