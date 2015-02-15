package GAME_SCHEDULING;

import GAME_COMPONENTS.Equip;
import GAME_COMPONENTS.Hero;
import GAME_COMPONENTS.NumericalAttribute;
import GAME_COMPONENTS.Player;
import GAME_COMPONENTS.Skill;
import GAME_COMPONENTS.CONDITIONS.HeroUpPhase;
import GAME_ENUM.GameEquipAttr;
import GAME_ENUM.GameHeroAttr;
import GAME_ENUM.GameHeroLocation;
import GAME_ENUM.GameHeroRace;
import GAME_ENUM.GameHeroSkillAttr;
import GAME_ENUM.GameHeroSoldiersare;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 游戏配置调度
 * 
 * @author Administrator
 *
 */
public class ConfigScheduling {

	private static String fileStr = System.getProperty("user.dir")
			+ File.separator + "CofigFiles" + File.separator + "gameconfig.xls";

	private static Workbook book = null;
	private static ConfigScheduling scheduling = null;

	/**
	 * 玩家选项卡编号
	 */
	private static int playerSheet = 0;

	/**
	 * 战斗及Buff配置
	 */
	private static int numberAttrSheet = 1;

	/**
	 * 英雄配置
	 */
	private static int heroSheet = 2;

	/**
	 * 英雄技能
	 */
	private static int heroSKillSheet = 8;
	/**
	 * 技能配置
	 */
	private static int heroSkillCfgSheet = 9;

	/**
	 * 装备配置
	 */
	private static int equipSheet = 5;

	/**
	 * 英雄进阶
	 */
	private static int heroUpPhaseSheet = 3;

	/**
	 * 从哪一行开始
	 */
	private static int sheetBeginRow = 1;

	/**
	 * 构造函数，初始化数据
	 */
	private ConfigScheduling() {
		initPlayer();
		// 加载顺序不能变，变了打死你
		initNumberAttr();
		initSkill();
		initSkillCfgList();
		initEquipList();
		initUpPhaseList();
		initHero();
	}

	public static ConfigScheduling getInstance() {
		if (scheduling == null)
			scheduling = new ConfigScheduling();
		return scheduling;
	}

	private Player player = null;

	/**
	 * 加载默认玩家数据
	 */
	private void initPlayer() {
		player = new Player();
		Sheet sheet = getConfigSheet(playerSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			player.setCfgID(Integer.parseInt(getCellString(sheet, 1, i)));
			player.setLevel(Integer.parseInt(getCellString(sheet, 2, i)));
			player.setLevelLimit(Integer.parseInt(getCellString(sheet, 3, i)));
			player.setExp(Integer.parseInt(getCellString(sheet, 4, i)));
			player.setExpLimit(Integer.parseInt(getCellString(sheet, 5, i)));
			player.setHeadSerialNum(Integer
					.parseInt(getCellString(sheet, 6, i)));
			player.setHeadSerialBoxNum(Integer.parseInt(getCellString(sheet, 7,
					i)));
			player.setEnergy(Integer.parseInt(getCellString(sheet, 8, i)));
			player.setEnergyLimit(Integer.parseInt(getCellString(sheet, 9, i)));
			player.setCoin(Integer.parseInt(getCellString(sheet, 10, i)));
			player.setDiamond(Integer.parseInt(getCellString(sheet, 11, i)));
			player.setSkillPoint(Integer.parseInt(getCellString(sheet, 12, i)));
			player.setVipLevel(Integer.parseInt(getCellString(sheet, 13, i)));

			/**
			 * 功勋
			 */
			player.setFeats(0);
			/**
			 * 势力（联盟）
			 */
			player.setForcesId(0);
			// player.setVipLevel(0);

		}
		player.setCfgData(true);

		book.close();
		book = null;
	}

	private HashMap<Integer, NumericalAttribute> numericalAttrList;

