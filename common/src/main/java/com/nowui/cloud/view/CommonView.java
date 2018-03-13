package com.nowui.cloud.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * baseView
 *
 * @author ZhongYongQiang
 * <p>
 * 2018-01-29
 */
//@Component
public class CommonView extends BaseView {

    /**
     * 请求人编号
     */
    @NotNull(message = "请求人编号不能为空")
    @Length(max = 32, message = "请求人编号长度超出限制")
    @JsonIgnore
    private String systemRequestUserId;
    public static final String SYSTEM_REQUEST_USER_ID = "systemRequestUserId";

    /**
     * 分页页数
     */
    @NotNull(message = "分页页数不能为空")
    @Digits(integer = 11, fraction = 0, message = "分页页数长度超出限制")
    @JsonIgnore
    private Integer pageIndex;
    public static final String PAGE_INDEX = "pageIndex";

    /**
     * 每页数量
     */
    @NotNull(message = "每页数量不能为空")
    @Digits(integer = 11, fraction = 0, message = "分页页数长度超出限制")
    @JsonIgnore
    private Integer pageSize;
    public static final String PAGE_SIZE = "pageSize";

    public String getSystemRequestUserId() {
        return systemRequestUserId;
    }

    public void setSystemRequestUserId(String systemRequestUserId) {
        this.systemRequestUserId = systemRequestUserId;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
