package GAME_DIRECTOR;

import java.util.HashMap;

import GAME_ENUM.GameValidator;
import GAME_INTERFACE.GAME_NUMERICAL;
import GAME_INTERFACE.GAME_RESOURCELOSS;

/**
 * ��Ϸ��������֤��
 * @author publiclll
 *
 */
public class Validator {
	private GAME_NUMERICAL numerical;
	private GAME_RESOURCELOSS loss;
	private HashMap<GameValidator, Long> list;
	
	public Validator(GAME_NUMERICAL numerical ,GAME_RESOURCELOSS loss){
		this.numerical = numerical;
		this.loss = loss;
		
		list = new HashMap<GameValidator, Long>();
	}
	
	/**
	 * ��֤�����캯��
	 */
	public Validator()
	{
		list = new HashMap<GameValidator, Long>();
	}
	
	/**
	 * ��֤��ָ��
	 * @param o
	 */
	public void validatorObject(){
		if(this.numerical == null)
		{
			list.put(GameValidator.NULLPOINT, new Long(0));
		}
		if(this.loss == null)
		{
			list.put(GameValidator.NULLPOINT, new Long(0));
		}
	}
	
	/**
	 * ��֤��ҵȼ�������
	 */
	public void validatorPlayerLevel()
	{
		if(loss.lossPlayerLevel() > numerical.currentLevel())
		{
			list.put(GameValidator.LEVEL_NOTENOUGH, (long)numerical.currentLevel());
		}
	}
	/**
	 * ��֤�����Ʒ�׵ĵȼ�
	 */
	public void validatorUpPhaseLevel()
	{
		if( loss.lossUpPhasePlayerLevel() > numerical.currentLevel() )
		{
			list.put(GameValidator.LEVEL_NOTENOUGH, (long)numerical.currentLevel());
		}
	}
	
	
	/**
	 * ��֤�Ƿ��Ѿ���װ��
	 * @param cfgId
	 */
	public void validatorHaveEquip(int cfgId){
		if(numerical.currentEquipList().get(cfgId)!=null)
		{
			list.put(GameValidator.EQUIP_ISWORNED, (long)cfgId);
		}
	}
	/***
	 * ��֤�Ƿ��ܴ�װ��
	 * @param cfgId
	 */
	public void validatorWornEquip(int cfgId){
		if(numerical.nextEquipList().get(cfgId)==null)
		{
			list.put(GameValidator.EQUIP_ISWORNED, (long)cfgId);
		}
	}
	
	
	
	
	/**
	 * ��֤����
	 */
	public void validatorCoin()
	{
		if(numerical.currentCoin() >= loss.lossCoin())
		{}else{
			list.put(GameValidator.COIN_NOTENOUGH, loss.lossCoin());

		}
	}
	
	/**
	 * ��֤���
	 */
	public void validatorGold()
	{
		if(numerical.currentGold() >= loss.lossGold())
		{}else{
			list.put(GameValidator.GOLD_NOTENOUGH, (long)loss.lossGold());
		}
	}
	
	/**
	 * ��֤��ʯ
	 */
	public void validatorDiamond()
	{
		if(numerical.currentDiamond() >= loss.lossDiamond())
		{}else{
			list.put(GameValidator.DIAMOND_NOTENOUGH, (long)loss.lossDiamond());
		}
	}
	
	/**
	 * ��֤����
	 */
	public void validatorEnergy()
	{
		if(numerical.currentEnergy() >= loss.lossEnergy())
		{}else{
			list.put(GameValidator.ENERY_NOTENOUGH, (long)loss.lossEnergy());
		}
	}
	
	/**
	 * ��֤�ȼ�
	 */
	public void validatorLevel()
	{
		if(numerical.currentLevel() >= loss.lossPlayerLevel())
		{}else{
			list.put(GameValidator.LEVEL_NOTENOUGH, (long)loss.lossPlayerLevel());
		}
	}
	
	/**
	 * �Ƿ���֤�ɹ�
	 * @return
	 */
	public boolean isValidator()
	{
		if(list.size() <= 0)
			return true;
		else
			return false;
	}
	
	/**
	 * �õ�����Ҫ��ɵ�����
	 * ������ֱ�Ӱ�Э��
	 * @return
	 */
	public HashMap<GameValidator ,Long> notEnough()
	{
		return list;
	}
	
	/**
	 * �����Ϣ
	 */
	public void prints()
	{
		for(GameValidator v : list.keySet())
		{
			System.out.println("[ERROR]:" + v);
		}
	}
	
}
