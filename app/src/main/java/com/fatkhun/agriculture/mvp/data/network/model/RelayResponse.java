package com.fatkhun.agriculture.mvp.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class RelayResponse{

	@SerializedName("pumpOn")
	private String pumpOn;

	@SerializedName("__v")
	private int V;

	@SerializedName("autoPumpOn")
	private String autoPumpOn;

	@SerializedName("_id")
	private String id;

	@SerializedName("time")
	private String time;

	public void setPumpOn(String pumpOn){
		this.pumpOn = pumpOn;
	}

	public String getisPumpOn(){
		return pumpOn;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setAutoPumpOn(String autoPumpOn){
		this.autoPumpOn = autoPumpOn;
	}

	public String getisAutoPumpOn(){
		return autoPumpOn;
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
			"RelayResponse{" + 
			"pumpOn = '" + pumpOn + '\'' + 
			",__v = '" + V + '\'' + 
			",autoPumpOn = '" + autoPumpOn + '\'' + 
			",_id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}