package io.github.hx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author：3500
 * @date: 2024-03-06
 * @Copyright： 公众号：3500的杂记
 */
@Data
@TableName("lottery_count")
public class LotteryCount {

    @TableField("user_id")
    private Integer userId;

    @TableField("lottery_count")
    private Integer lotteryCount;
}