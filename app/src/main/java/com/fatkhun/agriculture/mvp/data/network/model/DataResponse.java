package com.fatkhun.agriculture.mvp.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataResponse implements Serializable {

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

	@SerializedName("ruleFuzzyTemp")
	private String ruleTemp;

	@SerializedName("ruleFuzzyHum")
	private String ruleHum;

	@SerializedName("ruleFuzzyWater")
	private String ruleWater;

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

	public String getRuleTemp() {
		return ruleTemp;
	}

	public void setRuleTemp(String ruleTemp) {
		this.ruleTemp = ruleTemp;
	}

	public String getRuleHum() {
		return ruleHum;
	}

	public void setRuleHum(String ruleHum) {
		this.ruleHum = ruleHum;
	}

	public String getRuleWater() {
		return ruleWater;
	}

	public void setRuleWater(String ruleWater) {
		this.ruleWater = ruleWater;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	@Override
	public String toString() {
		return "DataResponse{" +
				"temp=" + temp +
				", waterVolume=" + waterVolume +
				", soilMoisture=" + soilMoisture +
				", V=" + V +
				", humidity=" + humidity +
				", id='" + id + '\'' +
				", ruleTemp='" + ruleTemp + '\'' +
				", ruleHum='" + ruleHum + '\'' +
				", ruleWater='" + ruleWater + '\'' +
				", time='" + time + '\'' +
				'}';
	}
}