package GAME_ADAPTER;

import java.util.ArrayList;
import java.util.HashMap;

import GAME_DB.DBOperation.ReadCommand;
import GAME_DB.DBOperation.WriteCommand;
import GAME_ADAPTER.Data.EquipDataAdapter;
import GAME_ADAPTER.Data.HeroDataAdapter;
import GAME_ADAPTER.Data.HeroEquipDataAdapter;
import GAME_ADAPTER.Data.PlayerDataAdapter;
import GAME_ADAPTER.Data.SkillDataAdapter;
import GAME_COMPONENTS.GameObject;
import GAME_ENUM.GameDataType;
import GAME_ENUM.GameObjectType;
import GAME_INTERFACE.IReadCommand;
import GAME_INTERFACE.IWriteCommand;

/**
 * 数据适配器，游戏对象和游戏数据库对象互相转换
 * 
 * @author publiclll
 *
 */
public class DataAdapter {
	private static DataAdapter adapter = null;

	private DataAdapter() {
	}

	public static DataAdapter getInstance() {
		if (adapter == null)
			adapter = new DataAdapter();

		return adapter;
	}

	private IWriteCommand createWriteCommand(GameDataType type) {
		return new WriteCommand(type, null);
	}

	private IReadCommand createReadCommand(GameDataType type) {
		return new ReadCommand(type, null);
	}

	/**
	 * 创建对象
	 * 
	 * @param ConfigID
	 * @param Sql
	 * @param dbType
	 * @param gameType
	 * @return
	 */
	public GameObject createObject(int ConfigID, String Sql,
			GameDataType dbType, GameObjectType gameType) {

		GameObject obj = null;
		switch (dbType) {
		case PLAYER:
			obj = new PlayerDataAdapter().convertObject(ConfigID,
					createReadCommand(dbType).executeModel(Sql));
			break;
		case NUMERICAL:
			obj = createReadCommand(dbType).executeModelGameObj(Sql);
			break;
		case HERO:
			obj = new HeroDataAdapter().convertObject(ConfigID,
					createReadCommand(dbType).executeModel(Sql));
			break;
		case EQUIP:
			obj = new EquipDataAdapter().convertObject(ConfigID, 
					createReadCommand(dbType).executeModel(Sql));
			break;
		case HEROEQUIP:
			obj = new HeroEquipDataAdapter().convertObject(ConfigID, createReadCommand(dbType).executeModel(Sql));
			break;
		case SKILL:
			obj = new SkillDataAdapter().convertObject(ConfigID, createReadCommand(dbType).executeModel(Sql));
			break;
		default:
			break;
		}
		return obj;
	}

	/**
	 * 创建列表
	 * 
	 * @param sql
	 * @param Count
	 * @param Number
	 * @param dbType
	 * @param gameType
	 * @return
	 */
	public ArrayList<GameObject> createObjectList(String sql, int Count,
			int Number, GameDataType dbType, GameObjectType gameType) {

		ArrayList<GameObject> list = null;
		switch (dbType) {
		case PLAYER:
			break;
		case NUMERICAL:
			list = createReadCommand(dbType).executeGameObj(sql, Count, Number);
			break;
		case HERO:
			list =  new HeroDataAdapter().convertArrayList( createReadCommand(dbType).execute(sql, Count, Number) );
			break;
		case EQUIP:
			list = new EquipDataAdapter().convertArrayList( createReadCommand(dbType).execute(sql, Count, Number) );
			break;
		case HEROEQUIP:
			list = new HeroEquipDataAdapter().convertArrayList( createReadCommand(dbType).execute(sql, Count, Number) );
			break;
		case SKILL:
			list = new SkillDataAdapter().convertArrayList( createReadCommand(dbType).execute(sql, Count, Number) );
			break;
		default:
			break;
		}

		return list;
	}

