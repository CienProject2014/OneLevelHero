package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.listener.AttackButtonListener;
import com.mygdx.listener.BackButtonListener;
import com.mygdx.listener.BuildingButtonListener;
import com.mygdx.listener.CloseButtonListener;
import com.mygdx.listener.DungeonEntranceButtonListener;
import com.mygdx.listener.GameEndListener;
import com.mygdx.listener.GameObjectButtonListener;
import com.mygdx.listener.GoBackwardFieldButtonListener;
import com.mygdx.listener.GoForwardFieldButtonListener;
import com.mygdx.listener.GoTitleListener;
import com.mygdx.listener.JumpSectionListener;
import com.mygdx.listener.LoadListener;
import com.mygdx.listener.RunAwayListener;
import com.mygdx.listener.SelectComponentListener;
import com.mygdx.listener.SelectEventListener;

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

	public SelectComponentListener getSelectComponentListener() {
		return context.getBean(SelectComponentListener.class);
	}

	public SelectEventListener getSelectEventListener() {
		return context.getBean(SelectEventListener.class);
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
}
