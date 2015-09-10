package com.mygdx.model.event;

import java.util.ArrayList;

import com.mygdx.model.eventParameter.BattleParameter;
import com.mygdx.model.eventParameter.ItemParameter;
import com.mygdx.model.eventParameter.LocationParameter;
import com.mygdx.model.eventParameter.MusicParameter;
import com.mygdx.model.eventParameter.TimeParameter;
import com.mygdx.model.eventParameter.UnitParameter;

public class EventParameters {
	private TimeParameter time;
	private ArrayList<EventScene> eventScenes;
	private LocationParameter location;
	private BattleParameter battle;
	private ArrayList<EventPacket> events;
	private EventPacket eventPacket;
	private ArrayList<String> options;
	private UnitParameter unit;
	private String targetComponent;
	private int nextSectionNumber;
	private MusicParameter music;
	private ItemParameter item;

	public TimeParameter getTime() {
		return time;
	}
	public void setTime(TimeParameter time) {
		this.time = time;
	}
	public ArrayList<EventScene> getEventScenes() {
		return eventScenes;
	}
	public void setEventScenes(ArrayList<EventScene> eventScenes) {
		this.eventScenes = eventScenes;
	}
	public LocationParameter getLocation() {
		return location;
	}
	public void setLocation(LocationParameter location) {
		this.location = location;
	}
	public BattleParameter getBattle() {
		return battle;
	}
	public void setBattle(BattleParameter battle) {
		this.battle = battle;
	}
	public ArrayList<EventPacket> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<EventPacket> events) {
		this.events = events;
	}
	public ArrayList<String> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	public int getNextSectionNumber() {
		return nextSectionNumber;
	}
	public void setNextSectionNumber(int nextSectionNumber) {
		this.nextSectionNumber = nextSectionNumber;
	}
	public ItemParameter getItem() {
		return item;
	}
	public void setItem(ItemParameter item) {
		this.item = item;
	}
	public EventPacket getEventPacket() {
		return eventPacket;
	}
	public void setEventPacket(EventPacket eventPacket) {
		this.eventPacket = eventPacket;
	}
	public String getTargetComponent() {
		return targetComponent;
	}
	public void setTargetComponent(String targetComponent) {
		this.targetComponent = targetComponent;
	}
	public MusicParameter getMusic() {
		return music;
	}
	public void setMusic(MusicParameter music) {
		this.music = music;
	}
	public UnitParameter getUnit() {
		return unit;
	}
	public void setUnit(UnitParameter unit) {
		this.unit = unit;
	}
}
