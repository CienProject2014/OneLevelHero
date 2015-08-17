package com.mygdx.stage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ItemEnum;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.unit.Hero;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class InventoryStage extends BaseOverlapStage {
	public static final String SCENE_NAME = "inventory_scene";
	public final String CHARACTER_STATUS_IMAGE = "character_status_image";
	public final String CHARACTER_IMAGE = "normal";
	private final String INVENTORY_ITEM_IMAGE = "inventory_item_image";
	private final String INVENTORY_ITEM_LABEL = "inventory_item_label";
	private final String INVENTORY_ACTIVATE_LINE = "inventory_activate_line";
	private final String INVENTORY_LIGHT_BUTTON = "inventory_light_button";
	private final String DEFAULT_VISIBILTY = "Default";
	private final String PRESSED_VISIBILTY = "pressed";
	private final int CHACRACTER_STATUS_SIZE = 4;
	private final int NUMBER_OF_EQUIPMENT = 4;
	private final int ITEM_SLOT_SIZE = 6;
	private ItemEnum.Inventory inventoryState;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventCheckManager eventCheckManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private BagManager bagManager;
	private Map<String, Array<String>> sceneConstants;
	private Hero currentSelectedHero;
	private int pageNumber;
	private Camera cam;

	public Stage makeStage() {
		sceneConstants = constantsAssets.getSceneConstants("inventory_scene");
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());
		currentSelectedHero = partyManager.getCurrentSelectedHero();
		setCamera();
		setBackground(sceneConstants);
		setActivateLine(bagManager, sceneConstants);
		setEquipmentScene(sceneConstants, pageNumber);
		setPageButton(sceneConstants);
		setTagButton(ItemEnum.Inventory.EQUIPMENT);
		setPageLight(sceneConstants, pageNumber);
		setCharacterStatusImage(partyManager, sceneConstants);
		return this;
	}

	private void setBackground(Map<String, Array<String>> sceneConstants) {
		ImageItem background = sceneLoader.getRoot().getImageById(
				sceneConstants.get("background").get(0));
		background.setTouchable(Touchable.enabled);
	}

	public void act(float delta) {
		setEquipmentScene(sceneConstants, pageNumber);
		setTagButton(ItemEnum.Inventory.EQUIPMENT);
		setPageLight(sceneConstants, pageNumber);
		setCharacterStatusImage(partyManager, sceneConstants);
	}

	public void setCompositeItemVisibilty(CompositeItem compositeItem,
			String visibilty) {
		if (visibilty == PRESSED_VISIBILTY) {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, true);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, false);
		} else {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, true);
		}
	}

	private void setPageButton(Map<String, Array<String>> sceneConstants) {
		final CompositeItem rightButton = sceneLoader.getRoot()
				.getCompositeById("right_button");
		final CompositeItem leftButton = sceneLoader.getRoot()
				.getCompositeById("left_button");
		setCompositeItemVisibilty(rightButton, DEFAULT_VISIBILTY);
		setCompositeItemVisibilty(leftButton, DEFAULT_VISIBILTY);
		leftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setCompositeItemVisibilty(leftButton, PRESSED_VISIBILTY);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (pageNumber > 0) {
					setPageNumber(getPageNumber() - 1);
				}
				setCompositeItemVisibilty(rightButton, DEFAULT_VISIBILTY);
			}
		});
		rightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setCompositeItemVisibilty(rightButton, PRESSED_VISIBILTY);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (pageNumber < 3) {
					setPageNumber(getPageNumber() + 1);
				}
				setCompositeItemVisibilty(leftButton, DEFAULT_VISIBILTY);
			}
		});
	}

	private void setPageLight(Map<String, Array<String>> sceneConstants,
			int pageNumber) {
		Array<String> inventoryLightButtonList = sceneConstants
				.get(INVENTORY_LIGHT_BUTTON);
		for (int i = 0; i < 4; i++) {
			CompositeItem pageLight = sceneLoader.getRoot().getCompositeById(
					inventoryLightButtonList.get(i));
			if (i == pageNumber) {
				setCompositeItemVisibilty(pageLight, PRESSED_VISIBILTY);
			} else {
				setCompositeItemVisibilty(pageLight, DEFAULT_VISIBILTY);
			}
		}
	}

	private void setEquipmentScene(Map<String, Array<String>> sceneConstants,
			int pageNumber) {
		Array<String> inventoryItemImageList = sceneConstants
				.get(INVENTORY_ITEM_IMAGE);
		Array<String> inventoryLabelList = sceneConstants
				.get(INVENTORY_ITEM_LABEL);
		for (int i = pageNumber * ITEM_SLOT_SIZE; i < (pageNumber + 1)
				* ITEM_SLOT_SIZE; i++) {
			setEquippedItemList(bagManager, inventoryItemImageList,
					inventoryLabelList, i);
			setBagItemList(bagManager, inventoryItemImageList,
					inventoryLabelList, i);
		}
	}

	private void setTagButton(ItemEnum.Inventory inventoryState) {
		CompositeItem equipTag = sceneLoader.getRoot().getCompositeById(
				"equip_tag");
		CompositeItem consumablesTag = sceneLoader.getRoot().getCompositeById(
				"use_tag");
		CompositeItem etcItemTag = sceneLoader.getRoot().getCompositeById(
				"etc_tag");
		switch (inventoryState) {
			case EQUIPMENT :
				setCompositeItemVisibilty(equipTag, PRESSED_VISIBILTY);
				setCompositeItemVisibilty(consumablesTag, DEFAULT_VISIBILTY);
				setCompositeItemVisibilty(etcItemTag, DEFAULT_VISIBILTY);
				break;
			case CONSUMABLES :
				setCompositeItemVisibilty(equipTag, DEFAULT_VISIBILTY);
				setCompositeItemVisibilty(consumablesTag, PRESSED_VISIBILTY);
				setCompositeItemVisibilty(etcItemTag, DEFAULT_VISIBILTY);
				break;
			case ETC_ITEM :
				setCompositeItemVisibilty(equipTag, DEFAULT_VISIBILTY);
				setCompositeItemVisibilty(consumablesTag, DEFAULT_VISIBILTY);
				setCompositeItemVisibilty(etcItemTag, PRESSED_VISIBILTY);
				break;
		}
	}

	private void setActivateLine(BagManager bagManager,
			Map<String, Array<String>> sceneConstants) {
		for (int i = 0; i < ITEM_SLOT_SIZE; i++) {
			Array<String> activateLineList = sceneConstants
					.get(INVENTORY_ACTIVATE_LINE);
			final ImageItem activateLineItem = sceneLoader.getRoot()
					.getImageById(activateLineList.get(i));
			activateLineItem.setVisible(false);
			activateLineItem.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (!activateLineItem.isVisible()) {
						activateLineItem.setVisible(true);
					}
				}
			});
		}
	}
	private boolean isFirstPage() {
		if (pageNumber == 0) {
			return true;
		} else {
			return false;
		}
	}
	private void setCharacterStatusImage(PartyManager partyManager,
			Map<String, Array<String>> sceneConstants) {
		Array<String> characterStatusList = sceneConstants
				.get(CHARACTER_STATUS_IMAGE);
		for (int i = 0; i < CHACRACTER_STATUS_SIZE; i++) {
			CompositeItem compositeItem = sceneLoader.getRoot()
					.getCompositeById(characterStatusList.get(i));
			ImageItem characterStatusImage = compositeItem
					.getImageById(CHARACTER_IMAGE);
			if (partyManager.getPartyList().size() > i) {
				final Hero imageHero = partyManager.getPartyList().get(i);
				characterStatusImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(TextureManager
								.getStatusTexture(imageHero.getFacePath()))));
				characterStatusImage.setTouchable(Touchable.enabled);
				characterStatusImage.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						setCurrentSelectedHero(imageHero);
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
					}
				});
				ImageItem highLightImage = compositeItem
						.getImageById("pressed");
				if (imageHero == currentSelectedHero) {
					highLightImage.setVisible(true);
				} else {
					highLightImage.setVisible(false);
				}
			} else {
				compositeItem.setVisible(false);
			}
		}
	}
	private void setEquippedItemList(BagManager bagManager,
			Array<String> inventoryItemImageList,
			Array<String> inventoryLabelList, int index) {
		Array<String> inventoryList = currentSelectedHero.getInventory()
				.getInventoryList();
		ImageItem equipPartsLabel = sceneLoader.getRoot().getImageById(
				"character_equip_parts");
		if (isFirstPage()) {
			if (index != 4 && index != 5) {
				ImageItem imageItem = sceneLoader.getRoot().getImageById(
						inventoryItemImageList.get(index));
				String itemPath = currentSelectedHero
						.getInventory()
						.getEquipment(
								ItemEnum.EquipmentPart
										.findEquipmentPart(inventoryList
												.get(index))).getItemPath();

				imageItem.setDrawable((new TextureRegionDrawable(
						new TextureRegion(TextureManager
								.getItemTexture(itemPath)))));
				imageItem.setVisible(true);
				imageItem.setTouchable(Touchable.disabled);
				LabelItem labelItem = sceneLoader.getRoot().getLabelById(
						inventoryLabelList.get(index));
				labelItem.setText(currentSelectedHero
						.getInventory()
						.getEquipment(
								ItemEnum.EquipmentPart
										.findEquipmentPart(inventoryList
												.get(index))).getName());
				labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(),
						Color.WHITE));
				labelItem.setFontScale(1.0f);
				labelItem.setVisible(true);
				labelItem.setTouchable(Touchable.disabled);
			}
			equipPartsLabel.setVisible(true);
		} else {
			equipPartsLabel.setVisible(false);
		}
	}

	private void setBagItemList(BagManager bagManager,
			Array<String> inventoryItemImageList,
			Array<String> inventoryLabelList, int index) {
		if (isFirstPage()) {
			if (index == 4 || index == 5) {
				if (bagManager.getEquipmentList().size() > index
						- NUMBER_OF_EQUIPMENT) {
					setBagItemImageAndLabel(bagManager, inventoryItemImageList,
							inventoryLabelList, index);
				} else {
					LabelItem labelItem = sceneLoader.getRoot().getLabelById(
							inventoryLabelList.get(index));
					labelItem.setVisible(false);
				}
			}
		} else {
			if (bagManager.getEquipmentList().size() > index
					- NUMBER_OF_EQUIPMENT) {
				setBagItemImageAndLabel(bagManager, inventoryItemImageList,
						inventoryLabelList, index);
			} else {
				setVoidItemImageAndLabel(inventoryItemImageList,
						inventoryLabelList, index);
			}
		}
	}

	private void setVoidItemImageAndLabel(Array<String> inventoryItemImageList,
			Array<String> inventoryLabelList, int index) {
		ImageItem inventoryItemImage = sceneLoader.getRoot().getImageById(
				inventoryItemImageList.get(index
						% (pageNumber * ITEM_SLOT_SIZE)));
		inventoryItemImage.setVisible(false);
		LabelItem labelItem = sceneLoader.getRoot().getLabelById(
				inventoryLabelList.get(index % (pageNumber * ITEM_SLOT_SIZE)));
		labelItem.setVisible(false);
	}

	private void setBagItemImageAndLabel(BagManager bagManager,
			Array<String> inventoryItemImageList,
			Array<String> inventoryLabelList, int index) {
		int dividedIndex;
		if (isFirstPage()) {
			dividedIndex = index;
		} else {
			dividedIndex = index % (pageNumber * ITEM_SLOT_SIZE);
		}
		ImageItem inventoryItemImage = sceneLoader.getRoot().getImageById(
				inventoryItemImageList.get(dividedIndex));
		String itemPath = bagManager.getEquipmentList()
				.get(index - NUMBER_OF_EQUIPMENT).getItemPath();
		inventoryItemImage.setDrawable((new TextureRegionDrawable(
				new TextureRegion(TextureManager.getItemTexture(itemPath)))));
		inventoryItemImage.setVisible(true);
		LabelItem labelItem = sceneLoader.getRoot().getLabelById(
				inventoryLabelList.get(dividedIndex));
		labelItem.setText(bagManager.getEquipmentList()
				.get(index - NUMBER_OF_EQUIPMENT).getName());
		labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(),
				Color.WHITE));
		labelItem.setFontScale(1.0f);
		labelItem.setVisible(true);
		labelItem.setTouchable(Touchable.disabled);
	}
	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH,
				StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}

	public ItemEnum.Inventory getInventoryState() {
		return inventoryState;
	}

	public void setInventoryState(ItemEnum.Inventory inventoryState) {
		this.inventoryState = inventoryState;
	}

	public Hero getCurrentSelectedHero() {
		return currentSelectedHero;
	}

	public void setCurrentSelectedHero(Hero currentSelectedHero) {
		this.currentSelectedHero = currentSelectedHero;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
