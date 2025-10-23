package com.bank.authorization.dto;

import com.bank.authorization.constants.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;
    private Long profileId;
    private String password;
    private RoleEnum role;
}
