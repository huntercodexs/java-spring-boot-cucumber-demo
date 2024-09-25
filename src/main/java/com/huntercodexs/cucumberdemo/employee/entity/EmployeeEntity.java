package com.huntercodexs.cucumberdemo.employee.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private List<AddressEntity> addresses;
}
