package com.spring.project.entity;

import com.spring.project.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private String address;
    private String phone_number;
    private int point;
    private Role role;

    @OneToMany
    private List<Order> orders=new ArrayList<>(); // Order와의 양방향 매핑을 위해 추가

    @OneToOne(mappedBy = "member")
    private Cart cart;

    @Builder
    public Member(String email, String name, String password, String address,
                  String phone_number, int point, Role role){
        this.email=email;
        this.name=name;
        this.password=password;
        this.address=address;
        this.phone_number=phone_number;
        this.point=point;
        this.role=role;
    }
}
