package GAME_INTERFACE;

import java.util.HashMap;

import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;

public interface IWriteCommand {
	public void setObject(DBBase obj);
	public void setObject(GameObject gameObj);
	public boolean executeAdd();
	public boolean executeUpdate();
	public boolean executeUpdate(HashMap<String ,Object> fields);
	public boolean executeDelete();
}
