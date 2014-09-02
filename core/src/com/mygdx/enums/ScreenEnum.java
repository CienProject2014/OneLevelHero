package com.mygdx.enums;

import com.badlogic.gdx.Screen;
import com.mygdx.screen.BGMScreen;
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
import com.mygdx.util.ScreenManager;

public enum ScreenEnum {

	MENU {
		public Screen getScreenInstance() {
			return new MenuScreen();
		}
	},
	OPTION {
		public Screen getScreenInstance() {
			return new OptionScreen();
		}
	},
	PROLOGUE {
		public Screen getScreenInstance() {
			return new PrologueScreen();
		}
	},
	VILLAGE {
		public Screen getScreenInstance() {
			ScreenManager.getInstance().dispose(VILLAGE);
			return new VillageScreen();
		}
	},
	WORLD_MAP {
		public Screen getScreenInstance() {
			return new WorldMapScreen();
		}
	},
	CREDIT {
		public Screen getScreenInstance() {
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
		public Screen getScreenInstance() {
			return new SaveScreen();
		}
	},
	BONUS {
		@Override
		public Screen getScreenInstance() {
			return new BonusPointScreen();
		}
	},
	LOAD {
		@Override
		public Screen getScreenInstance() {
			return new LoadScreen();
		}
	},
	EVENT {
		public Screen getScreenInstance() {
			return new EventScreen();
		}
	},
	MOVING {
		public Screen getScreenInstance() {
			return new MovingScreen();
		}
	},
	END {
		public Screen getScreenInstance() {
			return new EndingScreen();
		}
	},
	CG {
		public Screen getScreenInstance() {
			return new CGScreen();
		}
	},
	BGM {
		public Screen getScreenInstance() {
			return new BGMScreen();
		}
	},
	COLLETION {
		public Screen getScreenInstance() {
			return new CollectionScreen();
		}
	},
	STATUS {
		public Screen getScreenInstance() {
			return new StatusScreen();
		}
	};

	public abstract Screen getScreenInstance();

}
