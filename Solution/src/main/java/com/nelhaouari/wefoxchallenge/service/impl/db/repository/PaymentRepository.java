package com.nelhaouari.wefoxchallenge.service.impl.db.repository;

import com.nelhaouari.wefoxchallenge.service.impl.db.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
