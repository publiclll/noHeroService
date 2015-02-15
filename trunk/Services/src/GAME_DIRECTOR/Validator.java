package GAME_DIRECTOR;

import java.util.HashMap;

import GAME_ENUM.GameValidator;
import GAME_INTERFACE.GAME_NUMERICAL;
import GAME_INTERFACE.GAME_RESOURCELOSS;

/**
 * 游戏中数据验证类
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
	 * 验证器构造函数
	 */
	public Validator()
	{
		list = new HashMap<GameValidator, Long>();
	}
	
	/**
	 * 验证空指针
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
	 * 验证玩家等级够不够
	 */
	public void validatorPlayerLevel()
	{
		if(loss.lossPlayerLevel() > numerical.currentLevel())
		{
			list.put(GameValidator.LEVEL_NOTENOUGH, (long)numerical.currentLevel());
		}
	}
	/**
	 * 验证玩家升品阶的等级
	 */
	public void validatorUpPhaseLevel()
	{
		if( loss.lossUpPhasePlayerLevel() > numerical.currentLevel() )
		{
			list.put(GameValidator.LEVEL_NOTENOUGH, (long)numerical.currentLevel());
		}
	}
	
	
	/**
	 * 验证是否已经存装备
	 * @param cfgId
	 */
	public void validatorHaveEquip(int cfgId){
		if(numerical.currentEquipList().get(cfgId)!=null)
		{
			list.put(GameValidator.EQUIP_ISWORNED, (long)cfgId);
		}
	}
	/***
	 * 验证是否能穿装备
	 * @param cfgId
	 */
	public void validatorWornEquip(int cfgId){
		if(numerical.nextEquipList().get(cfgId)==null)
		{
			list.put(GameValidator.EQUIP_ISWORNED, (long)cfgId);
		}
	}
	
	
	
	
	/**
	 * 验证金子
	 */
	public void validatorCoin()
	{
		if(numerical.currentCoin() >= loss.lossCoin())
		{}else{
			list.put(GameValidator.COIN_NOTENOUGH, loss.lossCoin());

		}
	}
	
	/**
	 * 验证金币
	 */
	public void validatorGold()
	{
		if(numerical.currentGold() >= loss.lossGold())
		{}else{
			list.put(GameValidator.GOLD_NOTENOUGH, (long)loss.lossGold());
		}
	}
	
	/**
	 * 验证钻石
	 */
	public void validatorDiamond()
	{
		if(numerical.currentDiamond() >= loss.lossDiamond())
		{}else{
			list.put(GameValidator.DIAMOND_NOTENOUGH, (long)loss.lossDiamond());
		}
	}
	
	/**
	 * 验证精力
	 */
	public void validatorEnergy()
	{
		if(numerical.currentEnergy() >= loss.lossEnergy())
		{}else{
			list.put(GameValidator.ENERY_NOTENOUGH, (long)loss.lossEnergy());
		}
	}
	
	/**
	 * 验证等级
	 */
	public void validatorLevel()
	{
		if(numerical.currentLevel() >= loss.lossPlayerLevel())
		{}else{
			list.put(GameValidator.LEVEL_NOTENOUGH, (long)loss.lossPlayerLevel());
		}
	}
	
	/**
	 * 是否验证成功
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
	 * 得到所需要完成的条件
	 * 条件将直接绑定协议
	 * @return
	 */
	public HashMap<GameValidator ,Long> notEnough()
	{
		return list;
	}
	
	/**
	 * 输出信息
	 */
	public void prints()
	{
		for(GameValidator v : list.keySet())
		{
			System.out.println("[ERROR]:" + v);
		}
	}
	
}
