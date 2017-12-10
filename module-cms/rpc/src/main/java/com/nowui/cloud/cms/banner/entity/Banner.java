package com.nowui.cloud.cms.banner.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value = "banner")
public class Banner {

    /**
     * 应用编号
     */
    @TableField
    private String bannerId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;
}
