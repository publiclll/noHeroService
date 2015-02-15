package GAME_SCHEDULING;

import java.util.ArrayList;

import GAME_ADAPTER.DataAdapter;
import GAME_COMPONENTS.Equip;
import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Hero;
import GAME_COMPONENTS.NumericalAttribute;
import GAME_COMPONENTS.Skill;
import GAME_ENUM.GameDataType;
import GAME_ENUM.GameObjectType;

/**
 * 玩家自己的对象调度 (减少Player中的集合对象和调用对象的代码)
 * 
 * @author publiclll
 *
 */
public class PlayerObjScheduling {
	private ArrayList<GameObject> objList;
	private String PlayerID;
	private DataAdapter adapter;
	/**
	 * 游戏事件调度
	 */
	private EventScheduling gameEvent;

	public PlayerObjScheduling(String pID) {
		// 设置当前的PlayerID
		PlayerID = pID;
		objList = new ArrayList<GameObject>();
		adapter = DataAdapter.getInstance();
		gameEvent = EventScheduling.getInstance();
	}

	/**
	 * 初始化所有对象
	 * 
	 * @param PlayerID
	 */
	public void initObject() {
		// 加载英雄
		loadHero();
		// 加载道具
		loadEquip();
	}

	/**
	 * 加载道具
	 */
	private void loadEquip() {
		ArrayList<GameObject> equipList = adapter.createObjectList("PlayerID='"
				+ PlayerID + "'", 0, 9999, GameDataType.EQUIP,
				GameObjectType.EQUIP);

		for (GameObject o : equipList) {
			Equip h = (Equip) o;

			// 加载装备数值
			NumericalAttribute num = (NumericalAttribute) adapter.createObject(
					h.getBaseAttr().getCfgID(), "objId='" + h.getID() + "'",
					GameDataType.NUMERICAL, GameObjectType.NUMERICAL);

			if (num != null)
				h.setBaseAttr(num);

			// 加载英雄的装备
			objList.add(h);

			// 注册对象到游戏事件管理器
			gameEvent.addObj(h);
		}
	}

	/**
	 * 加载英雄
	 */
	private void loadHero() {
		ArrayList<GameObject> heroList = adapter.createObjectList("PlayerID='"
				+ PlayerID + "'", 0, 999, GameDataType.HERO,
				GameObjectType.HERO);

		for (GameObject o : heroList) {
			Hero h = (Hero) o;
			// 加载英雄的数值
			NumericalAttribute num = (NumericalAttribute) adapter.createObject(
					h.getBaseAttr().getCfgID(), "objId='" + h.getID() + "'",
					GameDataType.NUMERICAL, GameObjectType.NUMERICAL);

			if (num != null)
				h.setBaseAttr(num);

			h.initBufWithPhaseLevel(h.getPhaseLevel());
			h.setUpPhaseEquipList(h.getPhaseLevel()+1);
			// 加载英雄的装备
			ArrayList<GameObject> equipList = adapter.createObjectList(
					"HeroID='" + h.getID() + "'", 0, 9999,
					GameDataType.HEROEQUIP, GameObjectType.EQUIP);

			for (GameObject obj : equipList) {
				Equip e = (Equip) obj;
				h.getEquipList().put(e.getCfgID(), e);
				e.wornEquip_Complete(h);
				gameEvent.addObj(e);
			}
			
			

			// 激活英雄的技能
			for (Skill s : h.getSkillList().values()) {
				if (h.getLevel() >= s.getHeroLevelLimit()) {
					s.setUsed(true);
					s.setHeroID(h.getID());
					
					boolean isExits = adapter.isExitsObject(
							"HeroID='" + h.getID() + "' and ConfigID="
									+ s.getCfgID(), GameDataType.SKILL);
					if (!isExits) {
						adapter.doSaveObject(s);
					} else {
						s = (Skill)adapter.createObject(
								s.getCfgID(),
								"HeroID='" + h.getID() + "' and ConfigID="
										+ s.getCfgID(), GameDataType.SKILL,
								GameObjectType.SKILL);
					}
					gameEvent.addObj(s);
				}
			}
			objList.add(h);

			// 注册英雄到游戏事件管理器
			gameEvent.addObj(h);
		}
	}

	/**
	 * 得到英雄列表
	 * 
	 * @return
	 */
	public ArrayList<Hero> getHeroList() {
		ArrayList<Hero> outList = new ArrayList<Hero>();
		for (GameObject o : objList) {
			if (o.getObjType() == GameObjectType.HERO)
				outList.add((Hero) o);
		}
		return outList;
	}

	/**
	 * 得到装备列表
	 * 
	 * @return
	 */
	public ArrayList<Equip> getEquipList() {
		ArrayList<Equip> outList = new ArrayList<Equip>();
		for (GameObject o : objList) {
			if (o.getObjType() == GameObjectType.EQUIP)
				outList.add((Equip) o);
		}
		return outList;
	}

	/**
	 * 得到英雄
	 * 
	 * @param cfgID
	 * @return
	 */
	public Hero getHero(int cfgID) {
		Hero hero = null;
		for (GameObject o : objList) {
			if (o.getObjType() == GameObjectType.HERO && o.getCfgID() == cfgID) {
				hero = (Hero) o;
				break;
			}
		}
		return hero;
	}

	/**
	 * 得到装备
	 * 
	 * @param cfgID
	 * @return
	 */
	public Equip getEquip(int cfgID) {
		Equip equip = null;
		for (GameObject o : objList) {
			if (o.getObjType() == GameObjectType.EQUIP && o.getCfgID() == cfgID) {
				equip = (Equip) o;
				break;
			}
		}
		return equip;
	}

	public void addObj(GameObject obj) {
		if (!objList.contains(obj)) {
			objList.add(obj);
			gameEvent.addObj(obj);
		}
	}

	public void removeObj(GameObject obj) {
		objList.remove(obj);
		gameEvent.removeObj(obj);
	}

	public void removeAll() {
		objList.removeAll(objList);
	}
}
