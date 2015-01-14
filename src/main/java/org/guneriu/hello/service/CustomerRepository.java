package org.guneriu.hello.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface CustomerRepository extends JpaRepository<Customer, String> {

    public Customer findByFirstName(@Param("firstName") String firstName);
    public List<Customer> findByLastName(@Param("lastName") String lastName);

}
