package com.mygdx.Factory;

import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.Manager;
import com.mygdx.manager.MenuManager;
import com.mygdx.manager.VillageManager;

public class ManagerFactory {
	
	public void getManager(ScreenEnum screen){
		
		if(screen == null){
			throw new IllegalArgumentException("NULL불가");
		}
		if(screen.equals("EVENT")){
			 ScreenController.stageManager = new EventManager();
		}
		else if(screen.equals("VILLAGE")){
			ScreenController.stageManager = new VillageManager();
		}
		else {
			ScreenController.stageManager = new MenuManager();
		}
	}
}
