package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.ScreenEnum;

/**
 * 기여자 목록을 뿌려주는 Screen 클래스
 * 
 * @author Velmont
 *
 */
public class CreditScreen extends RootScreen {
	private SpriteBatch batch;
	private Stage stage;
	private String event;

	public CreditScreen(String event) {
		this.event = event;
	}

	public CreditScreen() {}

	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();
		// scene.show(delta); // 배경 출력
		batch.end();

		stage.draw();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		showEventScene();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// if (scene.isNext()) {
				// scene.showNextScene();
				// } else {
				// // back to previous screen
				// that invoke this event screen

				// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
				screenFactory.show(ScreenEnum.MENU);

				// }
				return true;
			}
		});
	}

	private void showEventScene() {
		/*
		 * EventManager.getInstance().setEventCode("Crd-scene-1");
		 * EventManager.getInstance().setEventType(EventTypeEnum.CREDIT);
		 * 
		 * if (EventManager.getInstance().getEventType() ==
		 * EventTypeEnum.CREDIT) scene = new CreditScene(stage, batch,
		 * EventManager.getInstance().getEventCode()); else Gdx.app.log("Error",
		 * "Scene 주입 에러"); // 파싱을 하기 위한 로드 scene.load();
		 */
	}
}
