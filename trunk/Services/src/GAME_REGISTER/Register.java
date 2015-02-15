package GAME_REGISTER;

import java.sql.Timestamp;
import java.util.Calendar;

import GAME_ADAPTER.DataAdapter;
import GAME_COMPONENTS.Player;
import GAME_DIRECTOR.Director;
import GAME_ENUM.GameDataType;
import GAME_ENUM.GameObjectType;

/**
 * ��¼�� ���� ��Ҫ�࣬ÿ���û���¼��ע�ᣬ����Director�࣬��ʹ�����
 * 
 * @author publiclll
 *
 */
public class Register {
	private static Register register = null;
	private DataAdapter adapter = null;

	private Register() {
		adapter = DataAdapter.getInstance();
	}

	public static Register getInstance() {
		if (register == null)
			register = new Register();
		return register;
	}
	
	/**
	 * ��֤��ɫ�Ƿ����
	 * @param accountId
	 * @return
	 */
	public boolean checkRole(String accountId)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("accountId='");
		sql.append(accountId);
		sql.append("'");
		
		boolean isExits = adapter.isExitsObject(sql.toString(), GameDataType.PLAYER);
		return isExits;
	}

	/**
	 * �û���¼
	 * 
	 * @param accountId
	 * @return
	 */
	private Director Login(String accountId) {
		StringBuilder sql = new StringBuilder();
		sql.append("accountId='");
		sql.append(accountId);
		sql.append("'");
		
		Player player = null;
		Director director = new Director();
		
		player = (Player)adapter.createObject(1, sql.toString(), GameDataType.PLAYER,
				GameObjectType.PLAYER);
		
		director.setPlayer(player);
		
		return director;
	}

	/**
	 * �����û�
	 * 
	 * @param accountId
	 * @param Sex
	 * @param NickName
	 * @return
	 */
	public Director CreatePlayer(String accountId, int Sex, String NickName) {

		StringBuilder sql = new StringBuilder();
		sql.append("accountId='");
		sql.append(accountId);
		sql.append("'");

		Player player = null;
		Director director = null;

		if (adapter.isExitsObject(sql.toString(), GameDataType.PLAYER)) {
			director = Login(accountId);
		} else {
			player = (Player)adapter.createObject(1, sql.toString(), GameDataType.PLAYER,
					GameObjectType.PLAYER);
			
			player.setSex(Sex);
			player.setNickName(NickName);
			player.setAccountId(accountId);
			
			Calendar now = Calendar.getInstance();
			Timestamp ts = new Timestamp(now.getTime().getTime());
			player.setCalculateTS(ts);
			
			Calendar now2 = Calendar.getInstance();
			Timestamp ts2 = new Timestamp(now2.getTime().getTime());
			player.setLastLoginTS(ts2);
			
			Calendar now3 = Calendar.getInstance();
			Timestamp ts3 = new Timestamp(now3.getTime().getTime());
			player.setLastLogoutTS(ts3);
			
			Calendar now4 = Calendar.getInstance();
			Timestamp ts4 = new Timestamp(now4.getTime().getTime());
			player.setSkillPointTS(ts4);
			
			player.setLastLoginIP("127.0.0.1");
			
			boolean isSave = adapter.doSaveObject(player);
			
			if(isSave)
			{
				director = new Director();
				director.setPlayer(player);
			}
		}
		
		return director;
	}
}
