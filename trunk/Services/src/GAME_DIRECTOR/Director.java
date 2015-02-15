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
 * 导演类，主要的游戏逻辑控制
 * 
 * @author publiclll
 *
 */
public class Director {

	private Player player;

	/**
	 * 游戏事件调度
	 */
	private EventScheduling gameEvent;

	/**
	 * 游戏数据适配器
	 */
	private DataAdapter adapter;

	/**
	 * 构造
	 */
	public Director() {
		gameEvent = EventScheduling.getInstance();
		adapter = DataAdapter.getInstance();
	}

	/**
	 * 设置用户
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;

		// 建立对象集合
		this.player.createObjCollection();
		// 加载Player上所有的游戏对象
		this.player.initObjCollection();
		// 注册玩家的游戏事件
		gameEvent.addObj(this.player);
	}

	/**
	 * 测试方法
	 */
	public void showPlayerInfo() {

		Hero hero = player.getObjCollection().getHeroList().get(2);
		Log("英雄 : " + hero.getName());
		Log(hero.getHeroAttr().toString());
		Log("  力量：" + hero.getBaseAttr().getPower() + "+"
				+ hero.getBufAttr().getPower());
		Log("  敏捷：" + hero.getBaseAttr().getAgile() + "+"
				+ hero.getBufAttr().getAgile());
		Log("  智力：" + hero.getBaseAttr().getIntelligence() + "+"
				+ hero.getBufAttr().getIntelligence());
		Log("===============");
		Log("装备：");
		for (Equip e : hero.getEquipList().values()) {
			Log(" " + e.getName());
		}
		Log("===============");
		Log("进阶所需装备：");
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
		 * Log("道具 : " + e.getName()); Log(e.getEquipAttr().toString());
		 * Log("  力量：" + e.getBaseAttr().getPower() ); Log("  敏捷：" +
		 * e.getBaseAttr().getAgile() ); Log("  智力：" +
		 * e.getBaseAttr().getIntelligence() ); Log("==============="); }
		 */

	}

	/**
	 * 升级英雄经验
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
				// 增长的经验值
				Integer c = plusExp - h.getExpLimit();
				h.setExp(c);
				// 发送Hero升级的消息
				gameEvent.doNotify(GameNotifyType.LEVELUP_TYPE, h.getID(), c);
			} else {
				h.setExp(plusExp);
			}
			// 修改游戏对象数据
			HashMap<String, Object> fields = new HashMap<String, Object>();
			fields.put("Exp", h.getExp());
			fields.put("ExpLimit", h.getExpLimit());
			fields.put("Level", h.getLevel());

			adapter.doModifyObject(h, fields);
			adapter.doModifyObject(h.getBaseAttr());
			// 推送
		}
	}

	/**
	 * 玩家增长经验
	 * 
	 * @param exp
	 */
	public void plusPlayerExp(int exp) {
		if (player.getLevel() == player.getLevelLimit()) {
			this.Log("玩家：" + this.player.getNickName() + "已经满级！");
		} else {
			int plusExp = player.getExp() + exp;
			if (plusExp >= player.getExpLimit()) {
				// 增长的经验值
				Integer c = plusExp - player.getExpLimit();
				player.setExp(c);
				// 发送Player升级的消息
				gameEvent.doNotify(GameNotifyType.LEVELUP_TYPE, player.getID(),
						c);
			} else {
				player.setExp(plusExp);
				// 修改游戏对象数据
				HashMap<String, Object> fields = new HashMap<String, Object>();
				fields.put("Exp", player.getExp());
				fields.put("ExpLimit", player.getExpLimit());
				fields.put("Level", player.getLevel());

				adapter.doModifyObject(player, fields);
				// 推送
			}
		}
	}

	/**
	 * 英雄进阶
	 * 
	 * @param hero_cfgId
	 */
	public void heroUpPhase(int hero_cfgId) {
		Hero hero = player.getObjCollection().getHero(hero_cfgId);
		Validator validator = new Validator(player, hero);
		validator.validatorObject();
		if (hero.getEquipList().size() != hero.getEquipCountLimit()) {
			Log("[ERROR]:装备不齐！");
			return;
		}
		if (hero.getUpPhaseLevel_LevelLimit() > hero.getLevel()) {
			Log("[ERROR]:英雄等级不足！");
			return;
		}
		if (player.getCoin() < hero.getUpPhaseLevelNeedCoint()) {
			Log("[ERROR]:金币不足！");
			return;
		}
		if (!validator.isValidator()) {
			validator.prints();
			return;
		}

		// 验证完成
		// 消除所有装备
		for (Equip e : hero.getEquipList().values()) {
			e.setObjDbType(GameDataType.HEROEQUIP);
			adapter.doDeleteObject(e);
			gameEvent.removeObj(e);
		}
		hero.getEquipList().clear();

		player.setCoin(player.getCoin() - hero.lossCoin());

		// 保存玩家信息
		HashMap<String, Object> fields = new HashMap<String, Object>();
		fields.put("Coin", player.getCoin());
		adapter.doModifyObject(player, fields);

		// 发送品阶升级信息
		gameEvent.doNotify(GameNotifyType.HEROUPPHASE_TYPE, hero.getID(), null);
		// 保存英雄信息
		HashMap<String, Object> heroFields = new HashMap<String, Object>();
		heroFields.put("PhaseLevel", hero.getPhaseLevel());
		adapter.doModifyObject(hero, heroFields);

		// 推送信息

	}

	/**
	 * 穿装备
	 * 
	 * @param cfgId
	 */
	public void wornEquip(int hero_cfgId, int equip_cfgId) {
		Hero hero = player.getObjCollection().getHero(hero_cfgId);
		Equip equip = player.getObjCollection().getEquip(equip_cfgId);

		if(hero == null)
		{
			Log("[ERROR]英雄对象空指针");
			return ;
		}
		if(equip == null)
		{
			Log("[ERROR]装备对象空指针");
			return ;
		}
		if(equip.getHeroLevelLimit() > hero.getLevel())
		{
			Log("[ERROR]英雄等级不够");
			return ;
		}
		if(hero.getUpPhaseEquipList().get(equip.getCfgID())==null)
		{
			Log("[ERROR]不能装备这个");
			return ;
		}
		if(hero.getEquipList().get(equip.getCfgID())!=null)
		{
			Log("[ERROR]不能重复装备这个");
			return ;
		}

		/**
		 * 验证完了，没问题，穿
		 */
		hero.getEquipList().put(equip.getCfgID(), equip);
		
		/**
		 * 进行数据库操作
		 */
		if ((equip.getCount() - 1) <= 0) {
			adapter.doDeleteObject(equip);
		} else {
			equip.setCount(equip.getCount() - 1);
			adapter.doModifyObject(equip);
		}

		/**
		 * 发送装备穿完的请求
		 */
		gameEvent.doNotify(GameNotifyType.WORNEQUIP_TYPE, equip.getID(), hero);
		if ((equip.getCount() - 1) <= 0) {
			//销毁对象
			player.getObjCollection().removeObj(equip);
		}
		
		/**
		 * 保存
		 */
		adapter.doSaveObject(equip);
	}

	/**
	 * 充值
	 * 
	 * @param Gold
	 */
	public void playerCharge(int Gold) {
		Long plusGold = player.getDiamond() + Gold;
		player.setDiamond(plusGold);
		// 发送充值成功的消息
		gameEvent
				.doNotify(GameNotifyType.CHARGE_TYPE, player.getID(), plusGold);
		adapter.doModifyObject(player);
	}

	/**
	 * 初始化一个Equip
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
			Log("已经拥有：" + equip.getName() + "，+1");
		}
	}

	/**
	 * 初始化一个Hero
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
				Log("已经拥有：" + hero.getName() + "，创建失败!");
			}
		}
	}

	/**
	 * 玩家登出，掉线等情况
	 */
	public void playerLoginOut() {
		gameEvent.removeAll();
	}

	/**
	 * 获取当前时间指定秒钟数之后的时间戳
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
	 * 测试的消息输出
	 * 
	 * @param s
	 */
	private void Log(String s) {
		System.out.println(s);
	}
}
