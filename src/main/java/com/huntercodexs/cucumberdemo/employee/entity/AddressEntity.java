package com.huntercodexs.cucumberdemo.employee.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "address")
public class AddressEntity implements Serializable {
    @Id
    private Long id;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String number;
    @Column
    private String state;
}
