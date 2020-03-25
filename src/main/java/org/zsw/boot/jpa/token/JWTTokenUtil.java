package org.zsw.boot.jpa.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.boot.jpa.constant.APIException;
import com.boot.jpa.constant.ErrorEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: justin
 * @Description:
 * @Date: 2018-12-18 18:37
 */
@Slf4j
public class JWTTokenUtil {
    static final String key = "e10adc3949ba59abbe56e057f20f883e";

    /**
     * 创建token
     * @param authToken
     * @return
     */
    public static String createToken(AuthToken authToken)  {
        String token = null;
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        try {
            token = JWT.create()
                    .withHeader(map)//header
                    .withClaim("userId", authToken.getUserId())//payload
                    .withClaim("userName", authToken.getUserName())
                    .withClaim("rand", authToken.getRand())
                    .withNotBefore(new Date(authToken.getActive()))
                    .withExpiresAt(new Date(authToken.getExpiry()))
                    .sign(Algorithm.HMAC256(key));
        } catch (Exception e) {
            log.error("createJWTToken create error{}", e);
        }

        return token;
    }

    /**
     * 解开token
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token)  {
        JWTVerifier verifier = null;
        Map<String, Claim> claims = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(key)).build();
            DecodedJWT jwt = verifier.verify(token);
            claims = jwt.getClaims();
        }catch (TokenExpiredException ex) {
            throw new APIException(ErrorEnum.SIGN_VERIFI_EXPIRE);
        }catch (JWTVerificationException ex){
            throw new APIException(ErrorEnum.SIGN_VERIFI_ERROR);
        }
        return claims;
    }

    /**
     * 解开token并返回AuthToken
     * @param token
     * @return
     */
    public static AuthToken buildAuthToken(String token)  {
        JWTVerifier verifier = null;
        Map<String, Claim> claims = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(key)).build();
            log.info("authToken:{}",token);
            DecodedJWT jwt = verifier.verify(token);
            claims = jwt.getClaims();
            long userId = claims.get("userId").asLong();
            String userName = claims.get("userName").asString();
            long active = claims.get("nbf").asLong();
            long expiry = claims.get("exp").asLong();
            String rand = claims.get("rand").asString();
            return new AuthToken(userId, userName, active, expiry, rand);
        }catch (TokenExpiredException ex) {
            throw new APIException(ErrorEnum.SIGN_VERIFI_EXPIRE);
        }catch (JWTVerificationException ex){
            throw new APIException(ErrorEnum.SIGN_VERIFI_ERROR);
        }catch (Exception e) {
            log.error("verifyToken create error{}", e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        final long now = System.currentTimeMillis();
//        final long expiry = now+1000;
//        String jsonWebToken = createToken(new AuthToken(1L, "test", now, expiry, "123"));
//        log.info("jsonWebToken:{}", jsonWebToken);
        Map<String, Claim> claims = verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyYW5kIjoiMTIzIiwibmJmIjoxNTg0NzAxNzk1LCJ1c2VyTmFtZSI6InRlc3QiLCJleHAiOjE1ODQ3MDE3OTYsInVzZXJJZCI6MX0.zOGar-ck3moAKJd5McIyoM5pRtbhsnJKh3JzHIhwdaQ");
        System.out.println(claims.get("exp"));
    }


}
