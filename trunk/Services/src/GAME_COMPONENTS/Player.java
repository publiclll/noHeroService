package GAME_COMPONENTS;

import java.sql.Timestamp;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameElementType;
import GAME_ENUM.GameObjectType;
import GAME_SCHEDULING.PlayerObjScheduling;

/**
 * 玩家类
 * 
 * @author publiclll
 *
 */
public class Player extends GameObject {
	private PlayerObjScheduling objCollection;
	/**
	 * 构造函数
	 */
	public Player() {
		//创建ID
		CreateID();
		//设置游戏对象类型
		setObjType(GameObjectType.PLAYER);
		//设置游戏数据库对象类型
		setObjDbType(GameDataType.PLAYER);
		
	}
	
	public void createObjCollection()
	{
		//游戏对象集合
		objCollection = new PlayerObjScheduling(getID());
	}
	/**
	 * 得到对象集合
	 * @return
	 */
	public PlayerObjScheduling getObjCollection()
	{
		return objCollection;
	}
	
	public void initObjCollection()
	{
		if(objCollection != null)
			objCollection.initObject();
	}

	@Override
	public String returnGameObjectID() {
		// TODO Auto-generated method stub
		return getID();
	}

	@Override
	public GameObjectType returnGameObjectType() {
		// TODO Auto-generated method stub
		return getObjType();
	}

	@Override
	public GameElementType returnGameElementType() {
		// TODO Auto-generated method stub
		return getElementType();
	}

	@Override
	public long currentExp() {
		// TODO Auto-generated method stub
		return getExp();
	}

	@Override
	public long currentExpLimit() {
		// TODO Auto-generated method stub
		return getExpLimit();
	}

	@Override
	public long nextExp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nextExpLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void levelUp_Complete(Object obj) {
		if((getLevel() + 1) > getLevelLimit())
		{
			System.out.println("玩家:" + getNickName() + "等级已达上限！");
		}else{
			setLevel(getLevel() + 1);
			setExpLimit((int)(getExpLimit() * 1.10));
			System.out.println("玩家:" + getNickName() + "升到" + getLevel() +"级！");
		}
	}
	
	/**
	 * 充值完成
	 */
	@Override
	public void charge_Complete(Object obj) {
		//看看充值金额，能变成VIP几的会员
		if(getDiamond() >= 1000)
			setVipLevel(1);
		if(getDiamond() >= 3000)
			setVipLevel(2);
		if(getDiamond() >= 8000)
			setVipLevel(3);
	}

	/**
	 * 克隆对象
	 */
	public Player clone() {
		Player p = null;
		try {
			p = (Player) super.clone();
			p.setID(getID());
			p.setObjType(getObjType());
			p.setElementType(getElementType());
			p.setCfgID(getCfgID());

			p.setSex(getSex());
			p.setNickName(getNickName());
			p.setAccountId(getAccountId());
			p.setCfgID(getCfgID());
			p.setLevel(getLevel());
			p.setLevelLimit(getLevelLimit());
			p.setExp(getExp());
			p.setExpLimit(getExpLimit());
			p.setCoin(getCoin());
			p.setFeats(getFeats());
			p.setDiamond(getDiamond());
			p.setHeadSerialNum(getHeadSerialNum());
			p.setHeadSerialBoxNum(getHeadSerialBoxNum());
			p.setForcesId(getForcesId());
			p.setVipLevel(getVipLevel());
			p.setEnergy(p.getEnergy());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return p;
	}

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

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
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

	public long getCoinLimit() {
		return coinLimit;
	}

	public void setCoinLimit(long coinLimit) {
		this.coinLimit = coinLimit;
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

	public int getSkillPoint() {
		return skillPoint;
	}

	public void setSkillPoint(int skillPoint) {
		this.skillPoint = skillPoint;
	}

	public int getEnergyLimit() {
		return energyLimit;
	}

	public void setEnergyLimit(int energyLimit) {
		this.energyLimit = energyLimit;
	}

	public Timestamp getSkillPointTS() {
		return SkillPointTS;
	}

	public void setSkillPointTS(Timestamp skillPointTS) {
		SkillPointTS = skillPointTS;
	}

	

	private int Sex;
	private String NickName;
	private String accountId;
	/**
	 * 精力
	 */
	private int energy;
	private int energyLimit;
	/**
	 * 头像编号
	 */
	private int headSerialNum;
	/**
	 * 头像框编号
	 */
	private int headSerialBoxNum;
	/**
	 * 金币
	 */
	private long coin;
	private long coinLimit;
	
	/**
	 * 充值币
	 */
	private long diamond;

	/**
	 * vip等级
	 */
	private int vipLevel;

	/**
	 * 科技点数
	 */
	private int skillPoint;
	
	/**
	 * 势力ID
	 */
	private int forcesId;
	
	/**
	 * 功勋
	 */
	private int feats;

	/**
	 * 推算时间戳 ， 用来增加精力
	 */
	private Timestamp calculateTS;
	/**
	 * 推算时间戳，用来增加技能点数
	 */
	private Timestamp SkillPointTS;
	/**
	 * 最后登录时间
	 */
	private Timestamp lastLoginTS;
	/**
	 * 最后登出时间
	 */
	private Timestamp LastLogoutTS;
	/**
	 * 最后登录IP
	 */
	private String LastLoginIP;
}
