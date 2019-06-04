package com.fatkhun.agriculture.mvp.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class DataResponse {

	@SerializedName("temp")
	private float temp;

	@SerializedName("waterVolume")
	private float waterVolume;

	@SerializedName("soilMoisture")
	private float soilMoisture;

	@SerializedName("__v")
	private int V;

	@SerializedName("humidity")
	private float humidity;

	@SerializedName("_id")
	private String id;

	@SerializedName("createdAt")
	private String time;

	public void setTemp(float temp){
		this.temp = temp;
	}

	public float getTemp(){
		return temp;
	}

	public void setWaterVolume(float waterVolume){
		this.waterVolume = waterVolume;
	}

	public float getWaterVolume(){
		return waterVolume;
	}

	public void setSoilMoisture(float soilMoisture){
		this.soilMoisture = soilMoisture;
	}

	public float getSoilMoisture(){
		return soilMoisture;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setHumidity(float humidity){
		this.humidity = humidity;
	}

	public float getHumidity(){
		return humidity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"DataResponse{" +
			"temp = '" + temp + '\'' + 
			",waterVolume = '" + waterVolume + '\'' + 
			",soilMoisture = '" + soilMoisture + '\'' + 
			",__v = '" + V + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",_id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}