package com.mygdx.model.event;

import java.util.List;

public class EventElement {
	protected String name;
	protected String facePath;
	private List<String> greetingMessages;

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGreetingMessages() {
		return greetingMessages;
	}

	public void setGreetingMessages(List<String> greetingMessages) {
		this.greetingMessages = greetingMessages;
	}

}
