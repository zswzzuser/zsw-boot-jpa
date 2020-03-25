package org.zsw.boot.jpa.token;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class Operator {
	private Long userId;
	private String userName;
}
