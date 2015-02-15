package GAME_DB.DBModel;

/**
 * 英雄装备对象 ,只是个关联对象
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
