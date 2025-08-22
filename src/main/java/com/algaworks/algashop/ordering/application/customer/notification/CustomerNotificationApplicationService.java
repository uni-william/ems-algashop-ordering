package com.algaworks.algashop.ordering.application.customer.notification;

import java.util.UUID;

public interface CustomerNotificationApplicationService {
    void notifyNewRegistration(NotifyNewRegistrationInput input);

    record NotifyNewRegistrationInput(UUID customerId, String firstName, String email ){}
}
