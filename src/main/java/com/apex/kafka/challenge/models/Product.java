package com.apex.kafka.challenge.models;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString( exclude = "description")
public class Product implements Serializable {

    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer unitQuantity;
}
