package com.algaworks.algashop.ordering.infrastructure.listener.shoppingcart;

import com.algaworks.algashop.ordering.application.shoppingcart.notification.ShoppingCartNotificationApplicationService;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartCreatedEvent;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartEmptiedEvent;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartItemAddedEvent;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartItemRemovedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartEventListener {

    private final ShoppingCartNotificationApplicationService shoppingCartNotificationApplicationService;

    @EventListener
    public void listen(ShoppingCartCreatedEvent event) {
        log.info("ShoppingCartCreatedEvent listen");
        ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput input =
                new ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput(
                        event.shoppingCartId().value(), event.customerId().value(), event.createdAt()
                );
        shoppingCartNotificationApplicationService.notifyNewRegistration(input);

    }

    @EventListener
    public void listen(ShoppingCartEmptiedEvent event) {
        log.info("ShoppingCartEmptiedEvent listen");
        ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput input =
                new ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput(
                        event.shoppingCartId().value(), event.customerId().value(), event.emptiedAt()
                );
        shoppingCartNotificationApplicationService.notifyNewRegistration(input);

    }

    @EventListener
    public void listen(ShoppingCartItemAddedEvent event) {
        log.info("ShoppingCartItemAddedEvent listen");
        ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput input =
                new ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput(
                        event.shoppingCartId().value(), event.customerId().value(), event.addedAt()
                );
        shoppingCartNotificationApplicationService.notifyNewRegistration(input);
    }

    @EventListener
    public void listen(ShoppingCartItemRemovedEvent event) {
        log.info("ShoppingCartItemRemovedEvent listen");
        ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput input =
                new ShoppingCartNotificationApplicationService.NotifyNewRegistrationInput(
                        event.shoppingCartId().value(), event.customerId().value(), event.removedAt()
                );
        shoppingCartNotificationApplicationService.notifyNewRegistration(input);
    }
}
