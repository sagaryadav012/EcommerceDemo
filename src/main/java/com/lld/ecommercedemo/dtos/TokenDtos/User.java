package com.lld.ecommercedemo.dtos.TokenDtos;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
}
