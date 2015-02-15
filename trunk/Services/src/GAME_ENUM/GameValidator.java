package GAME_ENUM;

/**
 * 游戏验证类型
 * @author publiclll
 *
 */
public enum GameValidator {
	INVALID ,
	/**
	 * 够了
	 */
	ENOUGH , 
	/**
	 * 钱不够
	 */
	COIN_NOTENOUGH , 
	GOLD_NOTENOUGH , 
	/**
	 * 等级不够
	 */
	LEVEL_NOTENOUGH ,
	/**
	 * 钻石不够
	 */
	DIAMOND_NOTENOUGH ,
	/**
	 * 精力不够
	 */
	ENERY_NOTENOUGH ,
	
	/**
	 * 空指针
	 */
	NULLPOINT ,
	/**
	 * 装备已穿
	 */
	EQUIP_ISWORNED,
	/**
	 * 装备不能穿
	 */
	EQUIP_NOTWAREND ,
}
