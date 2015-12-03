package com.mygdx.test.manager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.Assets;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.ItemAssets;
import com.mygdx.assets.MusicAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.assets.TextureAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.controller.BattleCommandController;
import com.mygdx.enums.BattleMessages;
import com.mygdx.enums.BattleSituationEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.gdxtesting.OneLevelTestCase;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.CameraManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.QuestManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.manager.UnitManager;
import com.mygdx.model.battle.BattleUi;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.battle.DefendOnBattleCommand;
import com.mygdx.model.battle.GeneralAttackOnBattleCommand;
import com.mygdx.model.battle.UseItemOnBattleCommand;
import com.mygdx.model.battle.UseSkillOnBattleCommand;
import com.mygdx.model.battle.WaitOnBattleCommand;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Status;
import com.mygdx.model.unit.Unit;
import com.mygdx.popup.SkillRunPopup;
import com.mygdx.stage.BattleStage;
import com.mygdx.unitStrategy.HeroBattleStrategy;
import com.mygdx.unitStrategy.MonsterBattleStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BattleManagerTest extends OneLevelTestCase {
	@Mock
	private StorySectionManager storySectionManager;
	@Mock
	private BagManager bagManager;
	@Mock
	private MovingManager movingManager;
	@Mock
	private PartyManager partyManager;
	@Mock
	private AnimationManager animationManager;
	@Mock
	private ScreenFactory screenFactory;
	@Mock
	private UnitManager unitManager;
	@Mock
	private SkillRunPopup skillRunPopup;
	@Mock
	private transient TextureManager textureManager;
	@Mock
	private ConstantsAssets constantsAssets;
	@Mock
	private TimeManager timeManager;
	@Mock
	private MusicManager musicManager;
	@Mock
	private SoundManager soundManager;
	@Mock
	private QuestManager questManager;
	@Mock
	private Table battleCommandButtonTable;
	@InjectMocks
	private Assets assets;
	@InjectMocks
	private BattleManager battleManager = new BattleManager();
	@InjectMocks
	private HeroBattleStrategy heroBattleStrategy;
	private Monster monster;
	@Mock
	private MusicAssets musicAssets;
	@Mock
	private AtlasUiAssets atlasUiAssets;
	@Mock
	private UiComponentAssets uiComponentAssets;
	@Mock
	private EventAssets eventAssets;
	@Mock
	private ItemAssets itemAssets;
	@Mock
	private SkillAssets skillAssets;
	@Mock
	private UnitAssets unitAssets;
	@Mock
	private CameraManager cameraManager;
	@Mock
	private WorldMapAssets worldMapAssets;
	@Mock
	private NodeAssets worldNodeAssets;
	@Mock
	private TextureAssets textureAssets;
	@Mock
	private AssetsManager assetsManager;
	@Mock
	private BattleStage battleStage;
	@Mock
	private GeneralAttackOnBattleCommand generalAttackOnBattleCommand;
	@Mock
	private WaitOnBattleCommand waitOnBattleCommand;
	@Mock
	private DefendOnBattleCommand defendOnBattleCommand;
	@Mock
	private UseSkillOnBattleCommand useSkillOnBattleCommand;
	@Mock
	private UseItemOnBattleCommand useItemOnBattleCommand;
	@InjectMocks
	private BattleUi battleUi;
	@Mock
	private BattleCommandController battleCommandController;
	private Hero yongsa, parath, lilis;
	private List<Hero> battleMemberHeroList;
	private PriorityQueue<Unit> battleQueue;

	@BeforeClass
	public static void initGdx() {
		Gdx.app = mock(Application.class);

	}

	@Before
	public void makeUnitStatus() {
		monster = new Monster();
		monster.setFacePath("mawang");
		monster.setName("마왕");
		Status monsterStatus = new Status();
		monsterStatus.setAttack(42);
		monsterStatus.setMagicAttack(42);
		monsterStatus.setLevel(100);
		monsterStatus.setDefense(27);
		monsterStatus.setMagicDefense(27);
		monsterStatus.setHp(300);
		monsterStatus.setMaxHp(300);
		monsterStatus.setSpeed(0);
		monsterStatus.setExperience(0);
		monsterStatus.setMaxExperience(3600);
		monsterStatus.setFireResistance(0);
		monsterStatus.setWaterResistance(0);
		monsterStatus.setElectricResistance(0);
		monsterStatus.setCasting(0);
		monsterStatus.setExperience(0);
		monster.setStatus(monsterStatus);
		int hitArea[][] = {{0, 0, 0, 20, 20}, {0, 0, 10, 200, 10}, {0, 10, 200, 50, 0}, {20, 200, 50, 10, 0},
				{20, 10, 10, 10, 0}};
		monster.setHitArea(hitArea);
		monster.setBattleStrategy(new MonsterBattleStrategy());

		yongsa = new Hero();
		parath = new Hero();
		lilis = new Hero();

		yongsa.setFacePath("yongsa");
		yongsa.setName("용사");
		Status yongsaStatus = new Status();
		yongsaStatus.setAttack(42);
		yongsaStatus.setMagicAttack(42);
		yongsaStatus.setLevel(100);
		yongsaStatus.setDefense(27);
		yongsaStatus.setMagicDefense(27);
		yongsaStatus.setHp(300);
		yongsaStatus.setMaxHp(300);
		yongsaStatus.setSpeed(150);
		yongsaStatus.setExperience(3600);
		yongsaStatus.setFireResistance(0);
		yongsaStatus.setWaterResistance(0);
		yongsaStatus.setElectricResistance(0);
		yongsaStatus.setCasting(0);
		yongsa.setStatus(yongsaStatus);

		parath.setFacePath("parath");
		parath.setName("파라스");
		Status parathStatus = new Status();
		parathStatus.setAttack(42);
		parathStatus.setMagicAttack(42);
		parathStatus.setLevel(100);
		parathStatus.setDefense(27);
		parathStatus.setMagicDefense(27);
		parathStatus.setHp(300);
		parathStatus.setMaxHp(300);
		parathStatus.setSpeed(75);
		parathStatus.setExperience(3600);
		parathStatus.setFireResistance(0);
		parathStatus.setWaterResistance(0);
		parathStatus.setElectricResistance(0);
		parathStatus.setCasting(0);
		parath.setStatus(parathStatus);

		lilis.setFacePath("lilis");
		lilis.setName("리리스");
		Status lilisStatus = new Status();
		lilisStatus.setAttack(42);
		lilisStatus.setMagicAttack(42);
		lilisStatus.setLevel(100);
		lilisStatus.setDefense(27);
		lilisStatus.setMagicDefense(27);
		lilisStatus.setHp(300);
		lilisStatus.setMaxHp(300);
		lilisStatus.setSpeed(70);
		lilisStatus.setExperience(3600);
		lilisStatus.setFireResistance(0);
		lilisStatus.setWaterResistance(0);
		lilisStatus.setElectricResistance(0);
		lilisStatus.setCasting(0);
		lilis.setStatus(lilisStatus);

		HeroBattleStrategy heroBattleStrategy = new HeroBattleStrategy();
		yongsa.setBattleStrategy(heroBattleStrategy);
		parath.setBattleStrategy(heroBattleStrategy);
		lilis.setBattleStrategy(heroBattleStrategy);
		battleMemberHeroList = new ArrayList<Hero>();
		battleMemberHeroList.add(yongsa);
		battleMemberHeroList.add(parath);
		battleMemberHeroList.add(lilis);

		battleQueue = new PriorityQueue<>();
		battleQueue.addAll(battleMemberHeroList);
		battleQueue.add(monster);
		battleManager.setCurrentBattleSituation(BattleSituationEnum.NOT_IN_BATTLE);
	}
	@Test
	public void testStartBattleWithMonster() {
		battleManager.startBattleWithMonster(monster);
		assertEquals(monster, battleManager.getCurrentMonster());
		Mockito.verify(screenFactory).show(Matchers.eq(ScreenEnum.BATTLE));
	}

	@Test
	public void testInitializeBattle() {
		battleManager.initializeBattle(battleMemberHeroList, monster);
		assertEquals(monster.getName() + BattleMessages.MEET_MESSAGE, battleManager.getBattleDescriptionLabel());
		assertEquals(battleManager.getBattleList().get(2).getName(), battleMemberHeroList.get(2).getName());
		assertEquals(battleManager.getBattleQueue().size(), 4);
		assertEquals(battleManager.getBattleQueue().peek().getFacePath(), "yongsa");
		assertTrue(battleManager.getBattleFlag().isMonsterTurnEnd());
	}

	@Test
	public void testGetCurrentPickedActor() {
		initializeBattlePropertiesAndUnit();
		battleManager.initializeBattle(battleMemberHeroList, monster);
		assertEquals(battleManager.getCurrentActor().getFacePath(), "yongsa");
	}

	@Test
	public void testTurnOverCase1() {
		initializeBattlePropertiesAndUnit();
		// (1) 보통 상태 (스턴이 걸리지 않음) 용사 -> 파라스
		assertEquals(battleManager.getCurrentActor().getFacePath(), "yongsa");
		yongsa.setGauge(yongsa.getGauge() - 25); // 일반적인 공격
		battleManager.turnOver();
		assertEquals(battleManager.getCurrentActor().getFacePath(), "parath");
	}

	@Test
	public void testTurnOverCase2() {
		initializeBattlePropertiesAndUnit();
		// (2) 두번째 Actor인 파라스가 스턴에 걸렸을때 : 용사 -> 파라스(스턴) -> 리리스
		assertEquals(battleManager.getCurrentActor().getFacePath(), "yongsa");
		yongsa.setGauge(yongsa.getGauge() - 25); // 일반적인 공격
		Buff stunBuff = new Buff();
		stunBuff.setBuffPath("buff_de_sleep"); // 스턴이 걸리는 버프
		parath.getBuffList().add(stunBuff);
		battleManager.turnOver();
		assertEquals(battleManager.getCurrentActor().getFacePath(), "lilis");
	}

	@Test
	public void testTurnOverCase3() {
		initializeBattlePropertiesAndUnit();
		// 용사 -> 파라스 -> 리리스 -> 용사
		assertEquals(battleManager.getCurrentActor().getFacePath(), "yongsa");
		yongsa.setGauge(yongsa.getGauge() - 25);
		battleManager.turnOver();
		assertEquals(battleManager.getCurrentActor().getFacePath(), "parath");
		parath.setGauge(parath.getGauge() - 25);
		battleManager.turnOver();
		assertEquals(battleManager.getCurrentActor().getFacePath(), "lilis");
		lilis.setGauge(lilis.getGauge() - 25);
		battleManager.turnOver();
		assertEquals(battleManager.getCurrentActor().getFacePath(), "mawang");
		monster.setGauge(monster.getGauge() - 25);
		battleManager.turnOver();
		assertEquals(battleManager.getCurrentActor().getFacePath(), "yongsa");
	}

	@Test
	public void testHasStunTypeBuff() {
		initializeBattlePropertiesAndUnit();
		assertFalse(battleManager.hasStunTypeBuff(yongsa));
		Buff stunBuff = new Buff();
		stunBuff.setBuffPath("buff_de_sleep"); // 스턴이 걸리는 버프
		yongsa.getBuffList().add(stunBuff);
		assertTrue(battleManager.hasStunTypeBuff(yongsa));
	}

	@Test
	public void testHandleTurnEnd() {
		initializeBattlePropertiesAndUnit();
		battleManager.handleTurnEnd();
		assertTrue(battleManager.getBattleFlag().isBigUpdate());
		assertTrue(battleManager.getBattleFlag().isSmallUpdate());
		assertFalse(battleManager.getBattleFlag().isUsingSkill());
		assertTrue(battleManager.getBattleFlag().isMonsterTurnEnd());
	}

	public void initializeBattlePropertiesAndUnit() {
		battleManager.startBattleWithMonster(monster);
		battleManager.initializeBattle(battleMemberHeroList, monster);
	}

	@Test
	public void testSetBattleCommandButtonClickState() {
		initializeBattlePropertiesAndUnit();
		int preGauge = battleManager.getCurrentActor().getPreGauge();
		int preTime = timeManager.getPreTime();
		battleManager.setBattleCommandButtonClickState();
		assertFalse(battleManager.getBattleFlag().isShowGrid());
		assertEquals(battleManager.getCurrentActor().getGauge(), preGauge);
		Mockito.verify(timeManager).plusSecond(Matchers.eq(-preTime));
	}

	@Test
	public void testRunAway() {
		initializeBattlePropertiesAndUnit();
		battleManager.runAway();
		if (battleManager.getBattleInfo().getCurrentBattleSituation().equals(BattleSituationEnum.NOT_IN_BATTLE)) {
			Mockito.verify(movingManager).goCurrentLocatePosition();
		}
	}

	@Test
	public void testApplyGauge() {
		initializeBattlePropertiesAndUnit();
		// 첫턴 : 용사
		Unit unit = battleManager.getCurrentActor();
		int preGauge = unit.getGauge();
		battleManager.applyGauge(30);
		int calculateGauge = (int) (((double) (150 - unit.getActingPower()) / 50) * 30);
		assertEquals(unit.getGauge(), preGauge - calculateGauge);
		Mockito.verify(timeManager).plusSecond(
				(battleManager.getCostGaugeStrategy().getCostGauge()) * BattleManager.TIME_FLOW_RATE);
	}

	@Test
	public void testCheckCastingValue() {
		initializeBattlePropertiesAndUnit();
		yongsa.getStatus().setCasting(0);
		battleManager.checkCastingValue(yongsa);
		assertEquals(yongsa.getOverload(), 0);
		assertFalse(yongsa.getBuffList().contains("overload"));
	}

	@Test
	public void testReadyForAnimation() {
		// TODO
	}

	@Test
	public void testIsInBattle() {
		assertFalse(battleManager.isInBattle());
		initializeBattlePropertiesAndUnit();
		assertTrue(battleManager.isInBattle());
	}

	@Test
	public void setMonsterSize() {
		// TODO
	}

	@Test
	public void setCurrentBattleSituation() {
		assertEquals(battleManager.getBattleInfo().getCurrentBattleSituation(), BattleSituationEnum.NOT_IN_BATTLE);
		battleManager.setCurrentBattleSituation(BattleSituationEnum.IN_GAME);
		assertEquals(battleManager.getBattleInfo().getCurrentBattleSituation(), BattleSituationEnum.IN_GAME);
		Mockito.verify(musicManager).setMusicAndPlay(Matchers.eq("bgm_battle"));
	}

	@Test
	public void makeDropItem() {
		// DropItemStrategy
	}
}
