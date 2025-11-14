package com.bank.authorization.dto;

import com.bank.authorization.constants.MessageEnum;
import com.bank.authorization.constants.OperationEnum;
import com.bank.authorization.constants.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaMessage {

    private String correlationId;
    private UserDto userData;
    private OperationEnum operation;
    private Long id;
    private Long profileId;
    private String token;
    private Boolean valid;
    private MessageEnum message;
    private String password;
    private RoleEnum role;
}
