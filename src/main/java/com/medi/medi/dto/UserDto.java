package com.medi.medi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @Email(message = "incorrect email")
    @NotBlank(message = "email should not be blank")
    private String email;
    @NotBlank(message = "name should not be blank")
    private String name;
}
