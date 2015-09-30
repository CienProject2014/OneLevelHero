package com.mygdx.model.eventParameter;

import com.mygdx.enums.ItemEnum;

public class ItemParameter {
	private String itemPath;
	private int itemCount;
	private ItemEnum itemType;

	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemPath() {
		return itemPath;
	}
	public void setItemPath(String itemPath) {
		this.itemPath = itemPath;
	}
	public ItemEnum getItemType() {
		return itemType;
	}
	public void setItemType(ItemEnum itemType) {
		this.itemType = itemType;
	}
}
