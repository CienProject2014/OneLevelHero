package com.mygdx.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mygdx.assets.Assets;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.SaveInfo;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.PositionEnum.LocatePosition;
import com.mygdx.enums.SaveVersion;
import com.mygdx.model.event.EventPacket;

public class SaveManager {
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
	@Autowired
	private SaveInfo saveInfo;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private EventManager eventManager;

	private final static String SAVEPATH = "save/";

	private Kryo kryo;

	public SaveManager() {
		kryo = new Kryo();
		kryo.register(SaveInfo.class);
		kryo.register(PartyInfo.class);
		kryo.register(PositionInfo.class);
		kryo.register(TimeInfo.class);
		kryo.register(StorySectionInfo.class);
		kryo.register(EventInfo.class);
	}

	public void setSaveInfo() {

		saveInfo.setGameTime(timeManager.getTimeInfo());
		saveInfo.setPartyList(partyInfo.getPartyList());
		if (positionManager.getCurrentLocatePositionType().equals(LocatePosition.NODE)) {
			saveInfo.setSavePlace(positionManager.getCurrentNodeName());
		} else {
			saveInfo.setSavePlace(positionManager.getCurrentSubNodeName());
		}
		switch (storySectionInfo.getCurrentSectionNumber()) {
			case 1 :
				saveInfo.setStoryName("프롤로그");
				break;
			case 2 :
				saveInfo.setStoryName("카딜라 스토리");
				break;
			case 3 :
				saveInfo.setStoryName("수정협곡 스토리");
				break;
			case 4 :
				saveInfo.setStoryName("레드로즈 스토리");
				break;
			case 5 :
				saveInfo.setStoryName("미라지포트 스토리");
				break;
		}
		saveInfo.setSaveTime(new SimpleDateFormat("yyyy년 MM월dd일 HH:mm에 저장").format(new Date()));
	}

	public void setNewGame() {
		loadNewGameInfo();
		assets.initializeUnitInfo();
	}

	public void saveNewGameInfo() {
		FileHandle handle;
		handle = Gdx.files.local(SAVEPATH + SaveVersion.NEW_GAME.toString() + "_" + CurrentInfo.CURRENT_APP_VERSION);
		Output output;
		try {
			Gdx.app.log("SaveManager", "save - " + handle.file().getParentFile().getAbsolutePath());
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gdx.app.log("SaveManager", "저장작업완료");
	}

	public void loadNewGameInfo() {
		FileHandle handle = Gdx.files.local(SAVEPATH + SaveVersion.NEW_GAME.toString() + "_"
				+ CurrentInfo.CURRENT_APP_VERSION);
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
			e.printStackTrace();
		}
	}

	public void save() {
		setSaveInfo();
		FileHandle handle = Gdx.files.local(SAVEPATH + saveInfo.getSaveVersion().toString() + "_"
				+ CurrentInfo.CURRENT_APP_VERSION);
		Preferences timePrefs = Gdx.app.getPreferences("Time");
		timePrefs.putInteger("Time", timeInfo.getSecondTime());
		timePrefs.flush();
		try {
			Gdx.app.log("SaveManager", "save - " + handle.file().getParentFile().getAbsolutePath());
			if (!handle.file().getParentFile().exists()) {
				handle.file().getParentFile().mkdirs();
				handle.file().createNewFile();
			}
			Gdx.app.log("SaveManager", "저장작업완료" + handle.file().getAbsolutePath());
			Output output = new Output(new FileOutputStream(handle.file()));
			kryo.writeObject(output, saveInfo);
			kryo.writeObject(output, partyInfo);
			kryo.writeObject(output, positionInfo);
			kryo.writeObject(output, timeInfo);
			kryo.writeObject(output, storySectionInfo);
			kryo.writeObject(output, eventInfo);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isLoadable(SaveVersion saveVersion) {
		return Gdx.files.local("SAVEPATH" + saveVersion.toString() + "_" + CurrentInfo.CURRENT_APP_VERSION).exists();
	}

	public void load(SaveVersion saveVersion) {
		assets.initializeUnitInfo();
		FileHandle handle = Gdx.files.local(SAVEPATH + saveVersion.toString() + "_" + CurrentInfo.CURRENT_APP_VERSION);
		Input input;
		try {
			input = new Input(new FileInputStream(handle.file()));
			BeanUtils.copyProperties(kryo.readObject(input, SaveInfo.class), saveInfo);
			BeanUtils.copyProperties(kryo.readObject(input, PartyInfo.class), partyInfo);
			BeanUtils.copyProperties(kryo.readObject(input, PositionInfo.class), positionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, TimeInfo.class), timeInfo);
			BeanUtils.copyProperties(kryo.readObject(input, StorySectionInfo.class), storySectionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, EventInfo.class), eventInfo);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		timeInfo.setTime(Gdx.app.getPreferences("Time").getInteger("Time"));
		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
		if (eventManager.getCurrentEvent() == null) {
			eventManager.setCurrentNpcEvent(new EventPacket("waiji", 1));
			eventManager.setCurrentStoryEvent(new EventPacket("prologue", 1));
		}
	}
	public SaveInfo readSaveInfo(SaveVersion saveVersion) {
		SaveInfo svInfo = null;

		try {
			svInfo = kryo.readObject(
					new Input(new FileInputStream(Gdx.files.local(
							SAVEPATH + saveVersion.toString() + "_" + CurrentInfo.CURRENT_APP_VERSION).file())),
					SaveInfo.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return svInfo;
	}

	public String readPartyInfo(int i) {
		if (i >= partyInfo.getPartyList().size())
			return "blank_face";
		else
			return partyInfo.getPartyList().get(i).getFacePath();
	}

}