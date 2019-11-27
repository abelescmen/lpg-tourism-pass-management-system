package com.lpg.codechallenge.tourismpassmanagement.repository;

import com.lpg.codechallenge.tourismpassmanagement.entity.CustomerEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

}
