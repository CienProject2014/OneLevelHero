package com.mygdx.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mygdx.currentState.BattleInfo;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.enums.SaveVersion;

public class SaveManager {
	@Autowired
	CurrentInfo currentState;
	@Autowired
	PartyInfo partyInfo;
	@Autowired
	PositionInfo positionInfo;
	@Autowired
	TimeInfo timeInfo;
	@Autowired
	StorySectionInfo storySectionInfo;
	@Autowired
	BattleInfo battleInfo;
	@Autowired
	EventInfo eventInfo;

	private Kryo kryo;

	public SaveManager() {
		kryo = new Kryo();
		kryo.register(PartyInfo.class);
		kryo.register(PositionInfo.class);
		kryo.register(TimeInfo.class);
		kryo.register(StorySectionInfo.class);
		kryo.register(BattleInfo.class);
		kryo.register(EventInfo.class);
	}

	public void save() {
		FileHandle handle;
		if (currentState.getSaveVersion() == SaveVersion.NEW) {
			handle = Gdx.files.internal("save/SAVE1.json");
		} else {
			handle = Gdx.files.internal("save/" + currentState.getSaveVersion().toString() + ".json");
		}
		Output output;
		try {
			output = new Output(new FileOutputStream(handle.file()));
			kryo.writeObject(output, partyInfo);
			kryo.writeObject(output, positionInfo);
			kryo.writeObject(output, timeInfo);
			kryo.writeObject(output, storySectionInfo);
			kryo.writeObject(output, battleInfo);
			kryo.writeObject(output, eventInfo);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void load() {
		FileHandle handle = Gdx.files.internal("save/" + currentState.getSaveVersion().toString() + ".json");
		Input input;
		try {
			input = new Input(new FileInputStream(handle.file()));
			BeanUtils.copyProperties(kryo.readObject(input, PartyInfo.class), partyInfo);
			BeanUtils.copyProperties(kryo.readObject(input, PositionInfo.class), positionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, TimeInfo.class), timeInfo);
			BeanUtils.copyProperties(kryo.readObject(input, StorySectionInfo.class), storySectionInfo);
			BeanUtils.copyProperties(kryo.readObject(input, BattleInfo.class), battleInfo);
			BeanUtils.copyProperties(kryo.readObject(input, EventInfo.class), eventInfo);
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
