package com.mygdx.enums;

import com.mygdx.screen.BGMScreen;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.BonusPointScreen;
import com.mygdx.screen.CGScreen;
import com.mygdx.screen.CollectionScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.EndingScreen;
import com.mygdx.screen.EventScreen;
import com.mygdx.screen.LoadScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.MovingScreen;
import com.mygdx.screen.OptionScreen;
import com.mygdx.screen.PrologueScreen;
import com.mygdx.screen.SaveScreen;
import com.mygdx.screen.StatusScreen;
import com.mygdx.screen.VillageScreen;
import com.mygdx.screen.WorldMapScreen;

public enum ScreenEnum {

	MENU {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new MenuScreen();
		}
	},
	OPTION {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new OptionScreen();
		}
	},
	PROLOGUE {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new PrologueScreen();
		}
	},
	VILLAGE {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new VillageScreen();
		}
	},
	WORLD {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new WorldMapScreen();
		}
	},
	CREDIT {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new CreditScreen();
		}
	},
	EXTRA {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new CollectionScreen();
		}
	},
	SAVE {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new SaveScreen();
		}
	},
	BONUS {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new BonusPointScreen();
		}
	},
	LOAD {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new LoadScreen();
		}
	},
	EVENT {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new EventScreen();
		}
	},
	MOVING {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new MovingScreen();
		}
	},
	END {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new EndingScreen();
		}
	},
	CG {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new CGScreen();
		}
	},
	BGM {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new BGMScreen();
		}
	},
	COLLETION {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new CollectionScreen();
		}
	},
	STATUS {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new StatusScreen();
		}
	},
	BATTLE {
		@Override
		public com.badlogic.gdx.Screen getScreenInstance() {
			return new BattleScreen();
		}
	};

	public abstract com.badlogic.gdx.Screen getScreenInstance();

}
