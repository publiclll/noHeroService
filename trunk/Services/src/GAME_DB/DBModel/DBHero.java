package GAME_DB.DBModel;

/**
 * 英雄数据库操作类
 * @author LLL
 *
 */
public class DBHero extends DBBase {
	private int Level;
	private int LevelLimit;
	private int StarLevel;
	private int StarLevelLimit;
	private int PhaseLevel;
	private int PhaseLevelLimit;
	private int Exp;
	private int ExpLimit;
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
	public int getStarLevel() {
		return StarLevel;
	}
	public void setStarLevel(int starLevel) {
		StarLevel = starLevel;
	}
	public int getStarLevelLimit() {
		return StarLevelLimit;
	}
	public void setStarLevelLimit(int starLevelLimit) {
		StarLevelLimit = starLevelLimit;
	}
	public int getPhaseLevel() {
		return PhaseLevel;
	}
	public void setPhaseLevel(int phaseLevel) {
		PhaseLevel = phaseLevel;
	}
	public int getPhaseLevelLimit() {
		return PhaseLevelLimit;
	}
	public void setPhaseLevelLimit(int phaseLevelLimit) {
		PhaseLevelLimit = phaseLevelLimit;
	}
	public int getExp() {
		return Exp;
	}
	public void setExp(int exp) {
		Exp = exp;
	}
	public int getExpLimit() {
		return ExpLimit;
	}
	public void setExpLimit(int expLimit) {
		ExpLimit = expLimit;
	}
}
