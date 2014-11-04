package com.mygdx.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.Factory.ManagerFactory;
import com.mygdx.manager.Manager;

public class StageController extends Task {
	
	private String name;
	private Manager manager;
	private Stage stage;
	private Screen screen;
	//StageController로 넘겨주는 인자의 타입을 똑같이 통일하면 생성자를 하나로만 할 수 있고, 그것을 create()인자로 그대로 넘겨줘서 Manager를 생성하도록 해주면 된다.
	
	//eventkey(NPC이름이)가 넘어왔을때는 eventManager호출
	public StageController(String name,Screen screen){
		this.name=name;
		this.screen=screen;
		//this.manager = new ManagerFactory().create(name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		manager.setStage(name);
	}
	
	
	

}
