package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.listener.AttackButtonListener;
import com.mygdx.listener.BackButtonListener;
import com.mygdx.listener.BuildingButtonListener;
import com.mygdx.listener.ChoiceGameObjectEventListener;
import com.mygdx.listener.ChoiceNpcEventListener;
import com.mygdx.listener.ChoiceOptionListener;
import com.mygdx.listener.CloseButtonListener;
import com.mygdx.listener.DungeonDoorButtonListener;
import com.mygdx.listener.DungeonEntranceButtonListener;
import com.mygdx.listener.GameEndListener;
import com.mygdx.listener.GameObjectButtonListener;
import com.mygdx.listener.GoBackwardFieldButtonListener;
import com.mygdx.listener.GoForwardFieldButtonListener;
import com.mygdx.listener.GoTitleListener;
import com.mygdx.listener.JumpSectionListener;
import com.mygdx.listener.LeaveDungeonButtonListener;
import com.mygdx.listener.LoadListener;
import com.mygdx.listener.RunAwayListener;

public class ListenerFactory {
	@Autowired
	private ApplicationContext context;

	public ArrowButtonListener getArrowButtonListener() {
		return context.getBean(ArrowButtonListener.class);
	}

	public AttackButtonListener getAttackButtonListener() {
		return context.getBean(AttackButtonListener.class);
	}

	public RunAwayListener getRunAwayListener() {
		return context.getBean(RunAwayListener.class);
	}

	public ChoiceGameObjectEventListener getChoiceGameObjectEventListener() {
		return context.getBean(ChoiceGameObjectEventListener.class);
	}

	public ChoiceNpcEventListener getChoiceNpcEventListener() {
		return context.getBean(ChoiceNpcEventListener.class);
	}
	public CloseButtonListener getCloseButtonListener() {
		return context.getBean(CloseButtonListener.class);
	}

	public BackButtonListener getBackButtonListener() {
		return context.getBean(BackButtonListener.class);
	}

	public BuildingButtonListener getBuildingButtonListener() {
		return context.getBean(BuildingButtonListener.class);
	}

	public DungeonEntranceButtonListener getDungeonEntranceButtonListener() {
		return context.getBean(DungeonEntranceButtonListener.class);
	}

	public GoBackwardFieldButtonListener getGoBackwardFieldButtonListener() {
		return context.getBean(GoBackwardFieldButtonListener.class);
	}

	public ChoiceOptionListener getChoiceOptionListener() {
		return context.getBean(ChoiceOptionListener.class);
	}

	public GoForwardFieldButtonListener getGoForwardFieldButtonListener() {
		return context.getBean(GoForwardFieldButtonListener.class);
	}

	public JumpSectionListener getJumpSectionListener() {
		return context.getBean(JumpSectionListener.class);
	}

	public GameObjectButtonListener getGameObjectButtonListener() {
		return context.getBean(GameObjectButtonListener.class);
	}

	public GoTitleListener getGoTitleListener() {
		return context.getBean(GoTitleListener.class);
	}

	public GameEndListener getGameEndListener() {
		return context.getBean(GameEndListener.class);
	}

	public LoadListener getLoadListener() {
		return context.getBean(LoadListener.class);
	}

	public DungeonDoorButtonListener getDungeonDoorButtonListener() {
		return context.getBean(DungeonDoorButtonListener.class);
	}

	public LeaveDungeonButtonListener getLeaveDungeonButtonListener() {
		return context.getBean(LeaveDungeonButtonListener.class);
	}
}
