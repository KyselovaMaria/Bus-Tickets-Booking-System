package com.example.bus_tickets.repository;

import com.example.bus_tickets.model.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseInfo, Long> {

}
