package com.example.employeesmanagment.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "departamentos")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "codigo", unique = true)
    private String code;
    @NotBlank
    @Column(name = "nombre")
    private String name;
    @Column(name = "fecha_hora_creacion")
    private LocalDateTime creationTime;
    @Column(name = "fecha_hora_actualizacion")
    private LocalDateTime updateTime;
}
