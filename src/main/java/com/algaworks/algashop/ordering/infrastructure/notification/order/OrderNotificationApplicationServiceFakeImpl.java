package com.algaworks.algashop.ordering.infrastructure.notification.order;

import com.algaworks.algashop.ordering.application.order.notification.OrderNotificationApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderNotificationApplicationServiceFakeImpl implements OrderNotificationApplicationService {
    @Override
    public void notifyNewRegistration(NotifyNewRegistrationInput input) {

        log.info("Order {}", input.order());

    }
}
