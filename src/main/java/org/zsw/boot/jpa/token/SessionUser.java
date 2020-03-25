package org.zsw.boot.jpa.token;

import java.util.Date;

public class SessionUser {
    private Long userId;
    private String userName;
    private String cellphone;
    private String photo;
    private String email;
    private Date lastLoginTime;
    private String extras;

    public SessionUser() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public SessionUser setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return this.userName;
    }

    public SessionUser setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
        return this;
    }
    public String getCellphone() {
        return this.cellphone;
    }

    public SessionUser setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
        return this;
    }

    public String getPhoto() {
        return this.photo;
    }

    public SessionUser setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public SessionUser setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    public SessionUser setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public String getExtras() {
        return this.extras;
    }

    public SessionUser setExtras(String extras) {
        this.extras = extras == null ? null : extras.trim();
        return this;
    }
}
