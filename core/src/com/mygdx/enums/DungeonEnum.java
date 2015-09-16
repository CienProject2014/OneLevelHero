package com.mygdx.enums;

public class DungeonEnum {
	public enum Type {
		GATE("gate"), ELITE("elite"), OBJECT("object"), BOSS("boss"), UP_STAIR("up_stair"), DOWN_STAIR("down_stair"), NORMAL(
				"normal");
		private String code;

		private Type(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}
	}

	public enum Direction {
		FORWARD("forward"), BACKWARD("backward");
		private String code;

		private Direction(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}
	}

	public enum ForwardAngle {
		LEFT("left"), RIGHT("right"), TOP_RIGHT("top_right"), BOTTOM("bottom"), TOP("top"), BOTTOM_RIGHT("bottom_right");
		private String code;

		private ForwardAngle(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}
	}
}
