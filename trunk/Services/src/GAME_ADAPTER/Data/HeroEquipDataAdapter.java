package GAME_ADAPTER.Data;

import java.util.ArrayList;

import GAME_COMPONENTS.Equip;
import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBHeroEquip;
import GAME_SCHEDULING.ConfigScheduling;

public class HeroEquipDataAdapter {
	public HeroEquipDataAdapter()
	{}
	/**
	 * DB�б����ת��Ϸ�б����
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
	 * DB����ת��Ϸ����
	 * @param ConfigID
	 * @param obj
	 * @return
	 */
	public Equip convertObject(int ConfigID ,DBBase obj)
	{
		DBHeroEquip dbEquip = (DBHeroEquip)obj;
		Equip equip = ConfigScheduling.getInstance().getEquip(ConfigID);
		
		if(dbEquip != null)
		{
			equip.setID(dbEquip.getEquipID());
			equip.setCfgData(false);
			equip.setHeroID(dbEquip.getHeroID());
		}
		
		return equip;
	}
	/**
	 * ��Ϸ����תDB����
	 * @param obj
	 * @return
	 */
	public DBHeroEquip convertGameDbObject(GameObject obj)
	{
		DBHeroEquip dbEquip = new DBHeroEquip();
		Equip equip = (Equip)obj;
		if(equip != null)
		{
			dbEquip.setID(equip.getID());
			dbEquip.setHeroID(equip.getHeroID());
			dbEquip.setConfigID(equip.getCfgID());
			dbEquip.setEquipID(equip.getID());
		}
		return dbEquip;
	}
}
