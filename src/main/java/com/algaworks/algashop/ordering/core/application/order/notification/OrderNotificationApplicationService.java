package com.algaworks.algashop.ordering.core.model.application.order.notification;

import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface OrderNotificationApplicationService {
    void notifyNewRegistration(OrderNotificationApplicationService.NotifyNewRegistrationInput input);

    record NotifyNewRegistrationInput(TSID order, UUID customerId, OffsetDateTime dateAt){}

}
