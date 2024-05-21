package com.example.employeesmanagment.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "empleados")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "documento_tipo")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @NotNull
    @Positive
    @Column(name = "documento_numero", unique = true)
    private Long documentNumber;
    @NotBlank
    @Column(name = "nombres")
    private String names;
    @NotBlank
    @Column(name = "apellidos")
    private String lastNames;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departamentos_id")
    private Department department;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "direccion")
    private String address;
    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "no cumple con el formato correcto")
    @Column(name = "correo_electronico")
    private String email;
    @NotBlank
    @Column(name = "telefono")
    private String telephoneNumber;
    @Column(name = "fecha_hora_creacion")
    private LocalDateTime creationTime;
    @Column(name = "fecha_hora_actualizacion")
    private LocalDateTime updateTime;
}
