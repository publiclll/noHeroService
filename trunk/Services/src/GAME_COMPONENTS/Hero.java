package GAME_COMPONENTS;

import java.util.HashMap;

import GAME_COMPONENTS.CONDITIONS.HeroUpPhase;
import GAME_ENUM.GameDataType;
import GAME_ENUM.GameElementType;
import GAME_ENUM.GameHeroAttr;
import GAME_ENUM.GameHeroLocation;
import GAME_ENUM.GameHeroRace;
import GAME_ENUM.GameHeroSoldiersare;
import GAME_ENUM.GameObjectType;
import GAME_INTERFACE.GAME_RESOURCELOSS;
import GAME_SCHEDULING.ConfigScheduling;

/**
 * Ӣ����
 * 
 * @author publiclll
 *
 */
public class Hero extends GameObject implements GAME_RESOURCELOSS {
	public Hero() {
		/**
		 * ����ID
		 */
		this.CreateID();
		setObjDbType(GameDataType.HERO);
		setObjType(GameObjectType.HERO);
		setFocusPhaseEquipList(new HashMap<Integer, Equip>());
		setUpPhaseEquipList(new HashMap<Integer, Equip>());
		setEquipList(new HashMap<Integer, Equip>());
		setPhaseBufList(new HashMap<Integer, NumericalAttribute>());
		setUpPhaseEquipAllList(new HashMap<Integer, HashMap<Integer,Equip>>());
	}
	
	/**
	 * �Ƿ���ҿ���
	 */
	private boolean isControl;

	/**
	 * ��ǰƷ�������װ��
	 */
	private HashMap<Integer, Equip> focusPhaseEquipList;

	/**
	 * ������Ҫ��װ��
	 */
	private HashMap<Integer, Equip> upPhaseEquipList;
	/**
	 * ������Ҫ��ȫ��װ���б�
	 */
	private HashMap<Integer ,HashMap<Integer,Equip>> upPhaseEquipAllList;

	/**
	 * װ��
	 */
	private HashMap<Integer, Equip> equipList;

	/**
	 * ����
	 */
	private HashMap<Integer, Skill> skillList;

	/**
	 * ����
	 */
	private NumericalAttribute baseAttr;

	/**
	 * Buff
	 */
	private NumericalAttribute bufAttr;
	
	/**
	 * ����Buff�б�
	 */
	private HashMap<Integer ,NumericalAttribute> phaseBufList;

	/**
	 * �Ǽ� ,����Ʒ��
	 */
	private int starLevel;
	
	/**
	 * ����Ӣ�۵ȼ�����
	 */
	private int upStarLevel_LevelLimit;

	/**
	 * �׶Σ���ɫ����ɫ����ɫ+����ɫ+2����ɫ ������ �ȵȵȵȣ�
	 */
	private int phaseLevel;
	/**
	 * ��Ʒ��Ӣ�۵ȼ�����
	 */
	private int upPhaseLevel_LevelLimit;
	/**
	 * ������Ҫ��Ǯ
	 */
	private int upPhaseLevelNeedCoint;

	
	/**
	 * ս�������������
	 */
	private int warPow;

	/**
	 * ������
	 */
	private GameHeroAttr heroAttr;

	/**
	 * ����
	 */
	private GameHeroRace heroRace;

	/**
	 * վλ
	 */
	private GameHeroLocation heroLocation;

	/**
	 * ��ϵ
	 */
	private GameHeroSoldiersare heroSoldiersare;

	/**
	 * ���Լ�飬�������۵Ļ�
	 */
	private String attrContent;

	/**
	 * ��������
	 */
	private int skillCountLimit;

	/**
	 * װ������
	 */
	private int equipCountLimit;
	/**
	 * ���ǵ�����id
	 */
	private int upStarLevel_cfgid;
	/**
	 * ��Ʒ�׵�����id
	 */
	private int upPhaseLevel_cfgid;
	/**
	 * ӵ�м��ܵ�����id
	 */
	private int skill_cfgid;
	
