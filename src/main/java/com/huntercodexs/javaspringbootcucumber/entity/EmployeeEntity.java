package com.huntercodexs.javaspringbootcucumber.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
    @Id
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    public LocalDate dateOfBirth;
    @Column
    public LocalDate startDate;
    @Column
    public LocalDate endDate;
    @Column
    private String employmentType;
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private List<AddressEntity> address;
}
