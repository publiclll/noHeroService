package GAME_INTERFACE;

import java.util.HashMap;

import GAME_COMPONENTS.Equip;

/**
 * 游戏数值接口，用于计算产生各种攻击，防御，闪躲，移动等等数值
 * @author publiclll
 *
 */
public interface GAME_NUMERICAL extends GAME_BASEINTERFACE {
	/**
	 * 当前银子
	 * @return
	 */
	public long currentCoin();
	/**
	 * 当前金币
	 * @return
	 */
	public int currentGold();
	/**
	 * 当前钻石
	 * @return
	 */
	public int currentDiamond();
	/**
	 * 当前等级
	 * @return
	 */
	public int currentLevel();
	
	/**
	 * 当前技能点数
	 * @return
	 */
	public int currentSkillPoint();
	
	/**
	 * 当前已有装备
	 * @return
	 */
	public HashMap<Integer ,Equip> currentEquipList();
	
	/**
	 * 需要装备列表
	 * @return
	 */
	public HashMap<Integer ,Equip> nextEquipList();
	
	/**
	 * 当前精力
	 * @return
	 */
	public int currentEnergy();
	
	public int currentAttack();
	public int currentBonusAttack();
	
	public int nextAttack();
	public int nextBonusAttack();
	
	public long currentExp();
	public long currentExpLimit();
	
	public long nextExp();
	public long nextExpLimit();
	
}
