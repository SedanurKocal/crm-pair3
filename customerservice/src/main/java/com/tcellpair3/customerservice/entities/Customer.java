package com.tcellpair3.customerservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    //TO DO : move to dto
    //TO DO : check MERNIS for nationalID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "This field is required") //TO DO : move to dto
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "This field is required")
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String lastName;

    @Column(name = "middle_name")
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String middleName;

    @Column(name = "national_id" )
    @NotBlank(message = "This field is required")
    private String nationalId;

    @Column(name = "mother_name" )
    private String motherName;

    @Column(name = "father_name" )
    private String fatherName;

    @Column(name = "birth_date")
    @NotBlank(message = "This field is required")
    private LocalDate birthdate;

    @Column(name = "gender")
    @NotBlank(message = "This field is required")
    private Gender gender;

    @OneToMany(mappedBy = "customer")
    private List<Address> Addresses;

    @OneToOne(mappedBy ="customer")
    private ContactMedium contactMedium;
}

//MOLA :20:35

