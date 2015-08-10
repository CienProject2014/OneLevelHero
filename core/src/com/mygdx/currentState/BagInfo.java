package com.mygdx.currentState;

import java.util.ArrayList;

import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.HandGrip;
import com.mygdx.model.item.Item;

public class BagInfo {
	private ArrayList<Clothes> clothesList;
	private ArrayList<Accessory> necklaceList;
	private ArrayList<HandGrip> handGripList;
	private ArrayList<Item> etcItemList;
	public ArrayList<Clothes> getClothesList() {
		return clothesList;
	}
	public void setClothesList(ArrayList<Clothes> clothesList) {
		this.clothesList = clothesList;
	}
	public ArrayList<Accessory> getNecklaceList() {
		return necklaceList;
	}
	public void setNecklaceList(ArrayList<Accessory> necklaceList) {
		this.necklaceList = necklaceList;
	}
	public ArrayList<HandGrip> getHandGripList() {
		return handGripList;
	}
	public void setHandGripList(ArrayList<HandGrip> handGripList) {
		this.handGripList = handGripList;
	}
	public ArrayList<Item> getEtcItemList() {
		return etcItemList;
	}
	public void setEtcItemList(ArrayList<Item> etcItemList) {
		this.etcItemList = etcItemList;
	}
}
