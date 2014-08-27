package com.mygdx.event;

public class Event {
	private String eventType;
	private String villageName;
	private String npcName;
	private String[] eventCode;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getNpcName() {
		return npcName;
	}

	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

	public String[] getEventCode() {
		return eventCode;
	}

	public void setEventCode(String[] eventCode) {
		this.eventCode = eventCode;
	}
}