	/**
	 * ��Ʒ�׵ȼ�����Buff
	 * @return
	 */
	public void initBufWithPhaseLevel(int phaseLevel)
	{
		NumericalAttribute bufNumAttr = getPhaseBufList().get(phaseLevel);
		if(bufNumAttr != null)
			plusBufNumerical(bufNumAttr);
	}
	/**
	 * ����Ʒ�׵ȼ����õ�������һƷ�׵�װ���б�
	 * @param phaseLevel
	 */
	public void setUpPhaseEquipList(int phaseLevel){
		setUpPhaseEquipList(getUpPhaseEquipAllList().get(phaseLevel));
	}
	
	/**
	 * ����Buff����
	 * @param buf
	 */
	public void plusBufNumerical(NumericalAttribute buf)
	{
		bufAttr.setPower(bufAttr.getPower()+buf.getPower());
		bufAttr.setI_power(bufAttr.getI_power()+buf.getI_power());
		bufAttr.setI_intelligence(bufAttr.getI_intelligence()+buf.getI_intelligence());
		bufAttr.setIntelligence(bufAttr.getIntelligence()+buf.getIntelligence());
		bufAttr.setAgile(bufAttr.getAgile()+buf.getAgile());
		bufAttr.setI_agile(bufAttr.getI_agile()+buf.getI_agile());
		bufAttr.setP_attack(bufAttr.getP_attack()+buf.getP_attack());
		bufAttr.setP_defense(bufAttr.getP_defense()+buf.getP_defense());
		bufAttr.setM_attack(bufAttr.getM_attack()+buf.getM_attack());
		bufAttr.setM_defense(bufAttr.getM_defense()+buf.getM_defense());
		bufAttr.setP_crit(bufAttr.getP_crit()+buf.getP_crit());
		bufAttr.setR_hp(bufAttr.getR_hp()+buf.getR_hp());
		bufAttr.setR_mp(bufAttr.getR_mp()+buf.getR_mp());
		bufAttr.setHp(bufAttr.getHp()+buf.getHp());
	}

	@Override
	public String returnGameObjectID() {
		// TODO Auto-generated method stub
		return getID();
	}

	@Override
	public GameObjectType returnGameObjectType() {
		// TODO Auto-generated method stub
		return getObjType();
	}

	@Override
	public GameElementType returnGameElementType() {
		// TODO Auto-generated method stub
		return getElementType();
	}

	@Override
	public void levelUp_Complete(Object obj) {
		if((getLevel() + 1) > getLevelLimit())
		{}else{
			setLevel(getLevel() + 1);
			setExpLimit((int)(getExpLimit() * 1.10));
			//��������
			getBaseAttr().setPower( getBaseAttr().getPower() * getBaseAttr().getI_power() );
			getBaseAttr().setIntelligence( getBaseAttr().getIntelligence() * getBaseAttr().getI_intelligence() );
			getBaseAttr().setAgile( getBaseAttr().getAgile() * getBaseAttr().getI_agile() );
			//����Ӣ�ۼ���
		}
	}
	
	/**
	 * ��ǰ�ȼ�
	 */
	@Override
	public int currentLevel() {
		return getLevel();
	}

	/**
	 * ��װ�����
	 */
	@Override
	public void wornEquip_Complete(Object obj) {
		// ��װ�����
	}

	/**
	 * �������
	 */
	@Override
	public void phaseUp_Complete(Object obj) {
		//�������
		setBufAttr(new NumericalAttribute());
		setPhaseLevel(getPhaseLevel()+1);
		//������׺��buf������
		initBufWithPhaseLevel(getPhaseLevel());
		setUpPhaseEquipList(getPhaseLevel()+1);
		System.out.println("������ɣ�����Ҫ������׺��Buff��");
	}

	/**
	 * �������
	 */
	@Override
	public void qualityUp_Complete(Object obj) {
		
	}
	
	@Override
	public HashMap<Integer, Equip> currentEquipList() {
		// TODO Auto-generated method stub
		return getEquipList();
	}

	@Override
	public HashMap<Integer, Equip> nextEquipList() {
		// TODO Auto-generated method stub
		return getUpPhaseEquipList();
	}

	public NumericalAttribute getBaseAttr() {
		return baseAttr;
	}

