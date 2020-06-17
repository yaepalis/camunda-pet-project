package com.example.workflow.delegate;

import com.example.workflow.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class CreateOrderDelegate implements JavaDelegate {

    private static final String STATUS = "Ждет подтверждения";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Order order = new Order(String.valueOf((int) (Math.random() * 1000)), STATUS);

        log.info("Заказ '{}' был успешно создан. Статус заказа '{}'.", order.getOrderId(), order.getStatus());
        Thread.sleep(1000);

        Boolean isValid = new Random().nextBoolean();
        if (!isValid) {
            log.info("Заказ '{}'. Не хватает денежных средств.", order.getOrderId());
        } else {
            log.info("Заказ '{}'. Заказ оплачен.", order.getOrderId());
        }
        Thread.sleep(1000);

        delegateExecution.setVariable("order", order);
        delegateExecution.setVariable("isValid", isValid);
    }
}
