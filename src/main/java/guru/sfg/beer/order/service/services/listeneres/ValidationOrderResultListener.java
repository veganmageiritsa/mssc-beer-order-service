package guru.sfg.beer.order.service.services.listeneres;

import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.services.BeerOrderManager;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationOrderResultListener {
    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen (ValidateOrderResult validateOrderResult){
        UUID orderId = validateOrderResult.getOrderId();

        log.debug("Validation Result for Order Id : {} ",orderId );
        beerOrderManager.processValidationResult(validateOrderResult);
    }

}
