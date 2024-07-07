package org.zeuscommerce.app.Messaging.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.zeuscommerce.app.Repo.DeliveryRepo;
import org.zeuscommerce.app.Util.Utils;


@Configuration
@Slf4j
public class Confirmed {

    @Autowired
    DeliveryRepo deliveryRepo;

    @RabbitListener(queues = {"zeus-delivery-order"})
    public void onUserRegistration(byte[] bytes){
        log.info("Listening to Message");
        String message = new String(bytes);
        log.info(message);
        try {
            deliveryRepo.save(Utils.Json2Order(message));
        } catch (JsonProcessingException e) {
            log.error("Error Happened");
        }
    }
}
