package com.lld.ecommercedemo.dtos.TokenDtos;

import lombok.Data;

import java.util.Date;

@Data
public class Token {
    private int id;
    private String value;
    private User user;
    private Date expiresAt;
    private boolean isActive;
}
