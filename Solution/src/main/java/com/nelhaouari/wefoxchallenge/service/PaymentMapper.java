package com.nelhaouari.wefoxchallenge.service;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;
import com.nelhaouari.wefoxchallenge.service.impl.db.entity.PaymentEntity;

public interface PaymentMapper {

    PaymentEntity mapPayment(PaymentDTO paymentDTO);
}
