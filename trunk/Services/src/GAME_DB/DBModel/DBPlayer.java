package GAME_DB.DBModel;

import java.sql.Timestamp;

public class DBPlayer extends DBBase {
	private int Sex;
	private String NickName;
	private String accountId;
	/**
	 * ͷ����
	 */
	private int headSerialNum;
	private int headSerialBoxNum;
	/**
	 * ���
	 */
	private long coin;
	private long coinLimit;

	/**
	 * ��ֵ��
	 */
	private long diamond;

	/**
	 * vip�ȼ�
	 */
	private int vipLevel;
	
	private int level;
	private int levelLimit;
	private int exp;
	private int expLimit;
	
	private int energy;
	private int energyLimit;
	
	private int skillPoint;
	
	/**
	 * ����ID
	 */
	private int forcesId;
	/**
	 * ��ѫ
	 */
	private int feats;
	
	/**
	 * ����ʱ���
	 */
	private Timestamp calculateTS;
	private Timestamp skillPointTS;
	/**
	 * ����¼ʱ��
	 */
	private Timestamp lastLoginTS;
	/**
	 * ���ǳ�ʱ��
	 */
	private Timestamp LastLogoutTS;
	/**
	 * ����¼IP
	 */
	private String LastLoginIP;
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getHeadSerialNum() {
		return headSerialNum;
	}
	public void setHeadSerialNum(int headSerialNum) {
		this.headSerialNum = headSerialNum;
	}
	public long getCoin() {
		return coin;
	}
	public void setCoin(long coin) {
		this.coin = coin;
	}
	public long getCoinLimit() {
		return coinLimit;
	}
	public void setCoinLimit(long coinLimit) {
		this.coinLimit = coinLimit;
	}
	public int getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevelLimit() {
		return levelLimit;
	}
	public void setLevelLimit(int levelLimit) {
		this.levelLimit = levelLimit;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getExpLimit() {
		return expLimit;
	}
	public void setExpLimit(int expLimit) {
		this.expLimit = expLimit;
	}
	public int getForcesId() {
		return forcesId;
	}
	public void setForcesId(int forcesId) {
		this.forcesId = forcesId;
	}
	public int getFeats() {
		return feats;
	}
	public void setFeats(int feats) {
		this.feats = feats;
	}
	public Timestamp getCalculateTS() {
		return calculateTS;
	}
	public void setCalculateTS(Timestamp calculateTS) {
		this.calculateTS = calculateTS;
	}
	public Timestamp getLastLoginTS() {
		return lastLoginTS;
	}
	public void setLastLoginTS(Timestamp lastLoginTS) {
		this.lastLoginTS = lastLoginTS;
	}
	public Timestamp getLastLogoutTS() {
		return LastLogoutTS;
	}
	public void setLastLogoutTS(Timestamp lastLogoutTS) {
		LastLogoutTS = lastLogoutTS;
	}
	public String getLastLoginIP() {
		return LastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		LastLoginIP = lastLoginIP;
	}
	public long getDiamond() {
		return diamond;
	}
	public void setDiamond(long diamond) {
		this.diamond = diamond;
	}
	public int getHeadSerialBoxNum() {
		return headSerialBoxNum;
	}
	public void setHeadSerialBoxNum(int headSerialBoxNum) {
		this.headSerialBoxNum = headSerialBoxNum;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getEnergyLimit() {
		return energyLimit;
	}
	public void setEnergyLimit(int energyLimit) {
		this.energyLimit = energyLimit;
	}
	public int getSkillPoint() {
		return skillPoint;
	}
	public void setSkillPoint(int skillPoint) {
		this.skillPoint = skillPoint;
	}
	public Timestamp getSkillPointTS() {
		return skillPointTS;
	}
	public void setSkillPointTS(Timestamp skillPointTS) {
		this.skillPointTS = skillPointTS;
	}
}
