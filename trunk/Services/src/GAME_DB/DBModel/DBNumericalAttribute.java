package GAME_DB.DBModel;

/**
 * 数值数据库
 * 一些装备的攻防数据，一些英雄的攻防数据，都在这
 * @author LLL
 *
 */
public class DBNumericalAttribute extends DBBase {
	/**
	 * 指向对象类型
	 */
	private int objType;
	/**
	 * 指向对象ID
	 */
	private String objId;
	/**
	 * 力量
	 */
	private double power;
	/**
	 * 力量系数
	 */
	private double i_power;
	/**
	 * 智力
	 */
	private double intelligence;
	/**
	 * 智力系数
	 */
	private double i_intelligence;
	/**
	 * 敏捷
	 */
	private double agile;
	/**
	 * 敏捷系数
	 */
	private double i_agile;
	/**
	 * 物攻
	 */
	private double p_attack;
	/**
	 * 物理护甲
	 */
	private double p_defense;
	/**
	 * 魔法攻击
	 */
	private double m_attack;
	/**
	 * 魔法抗性 
	 */
	private double m_defense;
	/**
	 * 物理暴击
	 */
	private double p_crit;
	/**
	 * 生命恢复
	 */
	private double r_hp;
	/**
	 * 能量恢复
	 */
	private double r_mp;
	/**
	 * 最大生命
	 */
	private double hp;
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public double getI_power() {
		return i_power;
	}
	public void setI_power(double i_power) {
		this.i_power = i_power;
	}
	public double getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(double intelligence) {
		this.intelligence = intelligence;
	}
	public double getI_intelligence() {
		return i_intelligence;
	}
	public void setI_intelligence(double i_intelligence) {
		this.i_intelligence = i_intelligence;
	}
	public double getAgile() {
		return agile;
	}
	public void setAgile(double agile) {
		this.agile = agile;
	}
	public double getI_agile() {
		return i_agile;
	}
	public void setI_agile(double i_agile) {
		this.i_agile = i_agile;
	}
	public double getP_attack() {
		return p_attack;
	}
	public void setP_attack(double p_attack) {
		this.p_attack = p_attack;
	}
	public double getP_defense() {
		return p_defense;
	}
	public void setP_defense(double p_defense) {
		this.p_defense = p_defense;
	}
	public double getM_attack() {
		return m_attack;
	}
	public void setM_attack(double m_attack) {
		this.m_attack = m_attack;
	}
	public double getM_defense() {
		return m_defense;
	}
	public void setM_defense(double m_defense) {
		this.m_defense = m_defense;
	}
	public double getP_crit() {
		return p_crit;
	}
	public void setP_crit(double p_crit) {
		this.p_crit = p_crit;
	}
	public double getR_hp() {
		return r_hp;
	}
	public void setR_hp(double r_hp) {
		this.r_hp = r_hp;
	}
	public double getR_mp() {
		return r_mp;
	}
	public void setR_mp(double r_mp) {
		this.r_mp = r_mp;
	}
	public double getHp() {
		return hp;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	public int getObjType() {
		return objType;
	}
	public void setObjType(int objType) {
		this.objType = objType;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
}
