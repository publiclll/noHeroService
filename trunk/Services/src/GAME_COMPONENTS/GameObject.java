package GAME_COMPONENTS;

import java.util.HashMap;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameElementType;
import GAME_ENUM.GameObjectType;
import GAME_INTERFACE.GAME_EVENT;
import GAME_INTERFACE.GAME_LOGICVALIDATOR;
import GAME_INTERFACE.GAME_NUMERICAL;

/**
 * 游戏基础类
 * @author publiclll
 *
 */
public class GameObject extends Object implements GAME_EVENT, GAME_NUMERICAL ,Cloneable ,GAME_LOGICVALIDATOR {
	
	/**
	 * 构造函数
	 */
	public GameObject(){}
	
	private String ID;
	private GameObjectType objType;
	private GameDataType objDbType;
	private GameElementType elementType;
	private int cfgID;
	protected Conditions conditions;
	private String PlayerID;
	/**
	 * 文字信息
	 */
	private String Name;
	private String Content;
	private int Level;
	private int LevelLimit;
	/**
	 * 经验
	 */
	private int exp;
	/**
	 * 经验上限
	 */
	private int expLimit;
	/**
	 * 是否为配置数据
	 */
	private boolean isCfgData;
	
	/**
	 * 创建ID
	 */
	protected void CreateID()
	{
		setID(IDCreator.getInstance().nextId()+"");
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public GameObjectType getObjType() {
		return objType;
	}

	public void setObjType(GameObjectType objType) {
		this.objType = objType;
	}

	public GameElementType getElementType() {
		return elementType;
	}

	public void setElementType(GameElementType elementType) {
		this.elementType = elementType;
	}

	@Override
	public String returnGameObjectID() {
		// TODO Auto-generated method stub
		return getID();
	}
	
	@Override
	public GameObjectType returnGameObjectType() {
		// TODO Auto-generated method stub
		return getObjType();
	}

	@Override
	public GameElementType returnGameElementType() {
		// TODO Auto-generated method stub
		return getElementType();
	}

	@Override
	public long currentCoin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentGold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentBonusAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nextAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nextBonusAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void levelUp_Complete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void wornEquip_Complete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void takeoffEquip_Complete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void useItem_Comlpete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void qualityUp_Complete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sell_Complete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buy_Complete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void useSkill_Complete(Object obj) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void charge_Complete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long currentExp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long currentExpLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nextExp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nextExpLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	public GameDataType getObjDbType() {
		return objDbType;
	}

	public void setObjDbType(GameDataType objDbType) {
		this.objDbType = objDbType;
	}

	@Override
	public GameDataType returnGameDataType() {
		// TODO Auto-generated method stub
		return getObjDbType();
	}

	public int getCfgID() {
		return cfgID;
	}

	public void setCfgID(int cfgID) {
		this.cfgID = cfgID;
	}


	@Override
	public int currentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getLevelLimit() {
		return LevelLimit;
	}

	public void setLevelLimit(int levelLimit) {
		LevelLimit = levelLimit;
	}

	public String getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(String playerID) {
		PlayerID = playerID;
	}

	@Override
	public void phaseUp_Complete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int currentDiamond() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentSkillPoint() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpLimit() {
		return expLimit;
	}

	public void setExpLimit(int expLimit) {
		this.expLimit = expLimit;
	}

	public boolean isCfgData() {
		return isCfgData;
	}

	public void setCfgData(boolean isCfgData) {
		this.isCfgData = isCfgData;
	}

	@Override
	public GameObject focusObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Equip> currentEquipList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Equip> nextEquipList() {
		// TODO Auto-generated method stub
		return null;
	}
}
