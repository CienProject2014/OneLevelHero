package com.mygdx.model.event;

import java.util.ArrayList;

import com.mygdx.model.eventParameter.BattleParameter;
import com.mygdx.model.eventParameter.ItemParameter;
import com.mygdx.model.eventParameter.LocationParameter;
import com.mygdx.model.eventParameter.TargetTimeParameter;
import com.mygdx.model.eventParameter.TimeParameter;

public class EventParameters {
	private TimeParameter time;
	private TargetTimeParameter targetTime;
	private ArrayList<EventScene> eventScenes;
	private LocationParameter location;
	private BattleParameter battle;
	private ArrayList<Event> events;
	private ArrayList<String> options;
	private String targetComponent;
	private int nextSectionNumber;
	private String heroName;
	private String musicName;
	private ItemParameter item;
	private EventPacket eventPacket;

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
	public ArrayList<Event> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<Event> events) {
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
	public String getHeroName() {
		return heroName;
	}
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public TargetTimeParameter getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(TargetTimeParameter targetTime) {
		this.targetTime = targetTime;
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
}
