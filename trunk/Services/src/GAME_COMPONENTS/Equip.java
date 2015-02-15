package GAME_COMPONENTS;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameEquipAttr;
import GAME_ENUM.GameObjectType;
import GAME_INTERFACE.GAME_RESOURCELOSS;

/**
 * 装备类
 * @author publiclll
 *
 */
public class Equip extends GameObject implements GAME_RESOURCELOSS {
	public Equip()
	{
		/**
		 * 构造ID
		 */
		this.CreateID();
		setObjDbType(GameDataType.EQUIP);
		setObjType(GameObjectType.EQUIP);
	}
	
	/**
	 * 限制英雄等级，使用等级
	 */
	private int heroLevelLimit;
	
	/**
	 * 星级 ,就是品质
	 */
	private int starLevel;
	
	/**
	 * 游戏主加成属性
	 */
	private GameEquipAttr equipAttr;
	
	/**
	 * 基础
	 */
	private NumericalAttribute baseAttr;
	
	/**
	 * 装备合成配置ID
	 */
	private int  merge_cfgid;
	
	/**
	 * 解锁图库需要的玩家等级
	 */
	private int unlockpicsLevelLimit;
	/**
	 * 道具数量
	 */
	private int Count;
	
	/**
	 * 英雄ID
	 */
	private String HeroID;
	
	@Override
	public void wornEquip_Complete(Object obj) {
		Hero h = (Hero)obj;
		
		h.plusBufNumerical(this.getBaseAttr());
		
		setHeroID(h.getID());
		setObjDbType(GameDataType.HEROEQUIP);
	}

	/**
	 * 克隆
	 */
	public Equip clone()
	{
		Equip equip = null;
		try {
			equip = (Equip) super.clone();
			equip.setBaseAttr(getBaseAttr());
			equip.setHeroLevelLimit(getHeroLevelLimit());
			equip.setStarLevel(getStarLevel());
			equip.setEquipAttr(getEquipAttr());
			equip.setMerge_cfgid(getMerge_cfgid());
			equip.setUnlockpicsLevelLimit(getUnlockpicsLevelLimit());
			equip.setCount(getCount());
			equip.setHeroID(getHeroID());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equip;
	}

	public int getHeroLevelLimit() {
		return heroLevelLimit;
	}

	public void setHeroLevelLimit(int heroLevelLimit) {
		this.heroLevelLimit = heroLevelLimit;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public GameEquipAttr getEquipAttr() {
		return equipAttr;
	}

	public void setEquipAttr(GameEquipAttr equipAttr) {
		this.equipAttr = equipAttr;
	}

	public NumericalAttribute getBaseAttr() {
		return baseAttr;
	}

	public void setBaseAttr(NumericalAttribute baseAttr) {
		this.baseAttr = baseAttr;
	}

	public int getMerge_cfgid() {
		return merge_cfgid;
	}

	public void setMerge_cfgid(int merge_cfgid) {
		this.merge_cfgid = merge_cfgid;
	}

	public int getUnlockpicsLevelLimit() {
		return unlockpicsLevelLimit;
	}

	public void setUnlockpicsLevelLimit(int unlockpicsLevelLimit) {
		this.unlockpicsLevelLimit = unlockpicsLevelLimit;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public String getHeroID() {
		return HeroID;
	}

	public void setHeroID(String heroID) {
		HeroID = heroID;
	}

	@Override
	public long lossCoin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossGold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossDiamond() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossPlayerLevel() {
		// TODO Auto-generated method stub
		return getHeroLevelLimit();
	}

	@Override
	public int lossEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameObject conditionObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lossUpPhasePlayerLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossUpStarLevelPlayerLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
}