	/**
	 * 保存
	 * 
	 * @param obj
	 * @param dbType
	 * @param gameType
	 * @return
	 */
	public boolean doSaveObject(GameObject obj) {
		boolean isComplete = false;
		IWriteCommand write = createWriteCommand(obj.getObjDbType());
		switch (obj.getObjDbType()) {
		case PLAYER:
			write.setObject(new PlayerDataAdapter().convertGameDbObject(obj));
			break;
		case NUMERICAL:
			write.setObject(obj);
			break;
		case HERO:
			write.setObject(new HeroDataAdapter().convertGameDbObject(obj));
			break;
		case EQUIP:
			write.setObject(new EquipDataAdapter().convertGameDbObject(obj));
			break;
		case HEROEQUIP:
			write.setObject(new HeroEquipDataAdapter().convertGameDbObject(obj));
			break;
		case SKILL:
			write.setObject( new SkillDataAdapter().convertGameDbObject(obj) );
			break;
		default:
			break;
		}
		isComplete = write.executeAdd();
		return isComplete;
	}

	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @param dbType
	 * @param gameType
	 * @return
	 */
	public boolean doModifyObject(GameObject obj) {
		boolean isComplete = false;
		IWriteCommand write = createWriteCommand(obj.getObjDbType());
		switch (obj.getObjDbType()) {
		case PLAYER:
			write.setObject(new PlayerDataAdapter().convertGameDbObject(obj));
			break;
		case HERO:
			write.setObject(new HeroDataAdapter().convertGameDbObject(obj));
			break;
		case NUMERICAL:
			write.setObject(obj);
			break;
		case EQUIP:
			write.setObject(new EquipDataAdapter().convertGameDbObject(obj));
			break;
		case HEROEQUIP:
			write.setObject(new HeroEquipDataAdapter().convertGameDbObject(obj));
			break;
		case SKILL:
			write.setObject( new SkillDataAdapter().convertGameDbObject(obj) );
			break;
		default:
			break;
		}
		isComplete = write.executeUpdate();
		return isComplete;
	}

	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @param fields
	 * @return
	 */
	public boolean doModifyObject(GameObject obj, HashMap<String, Object> fields) {
		boolean isComplete = false;
		IWriteCommand write = createWriteCommand(obj.getObjDbType());
		switch (obj.getObjDbType()) {
		case PLAYER:
			write.setObject(new PlayerDataAdapter().convertGameDbObject(obj));
			break;
		case HERO:
			write.setObject(new HeroDataAdapter().convertGameDbObject(obj));
			break;
		case EQUIP:
			write.setObject(new EquipDataAdapter().convertGameDbObject(obj));
			break;
		case HEROEQUIP:
			write.setObject(new HeroEquipDataAdapter().convertGameDbObject(obj));
			break;
		case SKILL:
			write.setObject( new SkillDataAdapter().convertGameDbObject(obj) );
			break;
		default:
			break;
		}
		isComplete = write.executeUpdate(fields);
		return isComplete;
	}

	/**
	 * 删除对象
	 * 
	 * @param obj
	 * @param dbType
	 * @param gameType
	 * @return
	 */
	public boolean doDeleteObject(GameObject obj) {
		IWriteCommand write = createWriteCommand(obj.getObjDbType());
		switch(obj.getObjDbType())
		{
		case EQUIP:
			write.setObject( new EquipDataAdapter().convertGameDbObject(obj) );
			break;
		case HEROEQUIP:
			write.setObject( new HeroEquipDataAdapter().convertGameDbObject(obj) );
			break;
		case HERO:
			break;
		case PLAYER:
			break;
		case SKILL:
			break;
		default:
			break;
		}
		
		return write.executeDelete();
	}

	public boolean isExitsObject(String Sql, GameDataType dbType) {
		return createReadCommand(dbType).isExits(Sql);
	}

	public int getObjectCount(String Sql, GameDataType dbType) {
		return createReadCommand(dbType).getCount(Sql);
	}

	public int getObjectCount(String Sql, int Count, int Number,
			GameDataType dbType) {
		return createReadCommand(dbType).getCount(Sql, Count, Number);
	}
}
