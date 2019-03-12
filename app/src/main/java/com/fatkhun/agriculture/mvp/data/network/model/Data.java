package com.fatkhun.agriculture.mvp.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

	@SerializedName("temp")
	private int temp;

	@SerializedName("waterVolume")
	private int waterVolume;

	@SerializedName("soilMoisture")
	private int soilMoisture;

	@SerializedName("__v")
	private int V;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("_id")
	private String id;

	@SerializedName("time")
	private String time;

	public void setTemp(int temp){
		this.temp = temp;
	}

	public int getTemp(){
		return temp;
	}

	public void setWaterVolume(int waterVolume){
		this.waterVolume = waterVolume;
	}

	public int getWaterVolume(){
		return waterVolume;
	}

	public void setSoilMoisture(int soilMoisture){
		this.soilMoisture = soilMoisture;
	}

	public int getSoilMoisture(){
		return soilMoisture;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
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
				"DataItem{" +
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