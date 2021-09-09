package com.apex.kafka.challenge.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMessage implements Serializable {

    private List<Product> productList;
    private String id;
    private Date date;

    public ProductMessage(List<Product> productList) {
        this.productList = productList;
        this.id = RandomStringUtils.randomAlphanumeric(10);
        this.date = new Date();
    }

    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

}

