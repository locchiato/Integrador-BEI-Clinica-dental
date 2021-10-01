package com.dh.clinicadental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Roles")
@Setter
@Getter
public class Rol {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
