package com.hanbat.zanbanzero.entity.user.User;

import jakarta.persistence.*;

@Entity
public class UserMyPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String coupon;
    private int point;
}
