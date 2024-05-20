package com.tcellpair3.customerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "customer_invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInvoice  extends  BaseEntity{

    @Column(name = "account_name")
    private int accountName;

    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "account_type")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
