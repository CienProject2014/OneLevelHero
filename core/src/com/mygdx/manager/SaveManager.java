package com.mygdx.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mygdx.assets.Assets;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.PositionEnum;

public class SaveManager {
	@Autowired
	private CurrentInfo currentInfo;
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private TimeInfo timeInfo;
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private LoadNewManager loadManager;
	@Autowired
	private Assets assets;
	@Autowired
	private BattleManager battleManager;

	private Kryo kryo;

	public SaveManager() {
		kryo = new Kryo();
		kryo.register(PartyInfo.class);
		kryo.register(PositionInfo.class);
		kryo.register(TimeInfo.class);
		kryo.register(StorySectionInfo.class);
		kryo.register(EventInfo.class);
	}

	public void setNewGame() {
		loadFirstInfo();
		assets.initializeUnitInfo();
		loadManager.loadNewGame();
	}

	public void saveFirstInfo() {
		FileHandle handle;
		handle = Gdx.files.local("save/first");
		Output output;
		try {

			Gdx.app.log("save", handle.file().getParentFile().getAbsolutePath());
			if (!handle.file().getParentFile().exists()) {
				handle.file().getParentFile().mkdirs();
				handle.file().createNewFile();
			}
			Gdx.app.log("SaveManager", "저장작업완료" + handle.file().getAbsolutePath());
			output = new Output(new FileOutputStream(handle.file()));
			kryo.writeObject(output, partyInfo);
			kryo.writeObject(output, positionInfo);
			kryo.writeObject(output, timeInfo);
			kryo.writeObject(output, storySectionInfo);
			kryo.writeObject(output, eventInfo);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gdx.app.log("SaveManager", "저장작업완료");
	}

	public void loadFirstInfo() {
		FileHandle handle = Gdx.files.local("save/first");
		Input input;
		try {
			input = new Input(new FileInputStream(handle.file()));
			BeanUtils.copyProperties(kryo.readObject(input, PartyInfo.class), partyInfo);
			BeanUtils.copyProperties(kryo.readObject(input, PositionInfo.class), positionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, TimeInfo.class), timeInfo);
			BeanUtils.copyProperties(kryo.readObject(input, StorySectionInfo.class), storySectionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, EventInfo.class), eventInfo);
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save() {
		FileHandle handle;
		handle = Gdx.files.local("save/" + currentInfo.getSaveVersion().toString() + ".json");
		Output output;
		try {

			Gdx.app.log("save", handle.file().getParentFile().getAbsolutePath());
			if (!handle.file().getParentFile().exists()) {
				handle.file().getParentFile().mkdirs();
				handle.file().createNewFile();
			}
			Gdx.app.log("SaveManager", "저장작업완료" + handle.file().getAbsolutePath());
			output = new Output(new FileOutputStream(handle.file()));
			kryo.writeObject(output, partyInfo);
			kryo.writeObject(output, positionInfo);
			kryo.writeObject(output, timeInfo);
			kryo.writeObject(output, storySectionInfo);
			kryo.writeObject(output, eventInfo);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gdx.app.log("SaveManager", "저장작업완료");
	}

	public void load() {
		assets.initializeUnitInfo();
		FileHandle handle = Gdx.files.local("save/" + currentInfo.getSaveVersion().toString() + ".json");
		Input input;
		try {
			input = new Input(new FileInputStream(handle.file()));
			BeanUtils.copyProperties(kryo.readObject(input, PartyInfo.class), partyInfo);
			BeanUtils.copyProperties(kryo.readObject(input, PositionInfo.class), positionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, TimeInfo.class), timeInfo);
			BeanUtils.copyProperties(kryo.readObject(input, StorySectionInfo.class), storySectionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, EventInfo.class), eventInfo);
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		battleManager.setBeforePosition(PositionEnum.SUB_NODE);
		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
	}
}
