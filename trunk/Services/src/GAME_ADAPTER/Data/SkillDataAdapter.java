package GAME_ADAPTER.Data;

import java.util.ArrayList;

import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Skill;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBSkill;
import GAME_SCHEDULING.ConfigScheduling;

public class SkillDataAdapter {
	public SkillDataAdapter()
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
			Skill skill = convertObject(db.getConfigID(), db);
			gameObjList.add(skill);
		}
		return gameObjList;
	}
	/**
	 * DB对象转游戏对象
	 * @param ConfigID
	 * @param obj
	 * @return
	 */
	public Skill convertObject(int ConfigID ,DBBase obj)
	{
		DBSkill dbSkill = (DBSkill)obj;
		Skill skill = ConfigScheduling.getInstance().getSkill(ConfigID);
		
		if(dbSkill != null)
		{
			skill.setCfgData(false);
			skill.setLevel(dbSkill.getLevel());
			skill.setLevelLimit(dbSkill.getLevelLimit());
			skill.setHeroID(dbSkill.getHeroID());
		}
		
		return skill;
	}
	/**
	 * 游戏对象转DB对象
	 * @param obj
	 * @return
	 */
	public DBSkill convertGameDbObject(GameObject obj)
	{
		DBSkill dbSkill = new DBSkill();
		Skill s = (Skill)obj;
		if(s != null)
		{
			dbSkill.setID(s.getID());
			dbSkill.setHeroID(s.getHeroID());
			dbSkill.setConfigID(s.getCfgID());
			dbSkill.setLevel(s.getLevel());
			dbSkill.setLevelLimit(s.getLevelLimit());
		}
		return dbSkill;
	}
}
