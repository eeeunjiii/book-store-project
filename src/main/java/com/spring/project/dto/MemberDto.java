package com.spring.project.dto;

import com.spring.project.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private String email;
    private String name;
    private String password;
    private String address;
    private String phone_number;
    private int point;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .address(address)
                .phone_number(phone_number)
                .build();
    }
}
