package com.example.courseBookingSystem;

import com.example.courseBookingSystem.Models.Booking;
import com.example.courseBookingSystem.Models.Course;
import com.example.courseBookingSystem.Models.Customer;
import com.example.courseBookingSystem.Repositories.BookingRepository;
import com.example.courseBookingSystem.Repositories.CourseRepository;
import com.example.courseBookingSystem.Repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CourseBookingSystemApplicationTests {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createBookingCourseAndCustomer() {
		Course course = new Course("Software Development", "Inverness", 5);
		courseRepository.save(course);

		Customer customer = new Customer("Brian", "Beauly", 33);
		customerRepository.save(customer);

		Booking booking = new Booking("23.15.21", course, customer);
		bookingRepository.save(booking);
	}

	@Test
	public void addBookingToCustomer() {
		Course course = new Course("Software Development", "Inverness", 5);
		courseRepository.save(course);

		Customer customer = new Customer("Brian", "Beauly", 33);
		customerRepository.save(customer);

		Booking booking = new Booking("23.15.21", course, customer);
		bookingRepository.save(booking);

		customer.addBooking(booking);
		customerRepository.save(customer);
	}

	@Test
	public void findAllCoursesByRating() {
		List<Course> foundCourses = courseRepository.findByRating(5);
		assertEquals("Software Development", foundCourses.get(0).getName());
	}

	@Test
	public void findAllCustomersByCourse() {
		List<Customer> foundCustomers = customerRepository.findByBookingsCourseName("Software Development");
		assertEquals("Brian", foundCustomers.get(0).getName());
	}

	@Test
	public void findAllCoursesByCustomer() {
		List<Course> foundCourses = courseRepository.findByBookingsCustomerName("Bob");
		assertEquals("Data Analysis", foundCourses.get(0).getName());
	}

	@Test
	public void findAllBookingsByDate() {
		List<Booking> foundBookings = bookingRepository.findByDate("23.04.21");
		assertEquals("Software Development", foundBookings.get(0).getCourse().getName());
	}

	@Test
	public void findAllCustomersByTownAndCourse() {
		List<Customer> foundCustomers = customerRepository.findByTownAndBookingsCourseName("Beauly", "Software Development");
		assertEquals("Brian", foundCustomers.get(0).getName());
	}

	@Test
	public void findAllCustomersOverAgeByTownAndCourse() {
		List<Customer> foundCustomers = customerRepository.findByAgeGreaterThanAndTownAndBookingsCourseName(29, "Glasgow", "Data Analysis");
		assertEquals("Bob", foundCustomers.get(0).getName());
	}

}
