package com.mygdx.manager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.mygdx.store.Savable;

public class SaveManager {

	@Autowired
	private ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	public void save() {

		Map<String, Savable> saveData = new HashMap<String, Savable>();
		Kryo kryo = new Kryo();
		FileHandle handle = Gdx.files.internal("save/save_1.json");
		Output output;
		int i = 0;

		try {
			output = new Output(new FileOutputStream(handle.file()));
			for (Entry<String, Savable> entry : applicationContext.getBeansOfType(Savable.class).entrySet()) {
				saveData.put(entry.getKey(), entry.getValue());
				kryo.register(entry.getValue().getData().getClass(), i);
				kryo.writeObject(output, entry.getValue().getData());
				i++;
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
