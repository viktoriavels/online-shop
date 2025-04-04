package ru.vels.orders.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.vels.orders.client.ProductClient;

@Component("unbookingDelegate")
@RequiredArgsConstructor
public class UnBookingDelegate implements JavaDelegate {

    private final ProductClient productClient;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String businessKey = execution.getBusinessKey();
        productClient.deleteBooking(businessKey);
    }

}