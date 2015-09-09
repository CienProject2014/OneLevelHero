package com.mygdx.model.eventParameter;

public class LocationParameter extends Parameter {
	private String nodeName;
	private String subNodeName;
	private String arrowName;
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getSubNodeName() {
		return subNodeName;
	}
	public void setSubNodeName(String subNodeName) {
		this.subNodeName = subNodeName;
	}
	public String getArrowName() {
		return arrowName;
	}
	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}
}
