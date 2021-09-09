package com.apex.kafka.challenge;

import com.apex.kafka.challenge.models.Product;
import com.apex.kafka.challenge.models.ProductMessage;
import com.apex.kafka.challenge.services.KafkaProducerService;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class EmbeddedKafkaIntegrationTest {
    @Autowired
    private KafkaProducerService producer;

    @Value("${kafka.productDetailsTopic}")
    private String productTopic;

    @Test
    public void testThatIAmGood() {
        Assertions.assertTrue(true);
    }

    /**
     * This test method will be used to generate sample data and
     * will send them to productDetailsTopic
     */
    @Test
    @Disabled
    public void startSendingMessage() throws Exception {
        do {
            ProductMessage productMessage = new ProductMessage(generateProduct(randomInt(0, 5)));
            producer.send(productTopic, productMessage.toJsonString());

            Thread.sleep(5000);
        } while (true);
    }

    private List<Product> generateProduct(int count) {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setName(RandomStringUtils.randomAlphabetic(6));
            product.setDescription("Description of the product [" + product.getName() + "]");
            product.setUnitPrice(BigDecimal.valueOf(randomDouble(0.1, 100.0)).setScale(2, RoundingMode.CEILING));
            product.setUnitQuantity(randomInt(1, 100));

            products.add(product);
        }

        return products;
    }

    private int randomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min + 1) + min);
    }

    private double randomDouble(double min, double max) {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }
}
