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
 * ����Լ��Ķ������ (����Player�еļ��϶���͵��ö���Ĵ���)
 * 
 * @author publiclll
 *
 */
public class PlayerObjScheduling {
	private ArrayList<GameObject> objList;
	private String PlayerID;
	private DataAdapter adapter;
	/**
	 * ��Ϸ�¼�����
	 */
	private EventScheduling gameEvent;

	public PlayerObjScheduling(String pID) {
		// ���õ�ǰ��PlayerID
		PlayerID = pID;
		objList = new ArrayList<GameObject>();
		adapter = DataAdapter.getInstance();
		gameEvent = EventScheduling.getInstance();
	}

	/**
	 * ��ʼ�����ж���
	 * 
	 * @param PlayerID
	 */
	public void initObject() {
		// ����Ӣ��
		loadHero();
		// ���ص���
		loadEquip();
	}

	/**
	 * ���ص���
	 */
	private void loadEquip() {
		ArrayList<GameObject> equipList = adapter.createObjectList("PlayerID='"
				+ PlayerID + "'", 0, 9999, GameDataType.EQUIP,
				GameObjectType.EQUIP);

		for (GameObject o : equipList) {
			Equip h = (Equip) o;

			// ����װ����ֵ
			NumericalAttribute num = (NumericalAttribute) adapter.createObject(
					h.getBaseAttr().getCfgID(), "objId='" + h.getID() + "'",
					GameDataType.NUMERICAL, GameObjectType.NUMERICAL);

			if (num != null)
				h.setBaseAttr(num);

			// ����Ӣ�۵�װ��
			objList.add(h);

			// ע�������Ϸ�¼�������
			gameEvent.addObj(h);
		}
	}

	/**
	 * ����Ӣ��
	 */
	private void loadHero() {
		ArrayList<GameObject> heroList = adapter.createObjectList("PlayerID='"
				+ PlayerID + "'", 0, 999, GameDataType.HERO,
				GameObjectType.HERO);

		for (GameObject o : heroList) {
			Hero h = (Hero) o;
			// ����Ӣ�۵���ֵ
			NumericalAttribute num = (NumericalAttribute) adapter.createObject(
					h.getBaseAttr().getCfgID(), "objId='" + h.getID() + "'",
					GameDataType.NUMERICAL, GameObjectType.NUMERICAL);

			if (num != null)
				h.setBaseAttr(num);

			h.initBufWithPhaseLevel(h.getPhaseLevel());
			h.setUpPhaseEquipList(h.getPhaseLevel()+1);
			// ����Ӣ�۵�װ��
			ArrayList<GameObject> equipList = adapter.createObjectList(
					"HeroID='" + h.getID() + "'", 0, 9999,
					GameDataType.HEROEQUIP, GameObjectType.EQUIP);

			for (GameObject obj : equipList) {
				Equip e = (Equip) obj;
				h.getEquipList().put(e.getCfgID(), e);
				e.wornEquip_Complete(h);
				gameEvent.addObj(e);
			}
			
			

			// ����Ӣ�۵ļ���
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

			// ע��Ӣ�۵���Ϸ�¼�������
			gameEvent.addObj(h);
		}
	}

	/**
	 * �õ�Ӣ���б�
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
	 * �õ�װ���б�
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
	 * �õ�Ӣ��
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
	 * �õ�װ��
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
