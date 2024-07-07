package org.zeuscommerce.app.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zeuscommerce.app.Entity.DeliveryStatus;

public class Utils {
    public static DeliveryStatus Json2Order(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString.trim().replaceFirst("\ufeff", ""));
        return objectMapper.treeToValue(jsonNode, DeliveryStatus.class);
    }
}