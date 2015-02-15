package GAME_ADAPTER.Data;

import java.util.ArrayList;

import GAME_COMPONENTS.Equip;
import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Hero;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBEquip;
import GAME_DB.DBModel.DBHero;
import GAME_SCHEDULING.ConfigScheduling;

/**
 * 装备数据库适配
 * @author publiclll
 *
 */
public class EquipDataAdapter {
	public EquipDataAdapter()
	{}
	/**
	 * DB列表对象转游戏列表对象
	 * @param objList
	 * @return
	 */
	public ArrayList<GameObject> convertArrayList(ArrayList<DBBase> objList)
	{
		ArrayList<GameObject> gameObjList = new ArrayList<GameObject>();
		for(DBBase db : objList)
		{
			Equip equip = convertObject(db.getConfigID(), db);
			gameObjList.add(equip);
		}
		return gameObjList;
	}
	/**
	 * DB对象转游戏对象
	 * @param ConfigID
	 * @param obj
	 * @return
	 */
	public Equip convertObject(int ConfigID ,DBBase obj)
	{
		DBEquip dbEquip = (DBEquip)obj;
		Equip equip = ConfigScheduling.getInstance().getEquip(ConfigID);
		
		if(dbEquip != null)
		{
			equip.setID(dbEquip.getID());
			equip.setCfgData(false);
			equip.setPlayerID(dbEquip.getPlayerID());
			equip.setCount(dbEquip.getCount());
		}
		
		return equip;
	}
	/**
	 * 游戏对象转DB对象
	 * @param obj
	 * @return
	 */
	public DBEquip convertGameDbObject(GameObject obj)
	{
		DBEquip dbEquip = new DBEquip();
		Equip equip = (Equip)obj;
		if(equip != null)
		{
			dbEquip.setID(equip.getID());
			dbEquip.setConfigID(equip.getCfgID());
			dbEquip.setPlayerID(equip.getPlayerID());
			dbEquip.setCount(equip.getCount());
		}
		return dbEquip;
	}
}
