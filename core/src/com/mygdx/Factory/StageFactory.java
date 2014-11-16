package com.mygdx.Factory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.stage.EventStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.VillageStage;


public class StageFactory{
	
	
	public Stage makeStage(String stageName){		
		if(stageName == "event"){

			return new EventStage();
		}
		else if (stageName == "village"){

			return new VillageStage();
		}
		else {
			return new MenuStage(stageName);
		}
	}
	

}
