package com.mygdx.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

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
	private Kryo kryo;

	public SaveManager() {
		kryo = new Kryo();
		kryo.register(CurrentState.class);
		kryo.register(BagInfo.class);
		kryo.register(EventInfo.class);
		kryo.register(FieldInfo.class);
		kryo.register(MusicInfo.class);
		// kryo.register(BattleInfo.class);
		// kryo.register(PartyInfo.class);
		kryo.register(PositionInfo.class);
		kryo.register(RewardInfo.class);
		kryo.register(SoundInfo.class);
		kryo.register(StorySectionInfo.class);
		kryo.register(TimeInfo.class);
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
			kryo.writeObject(output, positionInfo);
			kryo.writeObject(output, rewardInfo);
			kryo.writeObject(output, soundInfo);
			// kryo.writeObject(output, battleInfo);
			kryo.writeObject(output, storySectionInfo);
			kryo.writeObject(output, timeInfo);
			System.out.println("Save 성공");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void load() {
		FileHandle handle = Gdx.files.internal("save/save_1.json");
		Input input;

		try {
			input = new Input(new FileInputStream(handle.file()));
			kryo.readObject(input, CurrentState.class);
			kryo.readObject(input, BagInfo.class);
			kryo.readObject(input, EventInfo.class);
			kryo.readObject(input, FieldInfo.class);
			kryo.readObject(input, MusicInfo.class);
			// kryo.readObject(input, PartyInfo.class);
			kryo.readObject(input, PositionInfo.class);
			kryo.readObject(input, RewardInfo.class);
			kryo.readObject(input, SoundInfo.class);
			kryo.readObject(input, StorySectionInfo.class);
			kryo.readObject(input, TimeInfo.class);
			// kryo.readObject(input, BattleInfo.class);
			input.close();
			System.out.println("Load 성공");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
