package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.stage.TouchPadStage;
import com.mygdx.stage.WorldMapStage;
import com.mygdx.state.Assets;

public class WorldMapScreen2 implements Screen {
	private WorldMapStage worldMapStage;
	private TouchPadStage touchPadStage;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private InputMultiplexer multiplexer;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

	    cam.update();
		batch.end();
		 touchPadStage.act(Gdx.graphics.getDeltaTime());  
		worldMapStage.draw();
		touchPadStage.draw();

	}

	@Override
	public void resize(int width, int height) {


	}

	@Override
	public void show() {

		worldMapStage = new WorldMapStage();
		touchPadStage = new TouchPadStage();
		InputProcessor MapInputProcessor = new MapInputProcessor();
		multiplexer = new InputMultiplexer();
		cam = new OrthographicCamera();
		
		cam.setToOrtho(false, Assets.realWidth, Assets.realHeight);
		worldMapStage.getViewport().setCamera(cam);		
		
		multiplexer.addProcessor(0,touchPadStage);
		multiplexer.addProcessor(1,MapInputProcessor);
		
		batch = new SpriteBatch();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}
	
	public class MapInputProcessor implements InputProcessor {
	    Vector3 last_touch_down = new Vector3();

	   
	    public boolean touchDragged(int x, int y, int pointer) {
	        moveCamera( x, y ); 
	        return false;
	    }

	    private void moveCamera( int touch_x, int touch_y ) {
	        Vector3 new_position = getNewCameraPosition( touch_x, touch_y );

	        if( !cameraOutOfLimit( new_position ) ){
	            worldMapStage.getCamera().translate( new_position.sub( worldMapStage.getCamera().position ) );
	            
	        }
	        	
	        last_touch_down.set( touch_x, touch_y, 0);
	    }

	    private Vector3 getNewCameraPosition( int x, int y ) {
	        Vector3 new_position = last_touch_down;
	        new_position.sub(x, y, 0);
	        new_position.y = -new_position.y;
	        new_position.add( worldMapStage.getCamera().position );

	        return new_position;
	    }

	    private boolean cameraOutOfLimit( Vector3 position ) {
	        int x_left_limit = (int) (Assets.realWidth / 2);
	        int x_right_limit = (int) (3000-(Assets.realWidth / 2));
	        int y_bottom_limit = (int) (Assets.realHeight /2);
	        int y_top_limit = (int) (1688-(Assets.realHeight / 2));
	        

	        if( position.x < x_left_limit || position.x > x_right_limit )
	            return true;
	        else if( position.y < y_bottom_limit || position.y > y_top_limit )
	            return true;
	        else
	          return false;
	}

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			last_touch_down.set(screenX, screenY, 0);
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}

	}

}
