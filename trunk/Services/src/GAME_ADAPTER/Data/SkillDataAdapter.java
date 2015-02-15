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
	 * DB�б����ת��Ϸ�б����
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
	 * DB����ת��Ϸ����
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
	 * ��Ϸ����תDB����
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
