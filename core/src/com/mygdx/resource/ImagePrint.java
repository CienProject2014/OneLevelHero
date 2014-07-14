package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class ImagePrint {
	Image image;
	Table table;
	
	public ImagePrint(Table table) {
		this.table = table;
		System.out.println("table is loaded");
	}
	
	public void show(Image image, Pos pos) {
		switch(pos){
		case TOPLEFT: // 위 왼쪽
			table.add(image).expand().top().left();
			break;
		case TOPCENTER: // 위 가운데
			table.add(image).expand().top().center();
			break;
		case TOPRIGHT: // 위 오른쪽
			table.add(image).expand().top().right();
			break;
		case BOTTOMLEFT: // 아래 왼쪽
			table.row();
			table.add(image).expand().bottom().left();
			break;
		case BOTTOMCENTER: // 아래 가운데
			table.row();
			table.add(image).expand().bottom().center();
			break;
		case BOTTOMRIGHT: // 아래 오른쪽
			table.row();
			table.add(image).expand().bottom().right();
			break;
		}
	}
}
