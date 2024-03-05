package io.github.hx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author：3500
 * @date: 2024-03-04
 * @Copyright： 公众号：3500的杂记
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DragonScriptTest {

    @Resource
    private DragonScriptService dragonScriptService;

    @Resource
    private DragonAttackService dragonAttackService;

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test() throws IOException {

        /* 测试前，手动执行一遍redis初始化
        SET monsterHp 50000
        SET monsterRound 1
         */

        for(int i = 0; i < 10; i++) {
            dragonAttackService.attack("1",2,"1");
        }
     }
}

/**
 * org.redisson.client.RedisException: ERR user_script:18: attempt to perform arithmetic on local 'damage' (a nil value)
 */