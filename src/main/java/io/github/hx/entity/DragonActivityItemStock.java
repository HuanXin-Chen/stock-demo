package io.github.hx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @description:
 * @author：3500
 * @date: 2024-02-13
 * @Copyright： 公众号：3500的杂记
 */

@Data
@TableName("dragon_activity_item_stock")
public class DragonActivityItemStock {


    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("pool_id")
    private String poolId;

    @TableField("item_id")
    private String itemId;

    @TableField("stock_type")
    private Integer stockType;

    @TableField("stock")
    private Integer stock;

    @TableField("create_time")
    private Timestamp createTime;

    @TableField("update_time")
    private Timestamp updateTime;

    @TableField("operator")
    private String operator;

    @TableField("is_delete")
    private Boolean isDelete;
}
