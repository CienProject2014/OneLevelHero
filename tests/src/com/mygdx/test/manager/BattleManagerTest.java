package com.mygdx.test.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
import com.mygdx.enums.BattleMessages;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.QuestManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.manager.UnitManager;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Status;
import com.mygdx.model.unit.Unit;
import com.mygdx.unitStrategy.HeroBattleStrategy;
import com.mygdx.unitStrategy.MonsterBattleStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BattleManagerTest {
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
	@InjectMocks
	private Assets assets;
	@InjectMocks
	private BattleManager battleManager = new BattleManager();
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
	private WorldMapAssets worldMapAssets;
	@Mock
	private NodeAssets worldNodeAssets;
	@Mock
	private TextureAssets textureAssets;
	@Mock
	private AssetsManager assetsManager;
	private Hero yongsa, parath, lilis;
	private List<Hero> battleMemberHeroList;
	private PriorityQueue<Unit> orderedUnits;

	@Before
	public void makeUnitStatus() {

		monster = new Monster();
		monster.setFacePath("mawang");
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

		orderedUnits = new PriorityQueue<>();
		orderedUnits.addAll(battleMemberHeroList);
		orderedUnits.add(monster);

		battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
	}

	@Test
	public void testStartBattleWithMonster() {
		battleManager.startBattleWithMonster(monster);
		assertEquals(monster, battleManager.getSelectedMonster());
		Mockito.verify(screenFactory).show(Matchers.eq(ScreenEnum.BATTLE));
	}

	@Test
	public void testInitializeProperties() {
		battleManager.initializeProperties();
		assertNotNull(battleManager.getGridHitbox());
		assertFalse(battleManager.isSkill());
	}

	@Test
	public void testInitializeBattleMember() {
		battleManager.initializeBattleMember(battleMemberHeroList, monster);
		assertEquals(monster.getName() + BattleMessages.MEET_MESSAGE, battleManager.getBattleDescriptionLabel());
		assertEquals(battleManager.getBattleMemberList().get(2).getName(), battleMemberHeroList.get(2).getName());
		assertEquals(battleManager.getOrderedUnits().poll().getFacePath(), "yongsa");
	}

	@Test
	public void testGetPickedActor() {
		// (1) 보통 상태 (스턴이 걸리지 않음)
		assertEquals(battleManager.getPickedActor(orderedUnits), yongsa);

		// (2) 첫 Actor인 용사가 스턴에 걸렸을때 -> 파라스가 스턴이 걸린다.
		Buff stunBuff = new Buff();
		stunBuff.setBuffPath("buff_de_sleep"); // 스턴이 걸리는 버프
		yongsa.getBuffList().add(stunBuff);
		assertEquals(battleManager.getPickedActor(orderedUnits), parath);
	}

	@Test
	public void testHasStunTypeBuff() {
		assertFalse(battleManager.hasStunTypeBuff(yongsa));
		Buff stunBuff = new Buff();
		stunBuff.setBuffPath("buff_de_sleep"); // 스턴이 걸리는 버프
		yongsa.getBuffList().add(stunBuff);
		assertTrue(battleManager.hasStunTypeBuff(yongsa));
	}
}
