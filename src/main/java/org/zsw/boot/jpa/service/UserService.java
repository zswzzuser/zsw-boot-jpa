package org.zsw.boot.jpa.service;

import org.zsw.boot.jpa.token.SessionUser;
import java.util.Optional;

public interface UserService<ID> {

    Optional<SessionUser> loadByUserId(ID userId);

}
