package com.tcellpair3.customerservice.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseCustomer extends BaseEntity {
    @Column(name = "account_number")
    private int accountNumber;

}
