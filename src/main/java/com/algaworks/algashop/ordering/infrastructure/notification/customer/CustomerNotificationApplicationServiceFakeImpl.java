package com.algaworks.algashop.ordering.infrastructure.notification.customer;

import com.algaworks.algashop.ordering.application.customer.notification.CustomerNotificationApplicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerNotificationApplicationServiceFakeImpl implements CustomerNotificationApplicationService {


    @Override
    public void notifyNewRegistration(NotifyNewRegistrationInput input) {

        log.info("Welcome {}", input.firstName());
        log.info("User your email to acess your account {}", input.email());
    }
}
