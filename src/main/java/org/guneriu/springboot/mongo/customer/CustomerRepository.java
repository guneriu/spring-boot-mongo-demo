package org.guneriu.springboot.mongo.customer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public List<Customer> findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

	@Query(value = "{}", count = true)
	public int customerCount();

	@Query(value = "{$or:[{'firstName': '?0'}, {'lastName': '?0'}]}", count = true)
	public int customersCountByFirstNameOrLastName(String name);

}
