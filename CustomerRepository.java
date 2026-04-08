package com.trading.platform.repository;

import com.trading.platform.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByClientId(String clientId);
    List<Customer> findByStatus(String status);
    boolean existsByClientId(String clientId);
}
