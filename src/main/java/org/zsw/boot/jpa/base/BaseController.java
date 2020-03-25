package org.zsw.boot.jpa.base;

import org.springframework.context.annotation.Scope;
import org.zsw.boot.jpa.constant.APIException;
import org.zsw.boot.jpa.constant.ErrorEnum;
import org.zsw.boot.jpa.token.OperatorAware;
import org.zsw.boot.jpa.token.SessionUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author: zsw
 * @Description:
 * @Date: 19-4-10  下午4:36
 */
@Scope("prototype")
public abstract class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    public BaseController(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    protected SessionUser currentUser() {
        Optional<SessionUser> optional = OperatorAware.getCurrentUser();
        SessionUser currentUser = optional.orElse(null);
        if (currentUser == null || currentUser.getUserId() == null) {
            throw new APIException(ErrorEnum.USER_AUTH_FAILED);
        }
        return currentUser;
    }
}
