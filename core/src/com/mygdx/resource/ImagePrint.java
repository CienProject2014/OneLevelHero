/*
 * batch.draw함수의 좌표축은 좌측 아래가 원점. 
 * Gdx.graphics.getWidth는 창의 가로크기
 * Gdx.graphics.getHeight는 창의 세로 크기
 */

package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;


public class ImagePrint extends Group{
	SpriteBatch batch;
	
	public ImagePrint(SpriteBatch batch) {
		this.batch = batch;
		System.out.println("table is loaded");
	}
	
	public void addTexture(Texture texture) {
		
	}
	
	public void show(Texture texture, Pos pos) {
		System.out.println(Gdx.graphics.getWidth()+", "+Gdx.graphics.getHeight());
		System.out.println(texture.getWidth()+", "+texture.getHeight());
		switch(pos){
		case TOPLEFT: // 위 왼쪽
			batch.draw(texture, 0, Gdx.graphics.getHeight()-texture.getHeight());
		case TOPCENTER: // 위 가운데
			batch.draw(texture, (Gdx.graphics.getWidth()-texture.getWidth())/2, 
					Gdx.graphics.getHeight()-texture.getHeight());
			break;
		case TOPRIGHT: // 위 오른쪽
			batch.draw(texture, Gdx.graphics.getWidth()-texture.getWidth(), 
					Gdx.graphics.getHeight()-texture.getHeight());
			break;
		case BOTTOMLEFT: // 아래 왼쪽
			batch.draw(texture, 0, 0);
			break;
		case BOTTOMCENTER: // 아래 가운데
			batch.draw(texture, (Gdx.graphics.getWidth()-texture.getWidth())/2, 0);
			break;
		case BOTTOMRIGHT: // 아래 오른쪽
			batch.draw(texture, Gdx.graphics.getWidth()-texture.getWidth(), 0);
			break;
		}
	}
	
	public void clear() {
		
	}
}
