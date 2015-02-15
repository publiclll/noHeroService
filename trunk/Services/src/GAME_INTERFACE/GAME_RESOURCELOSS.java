package GAME_INTERFACE;

import GAME_COMPONENTS.GameObject;

/**
 * 消耗资源接口
 * @author publiclll
 *
 */
public interface GAME_RESOURCELOSS extends GAME_BASEINTERFACE {
	/**
	 * 需要消耗的钱
	 * @return
	 */
	public long lossCoin();
	/**
	 * 需要消耗的金币
	 * @return
	 */
	public int lossGold();
	/**
	 * 消耗钻石
	 * @return
	 */
	public int lossDiamond();
	/**
	 * 需求Player的等级
	 * @return
	 */
	public int lossPlayerLevel();
	/**
	 * 升品阶需要玩家等级
	 * @return
	 */
	public int lossUpPhasePlayerLevel();
	
	/**
	 * 升星需要玩家等级
	 * @return
	 */
	public int lossUpStarLevelPlayerLevel();
	/**
	 * 需求精力
	 * @return
	 */
	public int lossEnergy();
	
	public GameObject conditionObject();
}
