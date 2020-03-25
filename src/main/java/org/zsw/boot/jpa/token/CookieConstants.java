package org.zsw.boot.jpa.token;

/**
 * @author: justin
 * @Description:
 * @Date: 2018-10-19 17:47
 */
public class CookieConstants {
    /**
     * 平台端默认traderId -1L
     */
    public static final Long PLATFORM_TRADER_ID = -1L;
    /**
     * 未知 traderId -2L
     */
    public static final Long UNKNOWN_TRADER_ID = -2L;

    /**
     * 默认ParentId 0L
     */
    public static final Long ROOT_ID = 0L;



    public static final String SESSION_AUTH_TOKEN = "session_authToken";
    public static final String SESSION_USER_ID = "userId";

    /**
     * APP AuthToken 最长过期时间（1年）
     */
    public static final int AUTH_TOKEN_AGE_MAX = 365 * 24 * 3600;
    public static final String AUTH_TOKEN_NAME = "_MCH_AT";

    /**
     * token默认名称
     */
    public static final String AUTH_TOKEN_NAME_DEFAULT = "token";

}
