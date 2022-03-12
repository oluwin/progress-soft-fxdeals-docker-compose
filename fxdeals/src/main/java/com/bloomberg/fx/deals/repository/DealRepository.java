package com.bloomberg.fx.deals.repository;

import com.bloomberg.fx.deals.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
