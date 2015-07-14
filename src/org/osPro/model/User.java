package org.osPro.model;

public class User {
	private Integer id;
	private Integer type;
	private Integer arriveTime;
	private Integer currentTime;
	private Integer startTime=-1;
	private Integer seriveTime;
	private Integer endTime;
	private String state;

	
	public Integer getEndTime() {
		return startTime+seriveTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Integer arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Integer getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Integer currentTime) {
		this.currentTime = currentTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getSeriveTime() {
		return seriveTime;
	}

	public void setSeriveTime(Integer seriveTime) {
		this.seriveTime = seriveTime;
	}

	public String getState() {
		if (type == 1) {
			if (currentTime == startTime) {
				return "读者["+id+"]进入阅览室";
			}
			if (currentTime > startTime
					&& currentTime < (startTime + seriveTime)) {
				return "读者["+id+"]正在读书";
			}
			if (currentTime == (startTime + seriveTime)) {
				return "读者["+id+"]离开阅览室";
			}
		}
		if(type == 2){
			if (currentTime == startTime) {
				return "写者["+id+"]进入阅览室";
			}
			if (currentTime > startTime
					&& currentTime < (startTime + seriveTime)) {
				return "写者["+id+"]正在写";
			}
			if (currentTime == (startTime + seriveTime)) {
				return "写者["+id+"]离开阅览室";
			}
		}
		return "";
	}

	public void setState(String state) {
		this.state = state;
	}

}
