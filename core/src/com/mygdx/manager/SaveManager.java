package com.mygdx.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.mygdx.store.Savable;

public class SaveManager {

	@Autowired
	private ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	public void save() {

		Map<String, Object> saveData = new HashMap<String, Object>();

		for (Entry<String, Savable> entry : applicationContext.getBeansOfType(
				Savable.class).entrySet()) {
			saveData.put(entry.getKey(), entry.getValue());
		}

		// TODO Serialize saveDatas

		// FileHandle handle = Gdx.files.internal("save/save_1.save");
	}
}
