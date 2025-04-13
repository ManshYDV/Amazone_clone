package com.ecom.utilities;

public class APIResponse<T> {
	private String message;
	private String status;
	private T data;

	public static <T> APIResponse<T> success(String message, T data) {
		return new APIResponse<>("success", message, data);
	}

	public static <T> APIResponse<T> success(String message) {
		return new APIResponse<>("success", message, null);
	}

	public static <T> APIResponse<T> error(String message) {
		return new APIResponse<>("error", message, null);
	}

	public APIResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIResponse(String message, String status, T data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
