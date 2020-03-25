package org.zsw.boot.jpa.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author: zsw
 * @Description: 包含Version基础实体
 * @Date: 19-4-2  上午10:23
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class VersionableEntity extends Auditable {

    private Integer displayOrder;

    private String remark;

    @Version
    private Integer version;


    @Column(columnDefinition = "bit(1) DEFAULT 1")
    private Boolean enabled;
}
