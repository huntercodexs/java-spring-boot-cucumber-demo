package com.huntercodexs.cucumberdemo.employee.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressDTO {
    @NotNull
    private Long id;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String number;
    private String state;
}