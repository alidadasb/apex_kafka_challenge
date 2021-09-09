package com.apex.kafka.challenge.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTotalCalc {

    private String uuId;
    private BigDecimal totalAmount;
    private Integer totalQuantity;

    public void update(BigDecimal price, Integer quantity) {
        totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(quantity)));
        totalQuantity += quantity;
    }

    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
