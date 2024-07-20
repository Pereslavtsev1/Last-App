package org.example.customerservice.services;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.example.customerservice.dtos.CustomerRequest;
import org.example.customerservice.exceptions.CustomerNotFoundException;
import org.example.customerservice.mappers.CustomerMapper;
import org.example.customerservice.models.Customer;
import org.example.customerservice.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.customerservice.dtos.CustomerResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest customer) {
        return customerRepository.save(customerMapper.toCustomerWithOutId(customer)).getId();
    }

    public String updateCustomer(CustomerRequest customer) {
        var opCustomer = customerRepository.findById(customer.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id '%s' not found",customer.id()), HttpStatus.BAD_REQUEST));
        mergeCustomer(opCustomer,customer);
        return customerRepository.save(opCustomer).getId();
    }
    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstName(request.firstname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();

    }

    public Boolean existCustomer(String id) {
        return customerRepository.existsById(id);
    }
    public CustomerResponse findCustomer(String id) {
        return customerRepository.findById(id).map(customerMapper::toCustomerResponse).orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with id '%s' not found",id), HttpStatus.BAD_REQUEST)
        );
    }

    public void deleteCustomer(String customerId) {
        var customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer with id '%s' not found",customerId), HttpStatus.BAD_REQUEST);
        }
        customerRepository.deleteById(customerId);
    }
}
