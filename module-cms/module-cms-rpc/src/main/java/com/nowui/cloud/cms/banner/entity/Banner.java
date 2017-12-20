package com.nowui.cloud.cms.banner.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@TableName(value = "banner")
public class Banner extends BaseEntity{
    /**
     * 应用编号
     */
    @TableId
    @NotNull(message = "bannerId不能为空")
    @Length(max = 32, message = "应用编号字数超出限制")
    private String bannerId;

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "appId不能为空")
    @Length(max = 32, message = "应用编号字数超出限制")
    private String appId;

    /*
    *  广告分类编码
    * */
    @NotNull(message = "bannerCategoryCode不能为空")
    private String bannerCategoryCode;

    /**
     * 广告标题
     * */
    @TableField
    @NotNull(message = "广告标题不能为空")
    private String bannerTitle;

    /**
     * 广告位置
     * */
    @TableField
    @NotNull(message = "广告位置不能为空")
    private String bannerPosition;

    private String bannerLink;

    private String bannerImage;

    private int bannerSort;

    private int openStatus;

    public int getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }

    public String getAppId() {
        return appId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setAppId(String appId){
        this.appId = appId;
    }

    public void setBannerId(String bannerId){
        this.bannerId = bannerId;
    }

    public String getBannerCategoryCode() {
        return bannerCategoryCode;
    }

    public void setBannerCategoryCode(String bannerCategoryCode) {
        this.bannerCategoryCode = bannerCategoryCode;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerPosition() {
        return bannerPosition;
    }

    public void setBannerPosition(String bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }

    public int getBannerSort() {
        return bannerSort;
    }

    public void setBannerSort(int bannerSort) {
        this.bannerSort = bannerSort;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }
}
