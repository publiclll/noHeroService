package GAME_DB.DBOperation;
import java.util.ArrayList;
import java.util.HashMap;

import DBKit.DBKit;
import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;

/**
 * 数据库操作的抽象类
 * @author publiclll
 *
 */
public abstract class DataReceiver {
	protected DBKit db;
	public abstract boolean write();
	public abstract boolean update();
	public abstract boolean update(HashMap<String ,Object> fields);
	public abstract ArrayList<DBBase> list(String sql ,int count ,int number);
	public abstract ArrayList<GameObject> listGameObj(String sql ,int count ,int number);
	public abstract ArrayList<DBBase> list(String sql);
	public abstract ArrayList<GameObject> listGameObj(String sql);
	public abstract DBBase getModel(String sql);
	public abstract GameObject getModelGameObj(String sql);
	public abstract void set(DBBase obj);
	public abstract void set(GameObject obj);
	public abstract int getCount(String sql);
	public abstract int getCount(String sql ,int count ,int number);
	public abstract boolean delete();
	public abstract void closeDB();
	public abstract boolean isExits(String sql);
}
