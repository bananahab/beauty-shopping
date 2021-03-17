package com.beauty.shopping;
import com.beauty.shopping.mbg.Generator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wuzhenxian
 * @date 2021/03/03
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
        //Generator.generateMapper();
    }
}
