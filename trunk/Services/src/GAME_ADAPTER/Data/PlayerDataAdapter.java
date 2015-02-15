package GAME_ADAPTER.Data;

import java.util.ArrayList;

import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBPlayer;
import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Player;
import GAME_ENUM.GameObjectType;
import GAME_SCHEDULING.ConfigScheduling;

/**
 * Player数据库适配器
 * 作用是转化游戏对象和游戏数据库对象
 * @author publiclll
 * 
 */
public class PlayerDataAdapter {
	public PlayerDataAdapter()
	{}
	
	public ArrayList<GameObject> convertArrayList(ArrayList<DBBase> objList)
	{
		return null;
	}
	
	public Player convertObject(int ConfigID ,DBBase obj)
	{
		DBPlayer dbPlayer = (DBPlayer)obj;
		Player player = ConfigScheduling.getInstance().getPlayer();
		
		if(dbPlayer != null)
		{
			player.setCfgData(false);
			player.setID(dbPlayer.getID());
			player.setAccountId(dbPlayer.getAccountId());
			player.setObjType(GameObjectType.PLAYER);
			player.setNickName(dbPlayer.getNickName());
			player.setCalculateTS(dbPlayer.getCalculateTS());
			player.setLastLoginIP(dbPlayer.getLastLoginIP());
			player.setLastLoginTS(dbPlayer.getLastLoginTS());
			player.setLastLogoutTS(dbPlayer.getLastLogoutTS());
			player.setCfgID(dbPlayer.getConfigID());
			player.setLevel(dbPlayer.getLevel());
			player.setLevelLimit(dbPlayer.getLevelLimit());
			player.setExp(dbPlayer.getExp());
			player.setExpLimit(dbPlayer.getExpLimit());
			player.setHeadSerialNum(dbPlayer.getHeadSerialNum());
			player.setHeadSerialBoxNum(dbPlayer.getHeadSerialNum());
			player.setEnergy(dbPlayer.getExp());
			player.setEnergyLimit(dbPlayer.getExp());
			player.setCoin(dbPlayer.getCoin());
			player.setDiamond(dbPlayer.getExp());
			player.setSkillPoint(dbPlayer.getExp());
			player.setVipLevel(dbPlayer.getVipLevel());
			player.setFeats(dbPlayer.getFeats());
			player.setForcesId(dbPlayer.getForcesId());
			player.setSkillPointTS(dbPlayer.getSkillPointTS());
		}
		
		return player;
	}
	
	public DBBase convertGameDbObject(GameObject obj)
	{
		DBPlayer dbPlayer = new DBPlayer();
		Player player = (Player)obj;
		
		if(player != null)
		{
			dbPlayer.setID(player.getID());
			dbPlayer.setAccountId(player.getAccountId());
			dbPlayer.setNickName(player.getNickName());
			dbPlayer.setCalculateTS(player.getCalculateTS());
			dbPlayer.setLastLoginIP(player.getLastLoginIP());
			dbPlayer.setLastLoginTS(player.getLastLoginTS());
			dbPlayer.setLastLogoutTS(player.getLastLogoutTS());
			dbPlayer.setConfigID(player.getCfgID());
			dbPlayer.setLevel(player.getLevel());
			dbPlayer.setLevelLimit(player.getLevelLimit());
			dbPlayer.setExp(player.getExp());
			dbPlayer.setExpLimit(player.getExpLimit());
			dbPlayer.setHeadSerialNum(player.getHeadSerialNum());
			dbPlayer.setHeadSerialBoxNum(player.getHeadSerialBoxNum());
			dbPlayer.setEnergy(player.getEnergy());
			dbPlayer.setEnergyLimit(player.getEnergyLimit());
			dbPlayer.setCoin(player.getCoin());
			dbPlayer.setDiamond(player.getDiamond());
			dbPlayer.setSkillPoint(player.getSkillPoint());
			dbPlayer.setVipLevel(player.getVipLevel());
			dbPlayer.setFeats(player.getFeats());
			dbPlayer.setForcesId(player.getForcesId());
			dbPlayer.setSkillPointTS(player.getSkillPointTS());
		}
		
		return dbPlayer;
	}
}
