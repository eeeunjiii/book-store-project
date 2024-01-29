package com.spring.project.repository;

import com.spring.project.constant.Role;
import com.spring.project.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemberRepository memberRepository=new MemoryMemberRepository();

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
        Assertions.assertThat(result).isEqualTo(member);
    }

}