	/**
	 * 加载数值数据
	 */
	private void initNumberAttr() {
		if (numericalAttrList == null)
			numericalAttrList = new HashMap<Integer, NumericalAttribute>();
		Sheet sheet = getConfigSheet(numberAttrSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			NumericalAttribute numAttr = new NumericalAttribute();
			numAttr.setCfgID(Integer.parseInt(getCellString(sheet, 1, i)));
			numAttr.setPower(Double.parseDouble(getCellString(sheet, 2, i)));
			numAttr.setI_power(Double.parseDouble(getCellString(sheet, 3, i)));
			numAttr.setIntelligence(Double.parseDouble(getCellString(sheet, 4,
					i)));
			numAttr.setI_intelligence(Double.parseDouble(getCellString(sheet,
					5, i)));
			numAttr.setAgile(Double.parseDouble(getCellString(sheet, 6, i)));
			numAttr.setI_agile(Double.parseDouble(getCellString(sheet, 7, i)));
			numAttr.setP_attack(Double.parseDouble(getCellString(sheet, 8, i)));
			numAttr.setM_attack(Double.parseDouble(getCellString(sheet, 9, i)));
			numAttr.setP_defense(Double
					.parseDouble(getCellString(sheet, 10, i)));
			numAttr.setM_defense(Double
					.parseDouble(getCellString(sheet, 11, i)));
			numAttr.setP_crit(Double.parseDouble(getCellString(sheet, 12, i)));
			numAttr.setR_hp(Double.parseDouble(getCellString(sheet, 13, i)));
			numAttr.setR_mp(Double.parseDouble(getCellString(sheet, 14, i)));
			numAttr.setHp(Double.parseDouble(getCellString(sheet, 15, i)));
			numericalAttrList.put(numAttr.getCfgID(), numAttr);
		}
	}

	private HashMap<Integer, Hero> heroList;
	/**
	 * 初始化英雄
	 */
	private void initHero() {
		if (heroList == null)
			heroList = new HashMap<Integer, Hero>();
		Sheet sheet = getConfigSheet(heroSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			Hero h = new Hero();
			h.setCfgData(true);
			h.setCfgID(Integer.parseInt(getCellString(sheet, 1, i)));
			h.setName(getCellString(sheet, 2, i));
			h.setContent(getCellString(sheet, 3, i));
			h.setAttrContent(getCellString(sheet, 4, i));
			switch (Integer.parseInt(getCellString(sheet, 5, i))) {
			case 1:
				h.setHeroRace(GameHeroRace.human);
				break;
			case 2:
				h.setHeroRace(GameHeroRace.beast);
				break;
			case 3:
				h.setHeroRace(GameHeroRace.fishmen);
				break;
			case 4:
				h.setHeroRace(GameHeroRace.undead);
				break;
			default:
				h.setHeroRace(GameHeroRace.INVALID);
				break;
			}
			switch (Integer.parseInt(getCellString(sheet, 6, i))) {
			case 1:
				h.setHeroSoldiersare(GameHeroSoldiersare.knife);
				break;
			case 2:
				h.setHeroSoldiersare(GameHeroSoldiersare.bow);
				break;
			case 3:
				h.setHeroSoldiersare(GameHeroSoldiersare.axe);
				break;
			case 4:
				h.setHeroSoldiersare(GameHeroSoldiersare.magic);
				break;
			default:
				h.setHeroSoldiersare(GameHeroSoldiersare.INVALID);
				break;
			}
			switch (Integer.parseInt(getCellString(sheet, 7, i))) {
			case 1:
				h.setHeroAttr(GameHeroAttr.power);
				break;
			case 2:
				h.setHeroAttr(GameHeroAttr.intelligence);
				break;
			case 3:
				h.setHeroAttr(GameHeroAttr.agile);
				break;
			default:
				h.setHeroAttr(GameHeroAttr.INVALID);
				break;
			}
			h.setStarLevel(Integer.parseInt(getCellString(sheet, 8, i)));
			h.setPhaseLevel(Integer.parseInt(getCellString(sheet, 9, i)));
			switch (Integer.parseInt(getCellString(sheet, 10, i))) {
			case 1:
				h.setHeroLocation(GameHeroLocation.front);
				break;
			case 2:
				h.setHeroLocation(GameHeroLocation.middle);
				break;
			case 3:
				h.setHeroLocation(GameHeroLocation.back);
				break;
			default:
				h.setHeroLocation(GameHeroLocation.INVALID);
				break;
			}
			h.setLevel(Integer.parseInt(getCellString(sheet, 11, i)));
			h.setLevelLimit(Integer.parseInt(getCellString(sheet, 12, i)));
			h.setExp(Integer.parseInt(getCellString(sheet, 13, i)));
			h.setExpLimit(Integer.parseInt(getCellString(sheet, 14, i)));
			h.setBaseAttr(this.getNumberAttr(Integer.parseInt(getCellString(
					sheet, 15, i))));
			h.setBufAttr(new NumericalAttribute());
			h.setSkillCountLimit(Integer.parseInt(getCellString(sheet, 16, i)));
			h.setEquipCountLimit(Integer.parseInt(getCellString(sheet, 17, i)));
			h.setUpStarLevel_cfgid(Integer
					.parseInt(getCellString(sheet, 18, i)));
			h.setUpPhaseLevel_cfgid(Integer
					.parseInt(getCellString(sheet, 19, i)));

			HeroUpPhase up = this.getTargetUpPhase(h.getUpPhaseLevel_cfgid(),
					h.getLevel() + 1);
			if (up != null) {
				h.setUpPhaseLevel_LevelLimit(up.getHeroLevelLimit());
				h.setUpPhaseLevelNeedCoint(up.getNeedCoint());
				for (String equipCfgId : up.getEquipCfgIdArray()) {
					Equip e = getEquip(Integer.parseInt(equipCfgId));
					h.getUpPhaseEquipList().put(e.getCfgID(), e);
				}
			}
			
			/**
			 * 加载全部进阶所需的Buff属性
			 */
			for(HeroUpPhase p : this.getUpPhaseList().values())
			{
				HashMap<Integer ,Equip> equipList = new HashMap<Integer, Equip>();
				for (String equipCfgId : p.getEquipCfgIdArray()) {
					Equip e = getEquip(Integer.parseInt(equipCfgId));
					equipList.put(e.getCfgID(), e);
				}
				h.getUpPhaseEquipAllList().put(p.getTargetPhase(), equipList);
				
			}
			/**
			 * 初始化英雄当前品阶的Buff
			 */
			h.initBufWithPhaseLevel(h.getPhaseLevel());
			h.setUpPhaseEquipList(h.getPhaseLevel()+1);
			
			h.setSkill_cfgid(Integer.parseInt(getCellString(sheet, 20, i)));

			String[] skillArray = this.getSkillCfg(h.getSkill_cfgid()).split(
					",");
			HashMap<Integer, Skill> tempSkillList = new HashMap<Integer, Skill>();
			for (String s_cfgid : skillArray) {
				Skill s = this.getSkill(Integer.parseInt(s_cfgid));
				tempSkillList.put(s.getCfgID(), s);
			}
			h.setSkillList(tempSkillList);
			
			/**
			 * 设置属性所属对象的类型和ID
			 */
			h.getBaseAttr().setObjId(h.getID());
			h.getBaseAttr().setParentObjType(h.getObjType());
			h.getBufAttr().setObjId(h.getID());
			h.getBufAttr().setParentObjType(h.getObjType());
			h.setControl(Integer.parseInt(getCellString(sheet, 20, i))==1?true:false);
			
			heroList.put(h.getCfgID(), h);
		}
	}

