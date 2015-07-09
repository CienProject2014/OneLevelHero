package com.mygdx.model;

import com.mygdx.enums.MonsterEnum;

public class Monster extends Unit {
	private MonsterEnum.SizeType sizeType;
	private MonsterEnum.ElementType elementType;

	public MonsterEnum.SizeType getSizeType() {
		return sizeType;
	}

	public void setSizeType(MonsterEnum.SizeType type) {
		this.sizeType = type;
	}

	public MonsterEnum.ElementType getElementType() {
		return elementType;
	}

	public void setElementType(MonsterEnum.ElementType elementType) {
		this.elementType = elementType;
	}

}
