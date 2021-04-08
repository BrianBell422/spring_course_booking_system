package com.example.courseBookingSystem.Controllers;

import com.example.courseBookingSystem.Models.Customer;
import com.example.courseBookingSystem.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(required = false, name = "courseName") String courseName
    ) {
        if (courseName != null) {
            return new ResponseEntity(customerRepository.findByBookingsCourseName(courseName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/customers/courses")
    public ResponseEntity<List<Customer>> getAllCustomersAndFilter(
            @RequestParam(required = false, name = "courseName") String courseName,
            @RequestParam(required = false, name = "town") String town,
            @RequestParam(required = false, name = "age") Integer age
    ) {
        if (age != null && courseName != null && town != null) {
            return new ResponseEntity(customerRepository.findByTownAndBookingsCourseName(town, courseName), HttpStatus.OK);
        }
        if (courseName != null && town != null) {
            return new ResponseEntity(customerRepository.findByAgeGreaterThanAndTownAndBookingsCourseName(age, town, courseName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id){
        return new ResponseEntity(customerRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
