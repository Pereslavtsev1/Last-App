package org.example.customerservice.repositories;


import org.example.customerservice.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    void deleteById(String id);
}
