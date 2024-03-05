package io.github.hx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈焕新
 */
@Data
@TableName("dragon_activity_gold_damage")
public class GoldDamageMapping {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("gold_amount")
    private int goldAmount;

    @TableField("damage")
    private int damage;

    @TableField(" draw_times")
    private int drawTimes;
}