package com.spring.project.request;

import com.spring.project.constant.Role;
import com.spring.project.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class JoinDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String checkPassword;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNum;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .address(this.address)
                .phoneNum(this.phoneNum)
                .role(Role.USER)
                .build();
    }
}
