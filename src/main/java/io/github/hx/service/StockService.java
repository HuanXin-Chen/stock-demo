package io.github.hx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.hx.entity.DragonActivityItemStock;
import io.github.hx.mapper.StockMapper;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：3500
 * @date: 2024-02-13
 * @Copyright： 公众号：3500的杂记
 */
@Service
public class StockService {
    @Resource
    StockMapper stockMapper;

    @Resource
    RedissonClient redissonClient;


    public boolean subStock(String poolId, String itemId, int num) {
        // 1.先从Redis中获得当前的库存
        String key = poolId + ":" + itemId + ":" + "stock" ;
        RBucket<Integer> bucket = redissonClient.getBucket(key);
        Integer stock = bucket.get();

        // 2.如果redis中没有，初始化Redis
        if(stock == null) {
            DragonActivityItemStock stockRecord = getByPoolIdAndItemId(poolId, itemId);
            if(stockRecord == null) {
                return false;
            }
            try {
                initRedisStock(poolId, itemId, stockRecord.getStock());
            } catch (Exception e) { // 处理外部异常，防止影响主流程
                System.out.println("扣减库存失败");
                return false;
            }
            stock = stockRecord.getStock();
        }

        // 3.递增库存
        long useStock = editStock(poolId, itemId, num);
        if(useStock > stock && stock != -1){ //-1是没有库存限制
            // 4.超卖恢复库存
            editStock(poolId, itemId, -num);
            System.out.println("扣减库存失败");
            return false;
        }
        // 5.扣减库存成功
        System.out.println("扣减库存成功");
        return true;
    }



    // 查询信息
    private  DragonActivityItemStock getByPoolIdAndItemId(String poolId, String itemId) {
        QueryWrapper<DragonActivityItemStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pool_id", poolId);
        queryWrapper.eq("item_id", itemId);
        DragonActivityItemStock record = stockMapper.selectOne(queryWrapper);
        return record;
    }

    // 初始化库存
    private Boolean initRedisStock(String poolId, String itemId, int count) {
        String Key = poolId + ":" + itemId + ":" + "stock" ;
        RBucket<Integer> bucket = redissonClient.getBucket(Key);
        // 添加时间，避免内存泄露
        bucket.set(count,60, TimeUnit.MINUTES);
        return true;
    }

    //  修改redis库存
    private long editStock(String poolId, String itemId, int count) {
        String Key = poolId + ":" + itemId + ":" + "useStock";
        RAtomicLong atomicLong = redissonClient.getAtomicLong(Key);
        return atomicLong.addAndGet(count);
    }

}
