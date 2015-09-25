package com.mygdx.model.event;

public class NPC extends EventElement {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEvents() == null) ? 0 : getEvents().hashCode());
		return result;
	}
}
