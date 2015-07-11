package com.mygdx.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.UnitAssets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.model.Hero;

public class LoadManager {
	@Autowired
	private UnitAssets unitAssets;

	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private TimeInfo timeInfo;
	private Hero hero;

	public void loadNewGame() {
		Gdx.app.debug("LoadManager", "loadNewGame()");
		setHero();
		setPartyList();
		setCurrentPosition();
		timeInfo.setTime(1, 8, 0);
	}

	public LoadManager() {
		Gdx.app.debug("LoadManager", "Constructor() call");
	}

	private void setCurrentPosition() {
		// Blackwood 마을에서부터 게임을 시작한다.
		positionInfo.setCurrentNode(WorldNodeEnum.BLACKWOOD.toString());
		positionInfo.setCurrentPlace(PlaceEnum.VILLAGE);

		// FIXME 초기 CurrentMoving 정보를 주입한다.
		movingInfo.setStartNode("Blackwood");
		movingInfo.setDestinationNode("Blackwood Forest");
		movingInfo.setRoadLength(7);
		movingInfo.setLeftRoadLength(7);
		List<String> monsterList = new ArrayList<String>();
		monsterList.add("assassin girl");
		movingInfo.setRoadMonsterList(monsterList);
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setHero() {
		// 추후 JSON에서 불러오도록 바꿀 것
		setHero(unitAssets.getHero("yongsa"));
		this.hero.getStatus().setSpd(8); // FIXME
	}

	// 해당 Hero들을 Party구성원에 포함시킨다
	private void setPartyList() {
		partyInfo.addHero(hero);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public MovingInfo getMovingInfo() {
		return movingInfo;
	}

	public void setMovingInfo(MovingInfo movingInfo) {
		this.movingInfo = movingInfo;
	}

	public PartyInfo getPartyInfo() {
		return partyInfo;
	}

	public void setPartyInfo(PartyInfo partyInfo) {
		this.partyInfo = partyInfo;
	}

	public TimeInfo getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(TimeInfo timeInfo) {
		this.timeInfo = timeInfo;
	}

	public UnitAssets getUnitAssets() {
		return unitAssets;
	}

	public void setUnitAssets(UnitAssets unitAssets) {
		this.unitAssets = unitAssets;
	}
}
