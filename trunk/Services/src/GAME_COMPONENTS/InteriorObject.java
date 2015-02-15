package GAME_COMPONENTS;

import java.sql.Timestamp;
import java.util.Calendar;

import GAME_INTERFACE.GAME_RESOURCELOSS;

/**
 * 游戏中，内政对象的基础类
 * @author publiclll
 *
 */
public class InteriorObject extends GameObject implements GAME_RESOURCELOSS{
	
	/**
	 * 时间戳
	 */
	protected Timestamp ts;
	/**
	 * 产出时间戳
	 */
	protected Timestamp productionTs;
	
	/**
	 * 获取当前时间指定秒钟数之后的时间戳
	 * @param second
	 * @return
	 */
	protected Timestamp timeStampAfterSecond(int second) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, second);
		return new Timestamp(now.getTime().getTime());
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
	public int lossPlayerLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameObject conditionObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lossDiamond() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossEnergy() {
		// TODO Auto-generated method stub
		return 0;
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
