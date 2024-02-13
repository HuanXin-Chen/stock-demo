package io.github.hx;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author：3500
 * @date: 2024-02-13
 * @Copyright： 公众号：3500的杂记
 */
@Configurable
@SpringBootApplication
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication
                .run(StockApplication.class, args);
    }
}
