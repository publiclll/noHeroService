package GAME_INTERFACE;
import java.util.ArrayList;

import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;

public interface IReadCommand {
	public ArrayList<DBBase> execute(String sql);
	public ArrayList<GameObject> executeGameObj(String sql);
	public DBBase executeModel(String sql);
	public GameObject executeModelGameObj(String sql);
	public ArrayList<DBBase> execute(String sql ,int count ,int number);
	public ArrayList<GameObject> executeGameObj(String sql ,int count ,int number);
	public int getCount(String sql);
	public int getCount(String sql ,int count ,int number);
	public boolean isExits(String sql);
}
