package com.tcellpair3.customerservice.core.mappers;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.*;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerInvoiceMapper {

    CustomerInvoiceMapper INSTANCE = Mappers.getMapper(CustomerInvoiceMapper.class);

    @Mapping(target = "customer.id",source = "customerId")
    CustomerInvoice createCustomerInvoiceMapper(CreateCustomerInvoiceRequest request);

    @Mapping(target = "customerId",source = "customer.id")
    GetCustomerInvoiceByIdResponse getByIdCustomerInvoiceMapper(CustomerInvoice customerInvoice);
    @Mapping(target = "customerId",source = "customer.id")
    GetAllCustomerInvoiceResponse getAllCustomerInvoiceMapper(CustomerInvoice customerInvoice);

    List<GetAllCustomerInvoiceResponse> customerInvoiceToListCustomerInvoiceResponses(List<CustomerInvoice> customerInvoices);

    @Mapping(target = "customer.id", source = "customerId")
    CustomerInvoice updateCustomerInvoiceMapper(UpdateCustomerInvoiceRequest customerRequest, @MappingTarget CustomerInvoice customerInvoice);

    @Mapping(target = "customerId", source = "customer.id")
    CustomerInvoiceWithCustomerResponse customerInvoiceWithCustomer(CustomerInvoice customerInvoice);

    List<CustomerInvoiceWithCustomerResponse> customerInvoiceWithCustomer(List<CustomerInvoice> customerInvoices);

    @Mapping(target = "customerId", source = "customer.id")
    CustomerInvoiceWithAddressResponse toCustomerInvoiceWithAddressesResponse(CustomerInvoice customerInvoice);
}
