package GAME_DB.DBModel;

/**
 * װ�����ݿ������
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
