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
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.VillageStage;

// EventScreen - VillageStage와 GameUiStage를 addActor()해서 보여주는 Screen
// 마을의 경우 multiplexer를 이용하여 2개의 화면을 교차로 보여준다.
public class VillageScreen implements Screen {

	private Image background;
	private SpriteBatch batch;
	private String villageName;

	private VillageStage villageStage;
	private GameUiStage gameUiStage;

	public static Stage inventoryStage;
	private InventoryActor inventoryActor;
	private int key = 2;

	private OrthographicCamera cam;

	private boolean state = true;

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public VillageStage getVillageStage() {
		return villageStage;
	}

	public void setVillageStage(VillageStage villageStage) {
		this.villageStage = villageStage;
	}

	public GameUiStage getGameUiStage() {
		return gameUiStage;
	}

	public void setGameUiStage(GameUiStage gameUiStage) {
		this.gameUiStage = gameUiStage;
	}

	public static Stage getInventoryStage() {
		return inventoryStage;
	}

	public static void setInventoryStage(Stage inventoryStage) {
		VillageScreen.inventoryStage = inventoryStage;
	}

	public InventoryActor getInventoryActor() {
		return inventoryActor;
	}

	public void setInventoryActor(InventoryActor inventoryActor) {
		this.inventoryActor = inventoryActor;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

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
		gameUiStage.draw();
		// villageStage.getCamera().translate(0, 2, 0);

		//카메라를 지속적으로 업데이트 해준다.
		villageStage.getCamera().update();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		gameUiStage = new GameUiStage();
		// 인벤토리 스테이지
		inventoryStage = new Stage();

		// 여러 스테이지에 인풋 프로세서를 동시에 할당한다
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, gameUiStage);
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
