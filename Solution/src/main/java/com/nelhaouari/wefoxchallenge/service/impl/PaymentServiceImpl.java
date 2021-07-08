package com.nelhaouari.wefoxchallenge.service.impl;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;
import com.nelhaouari.wefoxchallenge.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public void handleIncomingPayment(PaymentDTO payment) {
        isPaymentValid(payment);
    }

    @Override
    public boolean isPaymentValid(PaymentDTO payment) {
        if (payment == null) return false;

        logger.info("Checking is payment valid ...");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentDTO> entity = new HttpEntity<>(payment, headers);

        RestTemplate restTemplate = new RestTemplate();
        String HOST_PAYMENT = "http://localhost:9000/payment";
        ResponseEntity<String> response = restTemplate.exchange(HOST_PAYMENT,
                HttpMethod.POST,
                entity,
                String.class);

        logger.info("Checking ended: " + response.getStatusCode());
        return HttpStatus.OK.equals(response.getStatusCode());
    }

}
