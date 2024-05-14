package com.tcellpair3.customerservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_medium")
public class ContactMedium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @NotBlank(message = "This field is required")
    private String email;

    @NotBlank
    @Column(name = "mobile_phone")
    @Size(max = 11,min = 11,message = "Telefon numarası formatında başında 0 olacak şekilde 11 haneli veri girişi yapınız")
    private String mobilePhone;

    @Column(name = "home_phone")
    @Size(max = 11,min = 11,message = "Ev telefonu formatında başında 0 olacak şekilde 11 haneli veri girişi yapınız")
    private String homePhone;

    @Column(name = "fax_number")
    @Size(max = 12,min = 12,message = "Fax numarası formatında 12 haneli veri girişi yapınız")
    private String faxNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;
}
