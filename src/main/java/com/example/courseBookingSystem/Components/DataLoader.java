package com.example.courseBookingSystem.Components;

import com.example.courseBookingSystem.Models.Booking;
import com.example.courseBookingSystem.Models.Course;
import com.example.courseBookingSystem.Models.Customer;
import com.example.courseBookingSystem.Repositories.BookingRepository;
import com.example.courseBookingSystem.Repositories.CourseRepository;
import com.example.courseBookingSystem.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CustomerRepository customerRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args) {
        Course course1 = new Course("Software Development", "Inverness", 5);
        courseRepository.save(course1);

        Course course2 = new Course("Data Analysis", "Edinburgh", 4);
        courseRepository.save(course2);

        Customer customer1 = new Customer("Brian", "Beauly", 33);
        customerRepository.save(customer1);

        Customer customer2 = new Customer("Bob", "Glasgow", 30);
        customerRepository.save(customer2);

        Booking booking1 = new Booking("23.04.21", course1, customer1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking("20.07.21", course2, customer2);
        bookingRepository.save(booking2);

        course1.addBooking(booking1);
        courseRepository.save(course1);

        course2.addBooking(booking2);
        courseRepository.save(course2);

        customer1.addBooking(booking1);
        customerRepository.save(customer1);

        customer2.addBooking(booking2);
        customerRepository.save(customer2);
    }
}
