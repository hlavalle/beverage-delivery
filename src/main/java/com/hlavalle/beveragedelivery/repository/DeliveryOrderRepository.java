package com.hlavalle.beveragedelivery.repository;

import com.hlavalle.beveragedelivery.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

}
