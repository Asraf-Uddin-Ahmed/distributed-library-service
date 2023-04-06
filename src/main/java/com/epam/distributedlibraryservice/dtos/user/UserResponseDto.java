package com.epam.distributedlibraryservice.dtos.user;

import lombok.Data;

@Data
public class UserResponseDto {
    // DTO for returning user data
    private Integer id;
    private String username;
    private String email;
    private String name;
}
