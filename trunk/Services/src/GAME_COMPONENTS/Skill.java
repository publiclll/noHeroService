package GAME_COMPONENTS;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameHeroSkillAttr;
import GAME_ENUM.GameObjectType;

/**
 * 技能
 * @author publiclll
 *
 */
public class Skill extends GameObject {
	
	public Skill()
	{
		/**
		 * 构造ID
		 */
		this.CreateID();
		setObjDbType(GameDataType.SKILL);
		setObjType(GameObjectType.SKILL);
		setUsed(false);
	}
	/**
	 * 技能属性
	 */
	private GameHeroSkillAttr skillAttr;
	/**
	 * 基本数值属性
	 */
	private NumericalAttribute baseAttr;
	/**
	 * 限制英雄等级，开启等级
	 */
	private int heroLevelLimit;
	/**
	 * 效果编号
	 */
	private int effect;
	/**
	 * 升级所需金币
	 */
	private int upNeedCoin;
	/**
	 * 预留参数
	 */
	private double parameter1;
	private double parameter2;
	private double parameter3;
	private double parameter4;
	/**
	 * 是否被激活
	 */
	private boolean isUsed;
	
	private String HeroID;
	
	public Skill clone()
	{
		Skill s = null;
		try {
			s = (Skill) super.clone();
			s.setCfgID(this.getCfgID());
			s.setName(this.getName());
			s.setContent(this.getContent());
			s.setSkillAttr(this.getSkillAttr());
			s.setLevel(this.getLevel());
			s.setLevelLimit(this.getLevelLimit());
			s.setHeroLevelLimit(this.getHeroLevelLimit());
			s.setUpNeedCoin(this.getUpNeedCoin());
			s.setBaseAttr(this.getBaseAttr());
			s.setEffect(this.getEffect());
			s.setUsed(this.isUsed());
			s.setParameter1(this.getParameter1());
			s.setParameter2(this.getParameter2());
			s.setParameter3(this.getParameter3());
			s.setParameter4(this.getParameter4());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public GameHeroSkillAttr getSkillAttr() {
		return skillAttr;
	}

	public void setSkillAttr(GameHeroSkillAttr skillAttr) {
		this.skillAttr = skillAttr;
	}

	public NumericalAttribute getBaseAttr() {
		return baseAttr;
	}

	public void setBaseAttr(NumericalAttribute baseAttr) {
		this.baseAttr = baseAttr;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	public double getParameter1() {
		return parameter1;
	}

	public void setParameter1(double parameter1) {
		this.parameter1 = parameter1;
	}

	public double getParameter2() {
		return parameter2;
	}

	public void setParameter2(double parameter2) {
		this.parameter2 = parameter2;
	}

	public double getParameter3() {
		return parameter3;
	}

	public void setParameter3(double parameter3) {
		this.parameter3 = parameter3;
	}

	public double getParameter4() {
		return parameter4;
	}

	public void setParameter4(double parameter4) {
		this.parameter4 = parameter4;
	}

	public int getUpNeedCoin() {
		return upNeedCoin;
	}

	public void setUpNeedCoin(int upNeedCoin) {
		this.upNeedCoin = upNeedCoin;
	}

	public int getHeroLevelLimit() {
		return heroLevelLimit;
	}

	public void setHeroLevelLimit(int heroLevelLimit) {
		this.heroLevelLimit = heroLevelLimit;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getHeroID() {
		return HeroID;
	}

	public void setHeroID(String heroID) {
		HeroID = heroID;
	}
}
