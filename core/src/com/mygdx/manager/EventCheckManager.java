package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.StorySectionPacket;

public class EventCheckManager {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;

	public boolean checkSelectComponent(int index,
			StorySectionPacket nextSectionPacket) {
		if (eventManager.getCurrentEvent().getEventComponent().get(index)
				.equals(nextSectionPacket.getTargetComponent())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkMovedBuilding(StorySectionPacket nextSectionPacket) {
		if (nextSectionPacket.getEventType()
				.equals(EventTypeEnum.MOVE_BUILDING)) {
			if (positionManager.getCurrentBuilding().equals(
					nextSectionPacket.getTargetComponent())) {
				Gdx.app.log(
						"EventCheckManager",
						"분기체크중 - " + positionManager.getCurrentBuilding()
								+ " / "
								+ nextSectionPacket.getTargetComponent());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean checkMovedVillage(StorySectionPacket nextSectionPacket) {
		if (nextSectionPacket.getEventType().equals(EventTypeEnum.MOVE_VILLAGE)) {
			if (positionManager.getCurrentNode().equals(
					nextSectionPacket.getTargetComponent())) {
				Gdx.app.log("EventCheckManager",
						"분기체크중 - " + positionManager.getCurrentNode() + " / "
								+ nextSectionPacket.getTargetComponent());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean checkSelectEvent(int index,
			StorySectionPacket nextSectionPacket) {
		if (nextSectionPacket.getEventType().equals(EventTypeEnum.SELECT_EVENT)) {
			if (String.valueOf(
					eventManager.getCurrentEventPacket().getEventNumber())
					.equals(nextSectionPacket.getTargetComponent())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean checkBattleEventType() {
		return eventManager.getCurrentEvent().getEventType()
				.equals(EventTypeEnum.BATTLE);
	}

	public String getBattleControlButton() {
		return storySectionManager.getNextSections().get(0)
				.getTargetComponent();
	}

	public boolean checkBattleControlEvent(
			StorySectionPacket nextSectionPacket, String controlName) {
		if (nextSectionPacket.getTargetComponent().equals(controlName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkMovedMoving(String arrowName,
			StorySectionPacket nextSectionPacket) {
		if (nextSectionPacket.getEventType().equals(EventTypeEnum.MOVE_MOVING)) {
			if (arrowName.equals(nextSectionPacket.getTargetComponent())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
