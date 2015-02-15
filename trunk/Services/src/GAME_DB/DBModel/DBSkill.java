package GAME_DB.DBModel;

/**
 * 英雄技能数据库操作类
 * @author publiclll
 *
 */
public class DBSkill extends DBBase {
	private String HeroID;
	private int Level;
	private int LevelLimit;
	public String getHeroID() {
		return HeroID;
	}
	public void setHeroID(String heroID) {
		HeroID = heroID;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	public int getLevelLimit() {
		return LevelLimit;
	}
	public void setLevelLimit(int levelLimit) {
		LevelLimit = levelLimit;
	}
}
