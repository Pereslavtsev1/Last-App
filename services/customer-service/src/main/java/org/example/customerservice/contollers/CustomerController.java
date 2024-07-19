package org.example.customerservice.contollers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.customerservice.dtos.CustomerRequest;
import org.example.customerservice.dtos.CustomerResponse;
import org.example.customerservice.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequest customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }
    @PutMapping
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody CustomerRequest customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> existCustomer(@PathVariable String id) {
        return ResponseEntity.ok(customerService.existCustomer(id));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findCustomer(customerId));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
