package io.github.hx.controller;

import io.github.hx.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @description:
 * @author：3500
 * @date: 2024-02-13
 * @Copyright： 公众号：3500的杂记
 */
@Controller
@RequestMapping("/stock")
public class StockTest {
    @Resource
    private StockService stockService;

    // 有库存限制请求：http://localhost:8080/stock/sub?poolId=1&itemId=1&subCount=1
    // 无库存限制请求：http://localhost:8080/stock/sub?poolId=2&itemId=2&subCount=1
    @RequestMapping("/sub")
    public ResponseEntity<String> test(String poolId, String itemId,Integer subCount) {
        boolean flag = stockService
                .subStock(poolId, itemId, subCount);

        if (flag) {
            return ResponseEntity.ok("success");
        }
        else {
            return ResponseEntity.status(400).body("fail");
        }
    }
}
