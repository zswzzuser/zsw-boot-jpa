package org.zsw.boot.jpa.token;//package com.boot.jpa.token;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zsw.boot.jpa.constant.APIException;
import org.zsw.boot.jpa.constant.ErrorEnum;
import org.zsw.boot.jpa.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 拦截请求，设置当前操作者
 *
 * @author martin
 */
@Slf4j
@Component
public class OperatorAwareInterceptor extends HandlerInterceptorAdapter {

    public static final String SESSION_USER = "X-Token";

    private UserService userService;

    public OperatorAwareInterceptor(UserService userService) {
        this.userService=userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        try {
            OperatorAware.clear(); // MUST clear
            String token = req.getHeader(SESSION_USER);

            if(StringUtils.isNotEmpty(token)){
                AuthToken authToken= AuthToken.parse(token);
                Optional<SessionUser> user = userService.loadByUserId(authToken.getUserId());
                if(user.isPresent()){
                    OperatorAware.setCurrentUser(user.get());
                }
                throw new APIException(ErrorEnum.SIGN_VERIFI_ERROR);
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return true;
    }
}
