package com.spring.project.request;

import com.spring.project.constant.Payment;
import com.spring.project.constant.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfoDto {

    @NotBlank
    private String customer;

    private String memo;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String destination;

    private int point;

    @ValidEnum(enumClass=Payment.class)
    private Payment payment;
}
