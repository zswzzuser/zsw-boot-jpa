package org.zsw.boot.jpa.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author justin
 */
@Slf4j
public class OperatorAware implements AuditorAware<Operator> {

    private static final ThreadLocal<SessionUser> userContext = new ThreadLocal<>();

    public static void setCurrentUser(SessionUser sessionUser) {
        userContext.set(sessionUser);
    }

    public static void clear() {
        userContext.remove();
    }

    public static Optional<SessionUser> getCurrentUser() {
        SessionUser user = userContext.get();
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public Optional<Operator> getCurrentAuditor() {
        SessionUser user = userContext.get();
        if (user == null) {
            return Optional.empty();
        }
        Operator operator = new Operator();
        operator.setUserId(user.getUserId());
        //如userName不存在,取手机号显示
        String userName = Optional.ofNullable(user.getUserName()).orElse(user.getCellphone());
        operator.setUserName(userName);
        log.debug("OperatorAware, userName:{}, cellphone:{}, currentUserName:{}", user.getUserName(), user.getCellphone(), userName);
        return Optional.of(operator);
    }
}
