package GAME_DIRECTOR;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import GAME_ADAPTER.DataAdapter;
import GAME_COMPONENTS.Equip;
import GAME_COMPONENTS.Hero;
import GAME_COMPONENTS.Player;
import GAME_ENUM.GameDataType;
import GAME_ENUM.GameNotifyType;
import GAME_ENUM.GameObjectType;
import GAME_SCHEDULING.EventScheduling;

/**
 * �����࣬��Ҫ����Ϸ�߼�����
 * 
 * @author publiclll
 *
 */
public class Director {

	private Player player;

	/**
	 * ��Ϸ�¼�����
	 */
	private EventScheduling gameEvent;

	/**
	 * ��Ϸ����������
	 */
	private DataAdapter adapter;

	/**
	 * ����
	 */
	public Director() {
		gameEvent = EventScheduling.getInstance();
		adapter = DataAdapter.getInstance();
	}

	/**
	 * �����û�
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;

		// �������󼯺�
		this.player.createObjCollection();
		// ����Player�����е���Ϸ����
		this.player.initObjCollection();
		// ע����ҵ���Ϸ�¼�
		gameEvent.addObj(this.player);
	}

	/**
	 * ���Է���
	 */
	public void showPlayerInfo() {

		Hero hero = player.getObjCollection().getHeroList().get(2);
		Log("Ӣ�� : " + hero.getName());
		Log(hero.getHeroAttr().toString());
		Log("  ������" + hero.getBaseAttr().getPower() + "+"
				+ hero.getBufAttr().getPower());
		Log("  ���ݣ�" + hero.getBaseAttr().getAgile() + "+"
				+ hero.getBufAttr().getAgile());
		Log("  ������" + hero.getBaseAttr().getIntelligence() + "+"
				+ hero.getBufAttr().getIntelligence());
		Log("===============");
		Log("װ����");
		for (Equip e : hero.getEquipList().values()) {
			Log(" " + e.getName());
		}
		Log("===============");
		Log("��������װ����");
		if(hero.getUpPhaseEquipList()!=null)
		{
			for(Equip e: hero.getUpPhaseEquipList().values())
			{
				Log(" " + e.getName());
			}
		}
		/*
		 * ArrayList<Equip> equipList =
		 * player.getObjCollection().getEquipList(); for(Equip e: equipList) {
		 * Log("���� : " + e.getName()); Log(e.getEquipAttr().toString());
		 * Log("  ������" + e.getBaseAttr().getPower() ); Log("  ���ݣ�" +
		 * e.getBaseAttr().getAgile() ); Log("  ������" +
		 * e.getBaseAttr().getIntelligence() ); Log("==============="); }
		 */

	}

	/**
	 * ����Ӣ�۾���
	 * 
	 * @param cfgId
	 * @param exp
	 */
	public void plusHeroExp(int cfgId, int exp) {
		Hero h = player.getObjCollection().getHero(cfgId);
		/*
		 * if(h == null) return;
		 */
		if (h.getLevel() == h.getLevelLimit()) {
		} else {
			int plusExp = h.getExp() + exp;
			if (plusExp >= h.getExpLimit()) {
				// �����ľ���ֵ
				Integer c = plusExp - h.getExpLimit();
				h.setExp(c);
				// ����Hero��������Ϣ
				gameEvent.doNotify(GameNotifyType.LEVELUP_TYPE, h.getID(), c);
			} else {
				h.setExp(plusExp);
			}
			// �޸���Ϸ��������
			HashMap<String, Object> fields = new HashMap<String, Object>();
			fields.put("Exp", h.getExp());
			fields.put("ExpLimit", h.getExpLimit());
			fields.put("Level", h.getLevel());

			adapter.doModifyObject(h, fields);
			adapter.doModifyObject(h.getBaseAttr());
			// ����
		}
	}

	/**
	 * �����������
	 * 
	 * @param exp
	 */
	public void plusPlayerExp(int exp) {
		if (player.getLevel() == player.getLevelLimit()) {
			this.Log("��ң�" + this.player.getNickName() + "�Ѿ�������");
		} else {
			int plusExp = player.getExp() + exp;
			if (plusExp >= player.getExpLimit()) {
				// �����ľ���ֵ
				Integer c = plusExp - player.getExpLimit();
				player.setExp(c);
				// ����Player��������Ϣ
				gameEvent.doNotify(GameNotifyType.LEVELUP_TYPE, player.getID(),
						c);
			} else {
				player.setExp(plusExp);
				// �޸���Ϸ��������
				HashMap<String, Object> fields = new HashMap<String, Object>();
				fields.put("Exp", player.getExp());
				fields.put("ExpLimit", player.getExpLimit());
				fields.put("Level", player.getLevel());

				adapter.doModifyObject(player, fields);
				// ����
			}
		}
	}

