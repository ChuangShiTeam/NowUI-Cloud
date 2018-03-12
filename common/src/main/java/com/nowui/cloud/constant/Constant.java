package com.nowui.cloud.constant;

/**
 * @author ZhongYongQiang
 */
public class Constant {

    public static String PRIVATE_KEY;

    public static final String EXCHANGE = "exchange";

    public static final String CODE = "code";

    public static final String TOTAL = "total";

    public static final String DATA = "data";

    public static final String LIST = "list";

    public static final String MESSAGE = "message";

    public static final String ERROR = "网络繁忙，请稍后访问！";
    
    public static final String CHILDREN = "children";

    public static final String PUBLISH = "publish";

    public static final int DEFAULT_LOAD_FACTOR = 1;
    
    public static final String THUMBNAIL = "thumbnail";
    
    public static final String ORIGINAL = "original";
    
    public static final String UPLOAD = "upload";

    public static final String APP_ID = "appId";

    public static final String REQUEST_BODY = "requestBody";
    
    public static final String UPLOAD_IMAGE_TYPES = ".jpg.jpeg.gif.png.bmp.JPG.JPEG.GIF.PNG.BMP";
    
    public static final String EXPIRE_TIME = "expireTime";

    public static final String TOKEN = "token";

    public static final String SIGN = "sign";

    public static final String TIMESTAMP = "timestamp";
    
    public static final String PLATFORM = "platform";

    public static final String VERSION = "version";

    public static final int PREFETCH_COUNT = 10;

    public static final String SYSTEM_REQUEST_IP_ADDRESS = "systemRequestIpAddress";

    public static final String SYSTEM_REQUEST_USER_ID = "systemRequestUserId";

    public static final String SYSTEM_CREATE_USER_ID = "systemCreateUserId";

    public static final String SYSTEM_CREATE_TIME = "systemCreateTime";

    public static final String SYSTEM_UPDATE_USER_ID = "systemUpdateUserId";

    public static final String SYSTEM_UPDATE_TIME = "systemUpdateTime";

    public static final String SYSTEM_VERSION = "systemVersion";

    public static final String SYSTEM_STATUS = "systemStatus";

    public static final String PAGE_INDEX = "pageIndex";

    public static final String PAGE_SIZE = "pageSize";
    
    static {
        PRIVATE_KEY = System.getenv("PRIVATE_KEY");

        if (PRIVATE_KEY == null) {
            throw new RuntimeException("私密不能为空");
        }
    }

}
