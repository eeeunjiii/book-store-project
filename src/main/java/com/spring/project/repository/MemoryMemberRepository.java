package com.spring.project.repository;

import com.spring.project.dto.MemberDto;
import com.spring.project.entity.Member;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{
    private static Long id=0L;

    @Override
    public Member save(Member member) {
        
        return null;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
