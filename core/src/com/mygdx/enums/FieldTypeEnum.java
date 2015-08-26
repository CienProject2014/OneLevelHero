package com.mygdx.enums;

public enum FieldTypeEnum {
	CRYSTALLIZED_VALLEY("crystallized_valley"), RANGE_OF_A_MOUNTAIN_TITANIA(
			"range_of_a_mountain_titania"), MOUNTAIN_TIANIA("mountain_titania"), CAVE_OF_SUCCUBUS(
					"cave_of_succubus"), GOLDEN_LAKE("golden_lake"), CAVE_OF_CAPE_OF_TEMPEST(
							"cave_of_cape_of_tempest"), ANCIENT_LIBRARY_B1("ancient_library_b1"), ANCIENT_LIBRARY_B2(
									"ancient_library_b2"), ANCIENT_LIBRARY_B3(
											"ancient_library_b3"), TEMPLE_OF_MOERAE_B12(
													"temple_of_moerae_b12"), DEVIL_CASTLE_1F(
															"devil_castle_1f"), DEVIL_CASTLE_2F(
																	"devil_castle_2f"), DEVIL_CASTLE_3F(
																			"devil_castle_3f"), BLACKWOOD_MOUNTAIN(
																					"blackwood_mountain"), KADILLA_MOUNTAIN(
																							"kadilla_mountain"), ELVEN_FOREST(
																									"elven_forest"), BLACKWOOD_FOREST(
																											"blackwood_forest"), BRIDGE(
																													"bridge"), GRASSLAND(
																															"grassland"), BEACH(
																																	"beach"), SEA(
																																			"sea"), DUNGEON(
																																					"dungeon");
	private String fieldType;
	private FieldTypeEnum(String fieldType) {
		this.fieldType = fieldType;
	}

	public String toString() {
		return fieldType;
	}
}