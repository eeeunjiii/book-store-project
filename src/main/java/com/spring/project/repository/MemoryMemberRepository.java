package com.spring.project.repository;

import com.spring.project.dto.MemberDto;
import com.spring.project.entity.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Long id=0L;
    private static Map<Long, Member> store=new HashMap<>();

    @Override
    public Member save(Member member) {
        member.setId(++id);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {store.clear();}
}
