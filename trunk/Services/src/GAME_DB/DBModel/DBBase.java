package GAME_DB.DBModel;

/**
 * 游戏数据结构基础类
 * @author publiclll
 *
 */
public class DBBase {
	private String ID;
	private int ConfigID;
	private String objName;
	private String PlayerID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getConfigID() {
		return ConfigID;
	}
	public void setConfigID(int configID) {
		ConfigID = configID;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(String playerID) {
		PlayerID = playerID;
	}
	
}
