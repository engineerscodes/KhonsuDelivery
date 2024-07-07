package org.zeuscommerce.app.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zeuscommerce.app.Service.TrackerService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/tracker")
public class TrackerController {

    @Autowired
    TrackerService trackerService;

    @GetMapping("{orderId}/liveStatus") //this service is not delivery person status ,not tracking order progress
    public SseEmitter streamOrders(@PathVariable String orderId) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                trackerService.trackOrder(orderId,emitter);
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();

        return emitter;
    }

}
