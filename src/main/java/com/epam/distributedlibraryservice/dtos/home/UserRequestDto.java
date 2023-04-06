package com.epam.distributedlibraryservice.dtos.home;

import com.epam.distributedlibraryservice.constants.ErrorCode;
import com.epam.distributedlibraryservice.validators.UniqueUsername;
import com.epam.distributedlibraryservice.validators.ValidateClassExpression;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@ToString
@ValidateClassExpression(value = "(#this.password.equals(#this.confirmPassword))", message = ErrorCode.Password.MISMATCH, actionMessage = "This field need to be equals with {dependentFields}", appliedFields = {
        "confirmPassword"}, dependentFields = {"password"})
public class UserRequestDto {

    @NotBlank
    @Size(min = 3, max = 100)
    @UniqueUsername
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 100)
    private String password;

    private String confirmPassword;

}
