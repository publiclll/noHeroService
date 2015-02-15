package GAME_COMPONENTS;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameObjectType;

/**
 * ����������
 * 
 * @author publiclll
 *
 */
public class NumericalAttribute extends GameObject implements Cloneable {

	private void init() {
		this.CreateID();

		setObjDbType(GameDataType.NUMERICAL);
		setObjType(GameObjectType.NUMERICAL);
		setCfgID(0);

		/**
		 * ����
		 */
		this.power = 0.00;
		/**
		 * ����ϵ��
		 */
		this.i_power = 0.00;
		/**
		 * ����
		 */
		this.intelligence = 0.00;
		/**
		 * ����ϵ��
		 */
		this.i_intelligence = 0.00;
		/**
		 * ����
		 */
		this.agile = 0.00;
		/**
		 * ����ϵ��
		 */
		this.i_agile = 0.00;
		/**
		 * �﹥
		 */
		this.p_attack = 0.00;
		/**
		 * ������
		 */
		this.p_defense = 0.00;
		/**
		 * ħ������
		 */
		this.m_attack = 0.00;
		/**
		 * ħ������
		 */
		this.m_defense = 0.00;
		/**
		 * ������
		 */
		this.p_crit = 0.00;
		/**
		 * �����ָ�
		 */
		this.r_hp = 0.00;
		/**
		 * �����ָ�
		 */
		this.r_mp = 0.00;
		/**
		 * �������
		 */
		this.hp = 0.00;
	}

	public NumericalAttribute() {
		init();
	}
	
	public NumericalAttribute(GameObjectType parentObjType){
		init();
		setParentObjType(parentObjType);
	}

	/**
	 * ָ���������
	 */
	private GameObjectType parentObjType;
	/**
	 * ָ�����ID
	 */
	private String objId;
	/**
	 * ����
	 */
	private double power;
	/**
	 * ����ϵ��
	 */
	private double i_power;
	/**
	 * ����
	 */
	private double intelligence;
	/**
	 * ����ϵ��
	 */
	private double i_intelligence;
	/**
	 * ����
	 */
	private double agile;
	/**
	 * ����ϵ��
	 */
	private double i_agile;
	/**
	 * �﹥
	 */
	private double p_attack;
	/**
	 * ������
	 */
	private double p_defense;
	/**
	 * ħ������
	 */
	private double m_attack;
	/**
	 * ħ������
	 */
	private double m_defense;
	/**
	 * ������
	 */
	private double p_crit;
	/**
	 * �����ָ�
	 */
	private double r_hp;
	/**
	 * �����ָ�
	 */
	private double r_mp;
	/**
	 * �������
	 */
	private double hp;

	/**
	 * ��¡
	 */
	public NumericalAttribute clone() {
		NumericalAttribute n = null;
		try {
			n = (NumericalAttribute) super.clone();
			n.setCfgID(getCfgID());
			n.CreateID();
			n.parentObjType = this.parentObjType;
			n.objId = this.objId;
			n.power = this.power;
			n.i_power = this.i_power;
			n.intelligence = this.intelligence;
			n.i_intelligence = this.i_intelligence;
			n.agile = this.agile;
			n.i_agile = this.i_agile;
			n.p_attack = this.p_attack;
			n.m_attack = this.m_attack;
			n.p_defense = this.p_defense;
			n.m_defense = this.m_defense;
			n.p_crit = this.p_crit;
			n.r_hp = this.r_hp;
			n.r_mp = this.r_mp;
			n.hp = this.hp;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

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

	public GameObjectType getParentObjType() {
		return parentObjType;
	}

	public void setParentObjType(GameObjectType parentObjType) {
		this.parentObjType = parentObjType;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
}
