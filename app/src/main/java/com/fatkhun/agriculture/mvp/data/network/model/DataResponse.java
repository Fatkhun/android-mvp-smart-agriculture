package com.fatkhun.agriculture.mvp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse{

	@SerializedName("data")
	private List<Data> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setData(List<Data> data){
		this.data = data;
	}

	public List<Data> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public String toString(){
		return
				"DataSensorResponse{" +
						"data = '" + data + '\'' +
						",message = '" + message + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}