	private HashMap<Integer, Skill> skillList;
	/**
	 * 初始化技能
	 */
	private void initSkill() {
		if (skillList == null)
			skillList = new HashMap<Integer, Skill>();
		Sheet sheet = getConfigSheet(heroSKillSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			Skill s = new Skill();
			s.setCfgID(Integer.parseInt(getCellString(sheet, 1, i)));
			s.setName(getCellString(sheet, 2, i));
			s.setContent(getCellString(sheet, 3, i));
			switch (Integer.parseInt(getCellString(sheet, 4, i))) {
			case 1:
				s.setSkillAttr(GameHeroSkillAttr.physical);
				break;
			case 2:
				s.setSkillAttr(GameHeroSkillAttr.fire);
				break;
			case 3:
				s.setSkillAttr(GameHeroSkillAttr.ice);
				break;
			case 4:
				s.setSkillAttr(GameHeroSkillAttr.thunder);
				break;
			case 5:
				s.setSkillAttr(GameHeroSkillAttr.poison);
				break;
			}
			s.setLevel(Integer.parseInt(getCellString(sheet, 5, i)));
			s.setLevelLimit(Integer.parseInt(getCellString(sheet, 6, i)));
			s.setHeroLevelLimit(Integer.parseInt(getCellString(sheet, 7, i)));
			s.setUpNeedCoin(Integer.parseInt(getCellString(sheet, 8, i)));
			s.setBaseAttr(this.getNumberAttr(Integer.parseInt(getCellString(
					sheet, 9, i))));
			s.setEffect(Integer.parseInt(getCellString(sheet, 10, i)));
			s.setParameter1(Integer.parseInt(getCellString(sheet, 11, i)));
			s.setParameter2(Integer.parseInt(getCellString(sheet, 12, i)));
			s.setParameter3(Integer.parseInt(getCellString(sheet, 13, i)));
			s.setParameter4(Integer.parseInt(getCellString(sheet, 14, i)));
			s.getBaseAttr().setObjId(s.getID());
			s.getBaseAttr().setParentObjType(s.getObjType());
			s.setUsed(false);
			skillList.put(s.getCfgID(), s);
		}
	}

	private HashMap<Integer, String> skillCfgList;
	
	/**
	 * 技能配置
	 */
	private void initSkillCfgList() {
		if (skillCfgList == null)
			skillCfgList = new HashMap<Integer, String>();
		Sheet sheet = getConfigSheet(heroSkillCfgSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			skillCfgList.put(Integer.parseInt(getCellString(sheet, 1, i)),
					getCellString(sheet, 2, i));
		}
	}

