package GAME_DB.DBModel;

/**
 * Ӣ��װ������ ,ֻ�Ǹ���������
 * @author publiclll
 *
 */
public class DBHeroEquip extends DBBase {
	private String EquipID;
	private String HeroID;
	public String getEquipID() {
		return EquipID;
	}
	public void setEquipID(String equipID) {
		EquipID = equipID;
	}
	public String getHeroID() {
		return HeroID;
	}
	public void setHeroID(String heroID) {
		HeroID = heroID;
	}
}
