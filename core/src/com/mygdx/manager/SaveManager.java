package com.mygdx.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mygdx.currentState.BagInfo;
import com.mygdx.currentState.BattleInfo;
import com.mygdx.currentState.CurrentState;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.currentState.MusicInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.currentState.SoundInfo;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.currentState.TimeInfo;

public class SaveManager {

	@Autowired
	private CurrentState currentState;
	@Autowired
	private BagInfo bagInfo;
	@Autowired
	private BattleInfo battleInfo;
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private FieldInfo fieldInfo;
	@Autowired
	private MusicInfo musicInfo;
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private RewardInfo rewardInfo;
	@Autowired
	private SoundInfo soundInfo;
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private TimeInfo timeInfo;
	@Autowired
	private PositionManager positionManager;
	private Kryo kryo;
	private ApplicationContext context;

	public SaveManager() {
		kryo = new Kryo();
		kryo.register(CurrentState.class, 0);
		kryo.register(BagInfo.class, 1);
		kryo.register(EventInfo.class, 2);
		kryo.register(FieldInfo.class, 3);
		kryo.register(MusicInfo.class, 4);
		// kryo.register(BattleInfo.class);
		// kryo.register(PartyInfo.class);
		kryo.register(PositionInfo.class, 5);
		kryo.register(RewardInfo.class, 6);
		kryo.register(SoundInfo.class, 7);
		kryo.register(StorySectionInfo.class, 8);
		kryo.register(TimeInfo.class, 9);
		kryo.register(PositionManager.class, 10);
	}

	public void save() {
		FileHandle handle = Gdx.files.internal("save/save_1.json");
		Output output;
		try {
			output = new Output(new FileOutputStream(handle.file()));
			kryo.writeObject(output, currentState);
			kryo.writeObject(output, bagInfo);
			kryo.writeObject(output, eventInfo);
			kryo.writeObject(output, fieldInfo);
			kryo.writeObject(output, musicInfo);
			// kryo.writeObject(output, partyInfo);
			kryo.writeObject(output, positionManager);
			kryo.writeObject(output, positionInfo);
			kryo.writeObject(output, rewardInfo);
			kryo.writeObject(output, soundInfo);
			// kryo.writeObject(output, battleInfo);
			kryo.writeObject(output, storySectionInfo);
			kryo.writeObject(output, timeInfo);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void load() {
		FileHandle handle = Gdx.files.internal("save/save_1.json");
		Input input;
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		try {
			input = new Input(new FileInputStream(handle.file()));
			currentState = kryo.readObject(input, CurrentState.class);
			bagInfo = kryo.readObject(input, BagInfo.class);
			eventInfo = kryo.readObject(input, EventInfo.class);
			fieldInfo = kryo.readObject(input, FieldInfo.class);
			musicInfo = kryo.readObject(input, MusicInfo.class);
			// partyInfo = kryo.readObject(input, PartyInfo.class);
			// positionInfo = kryo.readObject(input, PositionInfo.class);
			positionManager = context.getBean(PositionManager.class);
			positionManager = kryo.readObject(input, PositionManager.class);
			positionInfo = context.getBean(PositionInfo.class);
			positionInfo = kryo.readObject(input, PositionInfo.class);
			rewardInfo = kryo.readObject(input, RewardInfo.class);
			soundInfo = kryo.readObject(input, SoundInfo.class);
			storySectionInfo = kryo.readObject(input, StorySectionInfo.class);
			timeInfo = kryo.readObject(input, TimeInfo.class);
			// battleInfo = kryo.readObject(input, BattleInfo.class);
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
