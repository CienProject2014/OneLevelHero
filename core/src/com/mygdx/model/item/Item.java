package com.mygdx.model.item;

import com.mygdx.enums.ItemEnum;

public class Item {
	private ItemEnum itemType;
	private String name;
	private String itemPath;
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

	public ItemEnum getItemType() {
		return itemType;
	}

	@Override
	public boolean equals(Object obj) {
		Item item = (Item) obj;
		return this.getItemPath().equals(item.getItemPath());
	}
}
