package GAME_INTERFACE;

import java.util.HashMap;

import GAME_COMPONENTS.Equip;

/**
 * ��Ϸ��ֵ�ӿڣ����ڼ���������ֹ��������������㣬�ƶ��ȵ���ֵ
 * @author publiclll
 *
 */
public interface GAME_NUMERICAL extends GAME_BASEINTERFACE {
	/**
	 * ��ǰ����
	 * @return
	 */
	public long currentCoin();
	/**
	 * ��ǰ���
	 * @return
	 */
	public int currentGold();
	/**
	 * ��ǰ��ʯ
	 * @return
	 */
	public int currentDiamond();
	/**
	 * ��ǰ�ȼ�
	 * @return
	 */
	public int currentLevel();
	
	/**
	 * ��ǰ���ܵ���
	 * @return
	 */
	public int currentSkillPoint();
	
	/**
	 * ��ǰ����װ��
	 * @return
	 */
	public HashMap<Integer ,Equip> currentEquipList();
	
	/**
	 * ��Ҫװ���б�
	 * @return
	 */
	public HashMap<Integer ,Equip> nextEquipList();
	
	/**
	 * ��ǰ����
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
