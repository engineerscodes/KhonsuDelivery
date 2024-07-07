package org.zeuscommerce.app.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.zeuscommerce.app.Entity.DeliveryStatus;

import java.util.Optional;


@Repository
public interface DeliveryRepo extends MongoRepository<DeliveryStatus, String> {
    Optional<DeliveryStatus> findFirstByOrderId(String orderId);
}
