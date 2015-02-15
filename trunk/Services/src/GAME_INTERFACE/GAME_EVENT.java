package GAME_INTERFACE;

/**
 * 
 * @author publiclll
 *
 */
public interface GAME_EVENT extends GAME_BASEINTERFACE {
	/**
	 * 升级完成
	 */
	public void levelUp_Complete(Object obj);
	/**
	 * 穿完装备
	 */
	public void wornEquip_Complete(Object obj);
	/**
	 * 卸掉装备
	 */
	public void takeoffEquip_Complete(Object obj);
	/**
	 * 使用道具
	 */
	public void useItem_Comlpete(Object obj);
	/***-
	 * 升品质
	 */
	public void qualityUp_Complete(Object obj);
	/**
	 * 进阶完成
	 * @param obj
	 */
	public void phaseUp_Complete(Object obj);
	/**
	 * 出售完成
	 */
	public void sell_Complete(Object obj);
	/**
	 * 购买完成
	 */
	public void buy_Complete(Object obj);
	/**
	 * 使用技能完成
	 */
	public void useSkill_Complete(Object obj);
	/**
	 * 充值完成
	 */
	public void charge_Complete(Object obj);
}