	/**
	 * Ӣ�۽���
	 * 
	 * @param hero_cfgId
	 */
	public void heroUpPhase(int hero_cfgId) {
		Hero hero = player.getObjCollection().getHero(hero_cfgId);
		Validator validator = new Validator(player, hero);
		validator.validatorObject();
		if (hero.getEquipList().size() != hero.getEquipCountLimit()) {
			Log("[ERROR]:װ�����룡");
			return;
		}
		if (hero.getUpPhaseLevel_LevelLimit() > hero.getLevel()) {
			Log("[ERROR]:Ӣ�۵ȼ����㣡");
			return;
		}
		if (player.getCoin() < hero.getUpPhaseLevelNeedCoint()) {
			Log("[ERROR]:��Ҳ��㣡");
			return;
		}
		if (!validator.isValidator()) {
			validator.prints();
			return;
		}

		// ��֤���
		// ��������װ��
		for (Equip e : hero.getEquipList().values()) {
			e.setObjDbType(GameDataType.HEROEQUIP);
			adapter.doDeleteObject(e);
			gameEvent.removeObj(e);
		}
		hero.getEquipList().clear();

		player.setCoin(player.getCoin() - hero.lossCoin());

		// ���������Ϣ
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put("Coin", player.getCoin());
		adapter.doModifyObject(player, fields);

		// ����Ʒ��������Ϣ
		gameEvent.doNotify(GameNotifyType.HEROUPPHASE_TYPE, hero.getID(), null);
		// ����Ӣ����Ϣ
		HashMap<String, Object> heroFields = new HashMap<String, Object>();
		heroFields.put("PhaseLevel", hero.getPhaseLevel());
		adapter.doModifyObject(hero, heroFields);

		// ������Ϣ

	}

	/**
	 * ��װ��
	 * 
	 * @param cfgId
	 */
	public void wornEquip(int hero_cfgId, int equip_cfgId) {
		Hero hero = player.getObjCollection().getHero(hero_cfgId);
		Equip equip = player.getObjCollection().getEquip(equip_cfgId);

		if(hero == null)
		{
			Log("[ERROR]Ӣ�۶����ָ��");
			return ;
		}
		if(equip == null)
		{
			Log("[ERROR]װ�������ָ��");
			return ;
		}
		if(equip.getHeroLevelLimit() > hero.getLevel())
		{
			Log("[ERROR]Ӣ�۵ȼ�����");
			return ;
		}
		if(hero.getUpPhaseEquipList().get(equip.getCfgID())==null)
		{
			Log("[ERROR]����װ�����");
			return ;
		}
		if(hero.getEquipList().get(equip.getCfgID())!=null)
		{
			Log("[ERROR]�����ظ�װ�����");
			return ;
		}

		/**
		 * ��֤���ˣ�û���⣬��
		 */
		hero.getEquipList().put(equip.getCfgID(), equip);
		
		/**
		 * �������ݿ����
		 */
		if ((equip.getCount() - 1) <= 0) {
			adapter.doDeleteObject(equip);
		} else {
			equip.setCount(equip.getCount() - 1);
			adapter.doModifyObject(equip);
		}

		/**
		 * ����װ�����������
		 */
		gameEvent.doNotify(GameNotifyType.WORNEQUIP_TYPE, equip.getID(), hero);
		if ((equip.getCount() - 1) <= 0) {
			//���ٶ���
			player.getObjCollection().removeObj(equip);
		}
		
		/**
		 * ����
		 */
		adapter.doSaveObject(equip);
	}

	/**
	 * ��ֵ
	 * 
	 * @param Gold
	 */
	public void playerCharge(int Gold) {
		Long plusGold = player.getDiamond() + Gold;
		player.setDiamond(plusGold);
		// ���ͳ�ֵ�ɹ�����Ϣ
		gameEvent
				.doNotify(GameNotifyType.CHARGE_TYPE, player.getID(), plusGold);
		adapter.doModifyObject(player);
	}

	/**
	 * ��ʼ��һ��Equip
	 * 
	 * @param cfgId
	 */
	public void initEquip(int cfgId) {
		Equip equip = (Equip) adapter.createObject(cfgId,
				"PlayerID='" + player.getID() + "'  and ConfigID=" + cfgId,
				GameDataType.EQUIP, GameObjectType.EQUIP);

		if (equip.isCfgData()) {

			equip.setPlayerID(player.getID());
			equip.setCount(1);

			adapter.doSaveObject(equip);
			adapter.doSaveObject(equip.getBaseAttr());

			player.getObjCollection().addObj(equip);
			gameEvent.addObj(equip);

		} else {
			equip.setCount(equip.getCount() + 1);
			adapter.doModifyObject(equip);
			Log("�Ѿ�ӵ�У�" + equip.getName() + "��+1");
		}
	}

	/**
	 * ��ʼ��һ��Hero
	 * 
	 * @param cfgId
	 */
	public void initHero(int cfgId) {
		Hero hero = (Hero) adapter.createObject(cfgId,
				"PlayerID='" + player.getID() + "'  and ConfigID=" + cfgId,
				GameDataType.HERO, GameObjectType.HERO);
		if (hero.isCfgData()) {
			hero.setPlayerID(player.getID());
			hero.getBaseAttr().setParentObjType(GameObjectType.HERO);
			hero.getBaseAttr().setObjId(hero.getID());

			adapter.doSaveObject(hero);
			adapter.doSaveObject(hero.getBaseAttr());

			player.getObjCollection().addObj(hero);
			gameEvent.addObj(hero);
		} else {
			if (player.getObjCollection().getHero(cfgId) != null) {
				Log("�Ѿ�ӵ�У�" + hero.getName() + "������ʧ��!");
			}
		}
	}

	/**
	 * ��ҵǳ������ߵ����
	 */
	public void playerLoginOut() {
		gameEvent.removeAll();
	}

	/**
	 * ��ȡ��ǰʱ��ָ��������֮���ʱ���
	 * 
	 * @param second
	 * @return
	 */
	private Timestamp timeStampAfterSecond(int second) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, second);
		return new Timestamp(now.getTime().getTime());
	}

	/**
	 * ���Ե���Ϣ���
	 * 
	 * @param s
	 */
	private void Log(String s) {
		System.out.println(s);
	}
}
