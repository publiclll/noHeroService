package GAME_ADAPTER.Data;

import java.util.ArrayList;

import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Hero;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBHero;
import GAME_SCHEDULING.ConfigScheduling;

public class HeroDataAdapter {
	public HeroDataAdapter()
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
			Hero hero = convertObject(db.getConfigID(), db);
			gameObjList.add(hero);
		}
		return gameObjList;
	}
	/**
	 * DB对象转游戏对象
	 * @param ConfigID
	 * @param obj
	 * @return
	 */
	public Hero convertObject(int ConfigID ,DBBase obj)
	{
		DBHero dbHero = (DBHero)obj;
		Hero hero = ConfigScheduling.getInstance().getHero(ConfigID);
		
		if(dbHero != null)
		{
			hero.setID(dbHero.getID());
			hero.setCfgData(false);
			hero.setPlayerID(dbHero.getPlayerID());
			hero.setStarLevel(dbHero.getStarLevel());
			hero.setPhaseLevel(dbHero.getPhaseLevel());
			hero.setLevel(dbHero.getLevel());
			hero.setLevelLimit(dbHero.getLevelLimit());
			hero.setExp(dbHero.getExp());
			hero.setExpLimit(dbHero.getExpLimit());
		}
		
		return hero;
	}
	/**
	 * 游戏对象转DB对象
	 * @param obj
	 * @return
	 */
	public DBHero convertGameDbObject(GameObject obj)
	{
		DBHero dbHero = new DBHero();
		Hero hero = (Hero)obj;
		
		if(hero != null)
		{
			dbHero.setID(hero.getID());
			dbHero.setConfigID(hero.getCfgID());
			dbHero.setPlayerID(hero.getPlayerID());
			dbHero.setStarLevel(hero.getStarLevel());
			dbHero.setStarLevelLimit(0);
			dbHero.setPhaseLevel(hero.getPhaseLevel());
			dbHero.setPhaseLevelLimit(0);
			dbHero.setLevel(hero.getLevel());
			dbHero.setLevelLimit(hero.getLevelLimit());
			dbHero.setExp(hero.getExp());
			dbHero.setExpLimit(hero.getExpLimit());
		}
		
		return dbHero;
	}
}
