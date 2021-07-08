package com.nelhaouari.wefoxchallenge.service;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;

public interface PaymentService {

    void handleIncomingPayment(PaymentDTO payment);

    void validatePaymentGatewayResponse(PaymentDTO payment) throws PaymentServiceException;

}
