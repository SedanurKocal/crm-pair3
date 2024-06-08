package com.tcellpair3.orderservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customer_invoice_id")
    private int customerInvoiceId;
    @Column(name = "order_items")
    private List<String> orderItems;
    @Column(name = "service_addresses")
    private List<String> serviceAddresses;
    @Column(name = "total_amount")
    private double totalAmount;

}
