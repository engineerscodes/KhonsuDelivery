package org.zeuscommerce.app.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zeuscommerce.app.Entity.DeliveryStatus;
import org.zeuscommerce.app.Repo.DeliveryRepo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class TrackerService {

    @Autowired
    DeliveryRepo repo;

    public void trackOrder(String orderId, SseEmitter emitter) throws IOException {
        Optional<DeliveryStatus> orderOpt= repo.findFirstByOrderId(orderId);
        if(orderOpt.isEmpty())  {
            emitter.send("Oder doesn't exist for tracking");
            emitter.complete();
            return;
        }
        DeliveryStatus deliveryStatus = orderOpt.get();
        long diff = Math.abs((long) (deliveryStatus.getDestinationLatitude()-
                deliveryStatus.getSourceLatitude()));
        System.out.println("Order Out for Delivery");
        for (long i=0;i<=diff;i++){
            emitter.send(String.format("On the way ,expected in %s",diff-i));
        }
        System.out.println("Delivery person outside the Address");
        emitter.complete();
    }
}
