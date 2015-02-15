package GAME_DB.DBModel;

/**
 * 装备数据库操作类
 * @author publiclll
 *
 */
public class DBEquip extends DBBase {
	private String heroID;
	private int Count;
	public String getHeroID() {
		return heroID;
	}
	public void setHeroID(String heroID) {
		this.heroID = heroID;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
}
