package com.algaworks.algashop.ordering.infrastructure.notification.shoppingcart;

import com.algaworks.algashop.ordering.application.shoppingcart.notification.ShoppingCartNotificationApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartNotificationApplicationServiceFakeImpl implements ShoppingCartNotificationApplicationService {

    @Override
    public void notifyNewRegistration(NotifyNewRegistrationInput input) {

        log.info("Cart id {} Customer {}  Date {}", input.shoppingCartId(), input.customerId(), input.dateAt());

    }
}
