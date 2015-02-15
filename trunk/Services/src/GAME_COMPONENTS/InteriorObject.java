package GAME_COMPONENTS;

import java.sql.Timestamp;
import java.util.Calendar;

import GAME_INTERFACE.GAME_RESOURCELOSS;

/**
 * ��Ϸ�У���������Ļ�����
 * @author publiclll
 *
 */
public class InteriorObject extends GameObject implements GAME_RESOURCELOSS{
	
	/**
	 * ʱ���
	 */
	protected Timestamp ts;
	/**
	 * ����ʱ���
	 */
	protected Timestamp productionTs;
	
	/**
	 * ��ȡ��ǰʱ��ָ��������֮���ʱ���
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
