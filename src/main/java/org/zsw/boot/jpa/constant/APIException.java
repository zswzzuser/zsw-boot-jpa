package org.zsw.boot.jpa.constant;

/**
 * @author zsw
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 6550932405990151612L;

	/**
	 * 消息标示代码
	 */
	private Integer code;
	/**
	 * 异常信息
	 */
	private String message;
	private Object data;
	private Exception exception;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * 抛出异常 无信息
	 */
	public APIException() {
		super();
	}

	/**
	 * 封装异常信息
	 *
	 * @param code : 消息标示代码
	 * 也可以将exception的message 直接传入该方法;
	 */
	public APIException(Integer code) {
		this.code = code;
	}

	public APIException(ErrorEnum codeMsg) {
		this.code = codeMsg.getCode();
		this.message = codeMsg.getMessage();
	}

	/**
	 * 封装异常信息
	 *
	 * @param code : 消息标示代码
	 * @param message : 异常信息
	 */
	public APIException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 封装异常信息,将Exception直接输入到该方法中
	 *
	 * @param cause
	 */
	public APIException(Throwable cause) {
		super(cause);
	}

	/**
	 * 封装异常信息, 将Exception直接输入到该方法中
	 *
	 * @param message
	 * @param cause
	 */
	public APIException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIException(Integer code, String message, Object data, Exception exception) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.exception = exception;
	}
}
