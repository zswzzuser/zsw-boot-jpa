package org.zsw.boot.jpa.token;

import lombok.Data;
import org.zsw.boot.jpa.utils.RandUtil;

import java.util.Optional;

/**
 * @author: justin
 * @Description:
 * @Date: 19-4-10  下午5:06
 */

@Data
public class AuthToken {

    public long userId;
    public String userName;
    public long expiry;
    private String rand;
    private long active;


    /**
     * @param userId * @param userName
     *               * @param active
     *               *            when the token is active (in milliseconds)
     *               * @param expiry
     *               *            when the token is expired (in milliseconds)
     */
    public AuthToken(long userId, String userName, long active, long expiry, String rand) {
        super();
        this.userId = userId;
        this.userName = Optional.ofNullable(userName).orElse("");
        this.active = active;
        this.expiry = expiry;
        this.rand = rand;
    }

    public AuthToken() {
        super();
    }

    public AuthToken(long userId, String userName) {
        super();
        this.userId = userId;
        this.userName = Optional.ofNullable(userName).orElse("");
        this.active = System.currentTimeMillis();
        this.expiry = this.active + ((long) CookieConstants.AUTH_TOKEN_AGE_MAX * 1000);
        this.rand = RandUtil.rand();
    }

    public String token() {
        return JWTTokenUtil.createToken(this);
    }

    public static AuthToken parse(String token) {
        return JWTTokenUtil.buildAuthToken(token);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthToken [userId=").append(userId).append(", active=").append(active)
                .append(", userName=").append(userName).append(", expiry=").append(expiry).append(", rand=").append
                (rand).append("]");
        return builder.toString();
    }

}

