package GAME_COMPONENTS.CONDITIONS;

import GAME_COMPONENTS.NumericalAttribute;

/**
 * Ó¢ÐÛ½ø½×Ìõ¼þ
 * @author LLL
 *
 */
public class HeroUpPhase {
	private int cfgId;
	private String[] equipCfgIdArray;
	private int heroLevelLimit;
	private int needCoint;
	private int focusPhase;
	private int targetPhase;
	private NumericalAttribute baseAttr;
	public int getCfgId() {
		return cfgId;
	}
	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}
	public String[] getEquipCfgIdArray() {
		return equipCfgIdArray;
	}
	public void setEquipCfgIdArray(String[] equipCfgIdArray) {
		this.equipCfgIdArray = equipCfgIdArray;
	}
	public int getHeroLevelLimit() {
		return heroLevelLimit;
	}
	public void setHeroLevelLimit(int heroLevelLimit) {
		this.heroLevelLimit = heroLevelLimit;
	}
	public int getNeedCoint() {
		return needCoint;
	}
	public void setNeedCoint(int needCoint) {
		this.needCoint = needCoint;
	}
	public int getTargetPhase() {
		return targetPhase;
	}
	public void setTargetPhase(int targetPhase) {
		this.targetPhase = targetPhase;
	}
	public NumericalAttribute getBaseAttr() {
		return baseAttr;
	}
	public void setBaseAttr(NumericalAttribute baseAttr) {
		this.baseAttr = baseAttr;
	}
	public int getFocusPhase() {
		return focusPhase;
	}
	public void setFocusPhase(int focusPhase) {
		this.focusPhase = focusPhase;
	}
}
