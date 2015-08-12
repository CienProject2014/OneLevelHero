package com.mygdx.model.item;

import com.mygdx.enums.WeaponEnum;

public class Item {
	private String name;
	private String itemPath;
	private WeaponEnum itemType;
	private String grade;
	private String category;
	private String description;
	private ItemEffect itemEffect;

	public ItemEffect getItemEffect() {
		return itemEffect;
	}

	public void setItemEffect(ItemEffect itemEffect) {
		this.itemEffect = itemEffect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WeaponEnum getItemType() {
		return itemType;
	}

	public void setItemType(WeaponEnum itemType) {
		this.itemType = itemType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getItemPath() {
		return itemPath;
	}

	public void setItemPath(String itemPath) {
		this.itemPath = itemPath;
	}
}
