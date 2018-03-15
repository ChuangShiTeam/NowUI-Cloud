package com.nowui.cloud.base.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 用户
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component

@TableName(value = "user_info")
public class User extends BaseEntity {

    /**
     * 用户编号
     */
    @TableId
    @TableField
    private String userId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 用户主体编号（会员、管理员、员工等编号）
     */
    @TableField
    private String objectId;

    /**
     * 类型
     */
    @TableField
    private String userType;


    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getObjectId() {
        return objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }


}