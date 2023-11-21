package com.bankapplication.config;

public class ResponseStructure<T> 
{
	private int Status;
	private String Msg;
	private T Data;
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public T getData() {
		return Data;
	}
	public void setData(T data) {
		Data = data;
	}
	
	
}