	private HashMap<Integer, Equip> equipList;
	/**
	 * 初始化装备
	 */
	private void initEquipList() {
		if (equipList == null)
			equipList = new HashMap<Integer, Equip>();
		Sheet sheet = getConfigSheet(equipSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			Equip s = new Equip();
			s.setCfgID(Integer.parseInt(getCellString(sheet, 1, i)));
			s.setName(getCellString(sheet, 2, i));
			s.setContent(getCellString(sheet, 3, i));
			s.setHeroLevelLimit(Integer.parseInt(getCellString(sheet, 4, i)));
			s.setStarLevel(Integer.parseInt(getCellString(sheet, 5, i)));
			switch (Integer.parseInt(getCellString(sheet, 6, i))) {
			case 1:
				s.setEquipAttr(GameEquipAttr.power);
				break;
			case 2:
				s.setEquipAttr(GameEquipAttr.intelligence);
				break;
			case 3:
				s.setEquipAttr(GameEquipAttr.agile);
				break;
			}
			
			s.setBaseAttr(this.getNumberAttr(Integer.parseInt(getCellString(
					sheet, 7, i))));
			
			s.setMerge_cfgid(Integer.parseInt(getCellString(sheet, 8, i)));
			s.setUnlockpicsLevelLimit(Integer.parseInt(getCellString(sheet, 9,
					i)));
			
			s.getBaseAttr().setObjId(s.getID());
			s.getBaseAttr().setParentObjType(s.getObjType());
			
			s.setCfgData(true);
			
			equipList.put(s.getCfgID(), s);
		}
	}

	private HashMap<Integer, HeroUpPhase> upPhaseList;
	/**
	 * 初始化进阶配置
	 */
	private void initUpPhaseList() {
		if (upPhaseList == null)
			upPhaseList = new HashMap<Integer, HeroUpPhase>();
		Sheet sheet = getConfigSheet(heroUpPhaseSheet);
		for (int i = sheetBeginRow; i < sheet.getRows(); i++) {
			HeroUpPhase up = new HeroUpPhase();
			up.setCfgId(Integer.parseInt(getCellString(sheet, 1, i)));
			up.setEquipCfgIdArray(getCellString(sheet, 2, i).split(","));
			up.setHeroLevelLimit(Integer.parseInt(getCellString(sheet, 3, i)));
			up.setNeedCoint(Integer.parseInt(getCellString(sheet, 4, i)));
			up.setFocusPhase(Integer.parseInt(getCellString(sheet, 5, i)));
			up.setTargetPhase(Integer.parseInt(getCellString(sheet, 6, i)));
			up.setBaseAttr(this.getNumberAttr(Integer.parseInt(getCellString(
					sheet, 7, i))));
			upPhaseList.put(up.getCfgId(), up);
		}
	}
	
	/**
	 * 得到Player的克隆
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player.clone();
	}

	/**
	 * 得到数字结构
	 * 
	 * @param cfgId
	 * @return
	 */
	public NumericalAttribute getNumberAttr(int cfgId) {
		return numericalAttrList.get(cfgId).clone();
	}

	public Hero getHero(int cfgId) {
		return heroList.get(cfgId).clone();
	}

	public Skill getSkill(int cfgId) {
		return skillList.get(cfgId).clone();
	}

	public String getSkillCfg(int cfgId) {
		return skillCfgList.get(cfgId);
	}

	public Equip getEquip(int cfgId) {
		return equipList.get(cfgId);
	}

	public HashMap<Integer, HeroUpPhase> getUpPhaseList()
	{
		return upPhaseList;
	}
	
	public HashMap<Integer, HeroUpPhase> getUpPhaseList(int cfgId) {
		HashMap<Integer, HeroUpPhase> outList = new HashMap<Integer, HeroUpPhase>();
		for (HeroUpPhase up : upPhaseList.values()) {
			if (up.getCfgId() == cfgId) {
				outList.put(up.getTargetPhase(), up);
			}
		}
		return outList;
	}

	public HeroUpPhase getTargetUpPhase(int cfgId, int phaseLevel) {
		return getUpPhaseList(cfgId).get(phaseLevel);
	}

	/**
	 * 通用方法
	 * 
	 * @param sheet
	 * @param colum
	 * @param row
	 * @return
	 */
	private String getCellString(Sheet sheet, int colum, int row) {
		return sheet.getCell(colum, row).getContents();
	}

	/**
	 * 通用方法
	 * 
	 * @param sheetID
	 * @return
	 */
	private Sheet getConfigSheet(int sheetID) {
		try {
			book = Workbook.getWorkbook(new File(fileStr));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获得第一个工作表对象
		Sheet sheet = book.getSheet(sheetID);
		return sheet;
	}

}
