package com.nowui.cloud.base.user.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 用户视图
 *
 * @author marcus
 *
 * 2018-03-14
 */
@Component
@Document(collection = "user_info")
public class UserView extends BaseView {

    /**
     * 用户编号
     */
    @KeyId
    @Field
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户主体编号
     */
    @Field
    @NotNull(message = "用户主体编号不能为空")
    @Length(max = 32, message = "用户主体编号长度超出限制")
    private String objectId;
    public static final String OBJECT_ID = "objectId";

    /**
     * 类型
     */
    @Field
    @NotNull(message = "类型不能为空")
    @Length(max = 25, message = "类型长度超出限制")
    private String userType;
    public static final String USER_TYPE = "userType";


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