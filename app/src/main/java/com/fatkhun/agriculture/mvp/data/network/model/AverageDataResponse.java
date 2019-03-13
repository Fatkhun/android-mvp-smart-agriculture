package com.fatkhun.agriculture.mvp.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class AverageDataResponse{

	@SerializedName("avgWater")
	private float avgWater;

	@SerializedName("avgHumidity")
	private float avgHumidity;

	@SerializedName("_id")
	private String id;

	@SerializedName("avgTemp")
	private float avgTemp;

	@SerializedName("avgSoilMoisture")
	private float avgSoilMoisture;

	public void setAvgWater(float avgWater){
		this.avgWater = avgWater;
	}

	public float getAvgWater(){
		return avgWater;
	}

	public void setAvgHumidity(float avgHumidity){
		this.avgHumidity = avgHumidity;
	}

	public float getAvgHumidity(){
		return avgHumidity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAvgTemp(float avgTemp){
		this.avgTemp = avgTemp;
	}

	public float getAvgTemp(){
		return avgTemp;
	}

	public void setAvgSoilMoisture(float avgSoilMoisture){
		this.avgSoilMoisture = avgSoilMoisture;
	}

	public float getAvgSoilMoisture(){
		return avgSoilMoisture;
	}

	@Override
	public String toString() {
		return "AverageDataResponse{" +
				"avgWater=" + avgWater +
				", avgHumidity=" + avgHumidity +
				", id=" + id +
				", avgTemp=" + avgTemp +
				", avgSoilMoisture=" + avgSoilMoisture +
				'}';
	}
}