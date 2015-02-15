package GAME_INTERFACE;

import GAME_COMPONENTS.GameObject;

/**
 * ������Դ�ӿ�
 * @author publiclll
 *
 */
public interface GAME_RESOURCELOSS extends GAME_BASEINTERFACE {
	/**
	 * ��Ҫ���ĵ�Ǯ
	 * @return
	 */
	public long lossCoin();
	/**
	 * ��Ҫ���ĵĽ��
	 * @return
	 */
	public int lossGold();
	/**
	 * ������ʯ
	 * @return
	 */
	public int lossDiamond();
	/**
	 * ����Player�ĵȼ�
	 * @return
	 */
	public int lossPlayerLevel();
	/**
	 * ��Ʒ����Ҫ��ҵȼ�
	 * @return
	 */
	public int lossUpPhasePlayerLevel();
	
	/**
	 * ������Ҫ��ҵȼ�
	 * @return
	 */
	public int lossUpStarLevelPlayerLevel();
	/**
	 * ������
	 * @return
	 */
	public int lossEnergy();
	
	public GameObject conditionObject();
}
