package com.nelhaouari.wefoxchallenge.service.impl;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;
import com.nelhaouari.wefoxchallenge.model.PaymentLogError;
import com.nelhaouari.wefoxchallenge.service.PaymentLoggerService;
import com.nelhaouari.wefoxchallenge.service.PaymentMapper;
import com.nelhaouari.wefoxchallenge.service.PaymentService;
import com.nelhaouari.wefoxchallenge.service.PaymentServiceException;
import com.nelhaouari.wefoxchallenge.service.impl.db.entity.AccountEntity;
import com.nelhaouari.wefoxchallenge.service.impl.db.repository.AccountRepository;
import com.nelhaouari.wefoxchallenge.service.impl.db.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentLoggerService paymentLoggerService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void handleIncomingPayment(PaymentDTO paymentDTO) {
        try {
            validatePaymentDTO(paymentDTO);
            validatePaymentGatewayResponse(paymentDTO);
            storePayment(paymentDTO);
        } catch (PaymentServiceException e) {
            sendLogError(preparePaymentErrorObject(paymentDTO, e));
        }
    }

    private PaymentLogError preparePaymentErrorObject(PaymentDTO payment, PaymentServiceException e) {
        PaymentLogError paymentLogError = new PaymentLogError();
        paymentLogError.setPayment_id(payment.getPayment_id());
        if (e == null) {
            paymentLogError.setError_type(PaymentServiceException.TYPE_GENERAL_ERROR);
            paymentLogError.setError_description(PaymentServiceException.TYPE_GENERAL_ERROR_MSG);
        } else {
            paymentLogError.setError_type(e.getCode());
            paymentLogError.setError_description(e.getMessage());
        }
        return paymentLogError;
    }

    private void sendLogError(PaymentLogError paymentLogError) {
        paymentLoggerService.sendLogError(paymentLogError);
    }

    @Transactional
    void storePayment(PaymentDTO paymentDTO) throws PaymentServiceException {
        logger.info("Saving payment record ...");
        AccountEntity accountEntity = accountRepository.getById(paymentDTO.getAccount_id());
        if (accountEntity == null)
            throw new PaymentServiceException(PaymentServiceException.TYPE_DATABASE, "The account_id not found");
        paymentRepository.save(paymentMapper.mapPayment(paymentDTO));

        accountEntity.setLast_payment_date(new Date());
        accountRepository.save(accountEntity);

        logger.info("Payment saved");
    }

    @Override
    public void validatePaymentGatewayResponse(PaymentDTO paymentDTO) throws PaymentServiceException {
        logger.info("Checking payment is valid ...");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentDTO> entity = new HttpEntity<>(paymentDTO, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            String HOST_PAYMENT = "http://localhost:9000/payment";
            response = restTemplate.exchange(HOST_PAYMENT,
                    HttpMethod.POST,
                    entity,
                    String.class);
            logger.info("Payment checked: " + response.getStatusCode());
            if( !response.getStatusCode().is2xxSuccessful() ){
                throw new PaymentServiceException(PaymentServiceException.TYPE_NETWORK, "Payment response not OK");
            }
        } catch (HttpStatusCodeException e) {
            logger.info("Exception on payment api");
            throw new PaymentServiceException(PaymentServiceException.TYPE_NETWORK, e.getResponseBodyAsString());
        }
    }

    void validatePaymentDTO(PaymentDTO paymentDTO) throws PaymentServiceException {
        if (paymentDTO == null)
            throw new PaymentServiceException(PaymentServiceException.TYPE_OTHER, "bad request : payment is null");
        if (paymentDTO.getPayment_id() == null)
            throw new PaymentServiceException(PaymentServiceException.TYPE_OTHER, "bad request : payment_id is null");
        if (paymentDTO.getAccount_id() == null)
            throw new PaymentServiceException(PaymentServiceException.TYPE_OTHER, "bad request : account_id is null");
    }

}
