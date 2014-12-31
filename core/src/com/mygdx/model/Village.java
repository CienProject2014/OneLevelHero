package com.mygdx.model;

import java.util.List;

public class Village {

	//inner클래스인 Settable static 클래스
	public static class Settable {
		private String name;
		private double positionX;
		private double positionY;
		private double width;
		private double height;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPositionX() {
			return positionX;
		}

		public void setPositionX(double positionX) {
			this.positionX = positionX;
		}

		public double getPositionY() {
			return positionY;
		}

		public void setPositionY(double positionY) {
			this.positionY = positionY;
		}

		public double getWidth() {
			return width;
		}

		public void setWidth(double width) {
			this.width = width;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

	}

	//inner클래스이며 Settable을 상속하는 Building 클래스
	public static class Building extends Settable {
		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

	//inner 클래스이며 Settable을 상속하는 NPC클래스
	public static class NPC extends Settable {
		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

	private String name;
	private String imagesource;
	private String background;
	private String frontground;
	private double ratio;
	private List<Building> building;
	private List<NPC> npc;
	private List<Settable> exit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagesource() {
		return imagesource;
	}

	public void setImagesource(String imagesource) {
		this.imagesource = imagesource;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getFrontground() {
		return frontground;
	}

	public void setFrontground(String frontground) {
		this.frontground = frontground;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public void setBuilding(List<Building> building) {
		this.building = building;
	}

	public List<NPC> getNpc() {
		return npc;
	}

	public void setNpc(List<NPC> npc) {
		this.npc = npc;
	}

	public List<Settable> getExit() {
		return exit;
	}

	public void setExit(List<Settable> exit) {
		this.exit = exit;
	}

	@Override
	public String toString() {
		return "Village [name=" + name + ", imagesource=" + imagesource
				+ ", background=" + background + ", frontground=" + frontground
				+ ", ratio=" + ratio + ", building=" + building + ", npc="
				+ npc + ", exit=" + exit + "]";
	}

	public List<Building> getBuilding() {
		// TODO Auto-generated method stub
		return building;
	}

}
