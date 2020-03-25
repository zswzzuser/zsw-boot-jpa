package org.zsw.boot.jpa.base;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.zsw.boot.jpa.token.Operator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
@DynamicInsert
@DynamicUpdate
public class Auditable  implements Serializable {
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "createUserId", updatable = false)),
			@AttributeOverride(name = "userName", column = @Column(name = "createUserName", updatable = false)) })
	@CreatedBy
	private Operator createdBy;
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "updateUserId", updatable = true)),
			@AttributeOverride(name = "userName", column = @Column(name = "updateUserName", updatable = true)) })
	@LastModifiedBy
	private Operator updatedBy;

	
	@CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createTime;
	
	@LastModifiedDate
    @Column(nullable = false, updatable = true)
    private Date updateTime;

	@Column(columnDefinition = "bit(1) DEFAULT 0")
    private Boolean deleted;
}