	public void setBaseAttr(NumericalAttribute baseAttr) {
		this.baseAttr = baseAttr;
	}

	public NumericalAttribute getBufAttr() {
		return bufAttr;
	}

	public void setBufAttr(NumericalAttribute bufAttr) {
		this.bufAttr = bufAttr;
	}

	public int getWarPow() {
		return warPow;
	}

	public void setWarPow(int warPow) {
		this.warPow = warPow;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public int getPhaseLevel() {
		return phaseLevel;
	}

	public void setPhaseLevel(int phaseLevel) {
		this.phaseLevel = phaseLevel;
	}

	public GameHeroAttr getHeroAttr() {
		return heroAttr;
	}

	public void setHeroAttr(GameHeroAttr heroAttr) {
		this.heroAttr = heroAttr;
	}

	public GameHeroRace getHeroRace() {
		return heroRace;
	}

	public void setHeroRace(GameHeroRace heroRace) {
		this.heroRace = heroRace;
	}

	public GameHeroLocation getHeroLocation() {
		return heroLocation;
	}

	public void setHeroLocation(GameHeroLocation heroLocation) {
		this.heroLocation = heroLocation;
	}

	public GameHeroSoldiersare getHeroSoldiersare() {
		return heroSoldiersare;
	}

	public void setHeroSoldiersare(GameHeroSoldiersare heroSoldiersare) {
		this.heroSoldiersare = heroSoldiersare;
	}

	public HashMap<Integer, Equip> getUpPhaseEquipList() {
		return upPhaseEquipList;
	}

	public void setUpPhaseEquipList(HashMap<Integer, Equip> upPhaseEquipList) {
		this.upPhaseEquipList = upPhaseEquipList;
	}

	public HashMap<Integer, Equip> getFocusPhaseEquipList() {
		return focusPhaseEquipList;
	}

	public void setFocusPhaseEquipList(
			HashMap<Integer, Equip> focusPhaseEquipList) {
		this.focusPhaseEquipList = focusPhaseEquipList;
	}

	public HashMap<Integer, Equip> getEquipList() {
		return equipList;
	}

	public void setEquipList(HashMap<Integer, Equip> equipList) {
		this.equipList = equipList;
	}

	public HashMap<Integer, Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(HashMap<Integer, Skill> skillList) {
		this.skillList = skillList;
	}
	/**
	 * �õ��Ѿ�����ļ���
	 * @return
	 */
	public HashMap<Integer , Skill> getUsedSkillList(){
		HashMap<Integer ,Skill> list = new HashMap<Integer, Skill>();
		for(Skill s : list.values())
		{
			if(s.isUsed())
				list.put(s.getCfgID(), s);
		}
		return list;
	}

	/**
	 * ��¡����
	 */
	public Hero clone() {
		Hero h = null;
		try {
			h = (Hero) super.clone();
			setFocusPhaseEquipList(getFocusPhaseEquipList());
			setUpPhaseEquipList(getUpPhaseEquipList());
			setEquipList(getEquipList());
			setSkillList(getSkillList());
			h.setCfgID(this.getCfgID());
			h.setName(this.getName());
			h.setContent(this.getContent());
			h.setAttrContent(this.getAttrContent());
			h.setHeroRace(this.getHeroRace());
			h.setHeroSoldiersare(this.getHeroSoldiersare());
			h.setHeroAttr(this.getHeroAttr());
			h.setStarLevel(this.getStarLevel());
			h.setPhaseLevel(this.getPhaseLevel());
			h.setHeroLocation(this.getHeroLocation());
			h.setLevel(this.getLevel());
			h.setLevelLimit(this.getLevelLimit());
			h.setExp(this.getExp());
			h.setExpLimit(this.getExpLimit());
			h.setBaseAttr(this.getBaseAttr());
			h.setBufAttr(this.getBufAttr());
			h.setSkillCountLimit(this.getSkillCountLimit());
			h.setEquipCountLimit(this.getEquipCountLimit());
			h.setUpStarLevel_cfgid(this.getUpStarLevel_cfgid());
			h.setUpPhaseLevel_cfgid(this.getUpPhaseLevel_cfgid());
			h.setSkill_cfgid(this.getSkill_cfgid());
			h.setUpStarLevel_LevelLimit(this.getUpStarLevel_LevelLimit());
			h.setUpPhaseLevel_LevelLimit(this.getUpPhaseLevel_LevelLimit());
			h.setUpPhaseLevelNeedCoint(this.getUpPhaseLevelNeedCoint());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}

	public String getAttrContent() {
		return attrContent;
	}

	public void setAttrContent(String attrContent) {
		this.attrContent = attrContent;
	}

	public int getSkillCountLimit() {
		return skillCountLimit;
	}

	public void setSkillCountLimit(int skillCountLimit) {
		this.skillCountLimit = skillCountLimit;
	}

	public int getEquipCountLimit() {
		return equipCountLimit;
	}

	public void setEquipCountLimit(int equipCountLimit) {
		this.equipCountLimit = equipCountLimit;
	}

	public int getUpStarLevel_cfgid() {
		return upStarLevel_cfgid;
	}

	public void setUpStarLevel_cfgid(int upStarLevel_cfgid) {
		this.upStarLevel_cfgid = upStarLevel_cfgid;
	}

	public int getUpPhaseLevel_cfgid() {
		return upPhaseLevel_cfgid;
	}

	public void setUpPhaseLevel_cfgid(int upPhaseLevel_cfgid) {
		this.upPhaseLevel_cfgid = upPhaseLevel_cfgid;
	}

	public int getSkill_cfgid() {
		return skill_cfgid;
	}

	public void setSkill_cfgid(int skill_cfgid) {
		this.skill_cfgid = skill_cfgid;
	}

	public int getUpPhaseLevel_LevelLimit() {
		return upPhaseLevel_LevelLimit;
	}

	public void setUpPhaseLevel_LevelLimit(int upPhaseLevel_LevelLimit) {
		this.upPhaseLevel_LevelLimit = upPhaseLevel_LevelLimit;
	}

	public int getUpStarLevel_LevelLimit() {
		return upStarLevel_LevelLimit;
	}

	public void setUpStarLevel_LevelLimit(int upStarLevel_LevelLimit) {
		this.upStarLevel_LevelLimit = upStarLevel_LevelLimit;
	}

	public int getUpPhaseLevelNeedCoint() {
		return upPhaseLevelNeedCoint;
	}

	public void setUpPhaseLevelNeedCoint(int upPhaseLevelNeedCoint) {
		this.upPhaseLevelNeedCoint = upPhaseLevelNeedCoint;
	}

	public boolean isControl() {
		return isControl;
	}

	public void setControl(boolean isControl) {
		this.isControl = isControl;
	}

	@Override
	public GameObject focusObject() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public long lossCoin() {
		// TODO Auto-generated method stub
		return getUpPhaseLevelNeedCoint();
	}

	@Override
	public int lossGold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossDiamond() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lossPlayerLevel() {
		// TODO Auto-generated method stub
		return getUpPhaseLevel_LevelLimit();
	}

	@Override
	public int lossEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameObject conditionObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lossUpPhasePlayerLevel() {
		// TODO Auto-generated method stub
		return getUpPhaseLevel_LevelLimit();
	}

	@Override
	public int lossUpStarLevelPlayerLevel() {
		// TODO Auto-generated method stub
		return getUpStarLevel_LevelLimit();
	}


	public HashMap<Integer ,NumericalAttribute> getPhaseBufList() {
		return phaseBufList;
	}

	public void setPhaseBufList(HashMap<Integer ,NumericalAttribute> phaseBufList) {
		this.phaseBufList = phaseBufList;
	}
	/**
	 * ������Ҫ��ȫ��װ���б�
	 * @return
	 */
	public HashMap<Integer ,HashMap<Integer,Equip>> getUpPhaseEquipAllList() {
		return upPhaseEquipAllList;
	}
	/**
	 * ���ý�����Ҫ��ȫ��װ���б�
	 * @return
	 */
	public void setUpPhaseEquipAllList(HashMap<Integer ,HashMap<Integer,Equip>> upPhaseEquipAllList) {
		this.upPhaseEquipAllList = upPhaseEquipAllList;
	}

}
