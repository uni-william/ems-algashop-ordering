package com.algaworks.algashop.ordering.infrastructure.listener.order;

import com.algaworks.algashop.ordering.application.order.notification.OrderNotificationApplicationService;
import com.algaworks.algashop.ordering.domain.model.order.OrderCanceledEvent;
import com.algaworks.algashop.ordering.domain.model.order.OrderPaidEvent;
import com.algaworks.algashop.ordering.domain.model.order.OrderPlacedEvent;
import com.algaworks.algashop.ordering.domain.model.order.OrderReadyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderNotificationApplicationService orderNotificationApplicationService;

    @EventListener
    public void listen(OrderPlacedEvent event) {
        log.info("OrderPlacedEvent listen");
        OrderNotificationApplicationService.NotifyNewRegistrationInput input = new OrderNotificationApplicationService.NotifyNewRegistrationInput(
                event.orderId().value(), event.customerId().value(), event.placedAt()
        );
        orderNotificationApplicationService.notifyNewRegistration(input);

    }

    @EventListener
    public void listen(OrderPaidEvent event) {
        log.info("OrderPaidEvent listen");
        OrderNotificationApplicationService.NotifyNewRegistrationInput input = new OrderNotificationApplicationService.NotifyNewRegistrationInput(
                event.orderId().value(), event.customerId().value(), event.paidAt()
        );
        orderNotificationApplicationService.notifyNewRegistration(input);

    }

    @EventListener
    public void listen(OrderReadyEvent event) {
        log.info("OrderReadyEvent listen");
        OrderNotificationApplicationService.NotifyNewRegistrationInput input = new OrderNotificationApplicationService.NotifyNewRegistrationInput(
                event.orderId().value(), event.customerId().value(), event.readyAt()
        );
        orderNotificationApplicationService.notifyNewRegistration(input);

    }

    @EventListener
    public void listen(OrderCanceledEvent event) {
        log.info("OrderCanceledEvent listen");
        OrderNotificationApplicationService.NotifyNewRegistrationInput input = new OrderNotificationApplicationService.NotifyNewRegistrationInput(
                event.orderId().value(), event.customerId().value(), event.canceledAt()
        );
        orderNotificationApplicationService.notifyNewRegistration(input);
    }
}
