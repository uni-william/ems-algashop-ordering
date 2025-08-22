package com.algaworks.algashop.ordering.application.shoppingcart.notification;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface ShoppingCartNotificationApplicationService {

    void notifyNewRegistration(ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput input);

    record NotifyNewRegistrationInput(UUID shoppingCartId, UUID customerId, OffsetDateTime dateAt){}
}
