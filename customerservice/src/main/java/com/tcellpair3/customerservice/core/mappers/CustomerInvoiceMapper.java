package com.tcellpair3.customerservice.core.mappers;

import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetAllCustomersResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetByIdCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.SearchResultsResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithCustomer;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.GetAllCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.GetByIdCustomerInvoiceResponse;
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

    GetByIdCustomerInvoiceResponse getByIdCustomerInvoiceMapper(CustomerInvoice customerInvoice);

    GetAllCustomerInvoiceResponse getAllCustomerInvoiceMapper(CustomerInvoice customerInvoice);
    List<GetAllCustomerInvoiceResponse> customerInvoiceToListCustomerInvoiceResponses(List<CustomerInvoice> customerInvoices);

    @Mapping(target = "customer.id", source = "customerId")
    CustomerInvoice updateCustomerInvoiceMapper(UpdateCustomerInvoiceRequest customerRequest, @MappingTarget CustomerInvoice customerInvoice);

    @Mapping(target = "customerId", source = "customer.id")
    CustomerInvoiceWithCustomer customerInvoiceWithCustomer(CustomerInvoice customerInvoice);

    List<CustomerInvoiceWithCustomer> customerInvoiceWithCustomer(List<CustomerInvoice> customerInvoices);
}
