package com.tcellpair3.orderservice.entities;

import com.tcellpair3.orderservice.core.entities.BaseEntity;
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

public class Order extends BaseEntity {

    @Column(name = "customer_invoice_id")
    private int customerInvoiceId;
    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "order_item")
    private List<String> orderItems;

    @ElementCollection
    @CollectionTable(name = "service_addresses", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "service_address")
    private List<String> serviceAddresses;
    @Column(name = "total_amount")
    private double totalAmount;
}
