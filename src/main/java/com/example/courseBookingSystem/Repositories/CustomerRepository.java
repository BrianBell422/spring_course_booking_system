package com.example.courseBookingSystem.Repositories;

import com.example.courseBookingSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByBookingsCourseName(String courseName);

    List<Customer> findByTownAndBookingsCourseName(String town, String courseName);

    List<Customer> findByAgeGreaterThanAndTownAndBookingsCourseName(int age, String town, String courseName);
}
