package GAME_INTERFACE;

/**
 * 
 * @author publiclll
 *
 */
public interface GAME_EVENT extends GAME_BASEINTERFACE {
	/**
	 * �������
	 */
	public void levelUp_Complete(Object obj);
	/**
	 * ����װ��
	 */
	public void wornEquip_Complete(Object obj);
	/**
	 * ж��װ��
	 */
	public void takeoffEquip_Complete(Object obj);
	/**
	 * ʹ�õ���
	 */
	public void useItem_Comlpete(Object obj);
	/***-
	 * ��Ʒ��
	 */
	public void qualityUp_Complete(Object obj);
	/**
	 * �������
	 * @param obj
	 */
	public void phaseUp_Complete(Object obj);
	/**
	 * �������
	 */
	public void sell_Complete(Object obj);
	/**
	 * �������
	 */
	public void buy_Complete(Object obj);
	/**
	 * ʹ�ü������
	 */
	public void useSkill_Complete(Object obj);
	/**
	 * ��ֵ���
	 */
	public void charge_Complete(Object obj);
}
