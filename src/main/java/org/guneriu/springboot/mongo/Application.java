package org.guneriu.springboot.mongo;

import java.util.Arrays;
import java.util.List;

import org.guneriu.springboot.mongo.config.MongoConfig;
import org.guneriu.springboot.mongo.customer.Customer;
import org.guneriu.springboot.mongo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@EnableAutoConfiguration
@Import(MongoConfig.class)
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MongoOperations mongoOperations;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Customer customer1 = new Customer("James", "Gosling");
		Customer customer2 = new Customer("James", "Joyce");
		Customer customer3 = new Customer("Joshua", "Bloch");

		customerRepository.save(Arrays.asList(customer1, customer2, customer3));

		List<Customer> customers = customerRepository.findAll();

		System.out.println("listing all customers");

		for (Customer customer : customers) {
			System.out.println(customer);
		}

		List<Customer> customersByFirstName = customerRepository.findByFirstName("James");

		System.out.println("customers with firstName James");

		for (Customer customer : customersByFirstName) {
			System.out.println(customer);
		}

		List<Customer> customersByFirstNameAndLastName =
				mongoOperations.find(
						new Query(new Criteria().andOperator(
								Criteria.where("firstName").is("Joshua"),
								Criteria.where("lastName").is("Bloch"))),
								Customer.class);

		System.out.println("customers with firstName Joshua and lastName Bloch");

		for (Customer customer : customersByFirstNameAndLastName) {
			System.out.println(customer);
		}

		System.out.println("all costumer counts: " + customerRepository.customerCount());
		System.out.println("customer count firstName or lastName is James :"
				+ customerRepository.customersCountByFirstNameOrLastName("James"));

	}
}
