package com.tcellpair3.customerservice.entities;

import com.tcellpair3.customerservice.core.entities.BaseEntity;
import jakarta.persistence.*;
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
public class ContactMedium extends BaseEntity {

    private String email;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "fax_number")
    private String faxNumber;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
