package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.game.OneLevelHeroSpring;
import com.mygdx.screen.BGMScreen;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.BonusPointScreen;
import com.mygdx.screen.CGScreen;
import com.mygdx.screen.CollectionScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.EncounterScreen;
import com.mygdx.screen.EndingScreen;
import com.mygdx.screen.EventScreen;
import com.mygdx.screen.GreetingScreen;
import com.mygdx.screen.LoadScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.MovingScreen;
import com.mygdx.screen.OptionScreen;
import com.mygdx.screen.SaveScreen;
import com.mygdx.screen.StatusScreen;
import com.mygdx.screen.VillageScreen;
import com.mygdx.screen.WorldMapScreen;

@Component
public class ScreenFactory {
	private OneLevelHeroSpring oneLevelHeroSpring;
	@Autowired
	private BattleScreen battleScreen;
	@Autowired
	private BGMScreen bgmScreen;
	@Autowired
	private BonusPointScreen bonusPointScreen;
	@Autowired
	private CGScreen cgScreen;
	@Autowired
	private CollectionScreen collectionScreen;
	@Autowired
	private CreditScreen creditScreen;;
	@Autowired
	private EncounterScreen encounterScreen;
	@Autowired
	private EndingScreen endingScreen;
	@Autowired
	private EventScreen eventScreen;
	@Autowired
	private GreetingScreen greetingScreen;
	@Autowired
	private LoadScreen loadScreen;
	@Autowired
	private MenuScreen menuScreen;
	@Autowired
	private MovingScreen movingScreen;
	@Autowired
	private OptionScreen optionScreen;
	@Autowired
	private SaveScreen saveScreen;
	@Autowired
	private StatusScreen statusScreen;
	@Autowired
	private VillageScreen villageScreen;
	@Autowired
	private WorldMapScreen worldMapScreen;
	private IntMap<Screen> screens;

	public ScreenFactory() {
		screens = new IntMap<Screen>();
	}

	private Screen getScreenInstance(ScreenEnum screenEnum) {
		switch (screenEnum) {
			case BATTLE:
				return battleScreen;
			case BGM:
				return bgmScreen;
			case BONUS_POINT:
				return bonusPointScreen;
			case CG:
				return cgScreen;
			case COLLETION:
				return collectionScreen;
			case CREDIT:
				return creditScreen;
			case ENCOUNTER:
				return encounterScreen;
			case ENDING:
				return endingScreen;
			case EVENT:
				return eventScreen;
			case GREETING:
				return greetingScreen;
			case LOAD:
				return loadScreen;
			case MENU:
				return menuScreen;
			case MOVING:
				return movingScreen;
			case OPTION:
				return optionScreen;
			case SAVE:
				return saveScreen;
			case STATUS:
				return statusScreen;
			case VILLAGE:
				return villageScreen;
			case WORLD_MAP:
				return worldMapScreen;
			default:
				return menuScreen; //FIXME
		}
	}

	public void setGame(OneLevelHeroSpring oneLevelHeroSpring) {
		this.oneLevelHeroSpring = oneLevelHeroSpring;
	}

	public void show(ScreenEnum screenEnum) {
		if (oneLevelHeroSpring == null)
			return;
		if (!screens.containsKey(screenEnum.ordinal())) {
			screens.put(screenEnum.ordinal(), getScreenInstance(screenEnum));
		}
		oneLevelHeroSpring.setScreen(screens.get(screenEnum.ordinal()));
	}

	public void dispose(ScreenEnum screen) {
		if (!screens.containsKey(screen.ordinal()))
			return;
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose() {
		for (com.badlogic.gdx.Screen screen : screens.values()) {
			screen.dispose();
		}
		screens.clear();
	}

}
