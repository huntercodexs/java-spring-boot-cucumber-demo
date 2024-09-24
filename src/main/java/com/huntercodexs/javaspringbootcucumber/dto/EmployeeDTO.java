package com.huntercodexs.javaspringbootcucumber.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class EmployeeDTO {
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    public LocalDate dateOfBirth;

    @NotNull
    public LocalDate startDate;

    public LocalDate endDate;

    @NotEmpty
    private String employmentType;

    @NotEmpty
    private String email;

    @NotNull
    private List<AddressDTO> address;
}
