package com.spring.project.entity;

import com.spring.project.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private String address;
    private String phoneNum;
    private int point;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    private List<Order> orders=new ArrayList<>();

    @Builder
    public User(Long id, String email, String name, String password, String address,
                String phoneNum, int point, Role role){
        this.id=id;
        this.email=email;
        this.name=name;
        this.password=password;
        this.address=address;
        this.phoneNum = phoneNum;
        this.point=point;
        this.role=role;
    }
}
