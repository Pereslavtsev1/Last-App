package org.example.orderservice.repositories;

import org.example.orderservice.dtos.orderlines.OrderLineResponse;
import org.example.orderservice.modles.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrderId(Long orderId);
}
