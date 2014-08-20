package com.mygdx.util;

import com.mygdx.resource.Assets;
import com.mygdx.screen.BonusPointScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.LoadScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.OptionScreen;
import com.mygdx.screen.PrologueScreen;
import com.mygdx.screen.SaveScreen;
import com.mygdx.screen.VillageScreen;
import com.mygdx.screen.WorldMapScreen;

public enum ScreenEnum {

	MAIN {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new MenuScreen();
		}
	},
	OPTION {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new OptionScreen();
		}
	},
	PROLOGUE {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new PrologueScreen();
		}
	},
	VILLAGE {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new VillageScreen(Assets.world);
		}
	},
	WORLD {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new WorldMapScreen();
		}
	},
	CREDIT {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new CreditScreen();
		}
	},
	EXTRA {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new OptionScreen();
		}
	},
	SAVE {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new SaveScreen();
		}
	},
	BONUS {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new BonusPointScreen();
		}
	},
	LOAD {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new LoadScreen();
		}
	};

	protected abstract com.badlogic.gdx.Screen getScreenInstance();

}
