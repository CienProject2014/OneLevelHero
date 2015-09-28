package com.mygdx.enums;

public class PositionEnum {
	public enum EventPosition {
		NONE("none"), GAME_OBJECT("game_object"), BATTLE("battle"), WORLD_MAP("world_map"), LOG("log"), NPC("greeting"), STORY(
				"story");

		private String eventPositionString;

		EventPosition(String eventPositionString) {
			this.eventPositionString = eventPositionString;
		}

		@Override
		public String toString() {
			return eventPositionString;
		}
		public static EventPosition findPlaceEnum(String positionEnumString) {
			for (EventPosition eventPositionType : EventPosition.values()) {
				if (eventPositionType.toString().equals(positionEnumString))
					return eventPositionType;
			}
			return null;
		}

	}

	public enum LocatePosition {
		NODE("node"), SUB_NODE("sub_node"), FIELD("field"), DUNGEON("dungeon");

		private String positionEnumString;

		LocatePosition(String positionEnumString) {
			this.positionEnumString = positionEnumString;
		}

		@Override
		public String toString() {
			return positionEnumString;
		}

		public static LocatePosition findPlaceEnum(String positionEnumString) {
			for (LocatePosition positionType : LocatePosition.values()) {
				if (positionType.toString().equals(positionEnumString))
					return positionType;
			}
			return null;
		}
	}
}
