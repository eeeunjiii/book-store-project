package com.spring.project.dto;

import com.spring.project.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String address;
    private String phone_number;
    private int point;

    @Builder
    public MemberDto(Long id, String email, String name, String password, String address, String phone_number, int point) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
        this.point = point;
    }

    /*public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .address(address)
                .phone_number(phone_number)
                .build();
    }*/
}
