package com.example.workflow.delegate;

import com.example.workflow.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorCreateOrderDelegate implements JavaDelegate {

    private static final String STATUS = "Отменен";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Order order = (Order) delegateExecution.getVariable("order");
        order.setStatus(STATUS);

        log.info("Заказ '{}'. Статус '{}'.", order.getOrderId(), order.getStatus());
        Thread.sleep(1000);

        delegateExecution.setVariable("order", order);
    }
}
