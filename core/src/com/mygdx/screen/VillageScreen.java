package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.inventory.InventoryActor;
import com.mygdx.stage.GameStage;
import com.mygdx.stage.VillageStage;

public class VillageScreen implements Screen {

	Image background;
	SpriteBatch batch;
	String villageName;

	VillageStage villageStage;
	GameStage uiTable;

	public static Stage inventoryStage;
	InventoryActor inventoryActor;
	int key = 2;

	OrthographicCamera cam;

	boolean state = true;

	public VillageScreen(String villagename) {
		this.villageName = villagename;
	}

	public VillageScreen() {
		villageName = "Blackwood";
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		villageStage.draw();
		uiTable.draw();
		// villageStage.getCamera().translate(0, 2, 0);
		villageStage.getCamera().update();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		uiTable = new GameStage();
		// 인벤토리 스테이지
		inventoryStage = new Stage();

		// 여러 스테이지에 인풋 프로세서를 동시에 할당한다
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, uiTable);
		multiplexer.addProcessor(1, villageStage);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);

		// vs1.getCamera().translate(100, 100, 0);
		// vs1.getCamera().update();
		// vs2.addActor(UI);
	}

	@Override
	public void hide() {
		Gdx.app.log("DEBUG", "Village hide is called");
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		Gdx.app.log("DEBUG", "Village dispose is called");
		villageStage.dispose();
	}
}
