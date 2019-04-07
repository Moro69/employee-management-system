package com.moro.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationModel {

    @NotNull
    @NotEmpty
    @Size(max = 45)
    private String name;

    @NotNull
    @NotEmpty
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Size(max = 128)
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 128)
    private String password;
}
