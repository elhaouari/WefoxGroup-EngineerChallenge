package com.nelhaouari.wefoxchallenge.service.impl;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;
import com.nelhaouari.wefoxchallenge.service.PaymentMapper;
import com.nelhaouari.wefoxchallenge.service.impl.db.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperServiceImpl implements PaymentMapper {

    @Override
    public PaymentEntity mapPayment(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPayment_id(paymentDTO.getPayment_id());
        paymentEntity.setPayment_type(paymentDTO.getPayment_type());
        paymentEntity.setAccount_id(paymentDTO.getAccount_id());
        paymentEntity.setAmount(paymentDTO.getAmount());
        paymentEntity.setCredit_card(paymentDTO.getCredit_card());
        return paymentEntity;
    }
}
