package io.github.hx.service;

import io.github.hx.entity.GoldDamageMapping;
import io.github.hx.mapper.DamageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author：3500
 * @date: 2024-03-06
 * @Copyright： 公众号：3500的杂记
 */

@Service
public class DragonAttackService {

    @Resource
    private DragonScriptService dragonScriptService;

    @Resource
    private DamageMapper damageMapper;

    // 传入用户id，用户的鞭炮，用户攻击的轮次
    public void attack(String roundId,Integer damageId,String userId) throws IOException {

        // 获取鞭炮的属性
        GoldDamageMapping damageMapping = damageMapper.selectById(damageId);

        // 传入轮次，和伤害，进行攻击年兽
        List<Object> result = dragonScriptService.executeLuaScript(roundId, damageMapping.getDamage());

        //TODO：如果用户网络超时，返回兜底方案

        // 返回的结果
        Long endResult = (Long) result.get(1);
        Long endDamage = (Long) result.get(0);

        // 根据结果进行判断
        if (endResult == -1) {
            System.out.println("攻击失败,当前用户id为"+userId);
        } else if (endResult == 0) {
            System.out.println("攻击成功，当前用户id为"+userId+"，伤害为"+endDamage);
            // TODO:更新用户抽奖次数，根据鞭炮映射进行数据库更新
        } else if (endResult == 1) {
            System.out.println("击败年兽成功，当前用户id为"+userId+"，伤害为"+ endDamage);
            //TODO：更新用户抽奖次数，根据鞭炮映射进行数据库更新
            //TODO: 触发击败宝箱奖励
        }

        return ;
    }
}
