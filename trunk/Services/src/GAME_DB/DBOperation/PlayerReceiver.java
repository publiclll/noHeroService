package GAME_DB.DBOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DBKit.DBCPPoolManage;
import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBPlayer;

/**
 * 数据库玩家表操作命令类
 * 
 * @author publiclll
 *
 */
public final class PlayerReceiver extends DataReceiver {

	private DBPlayer obj;
	private String tableName = "game_Player";

	public PlayerReceiver(DBBase obj) {
		this.obj = (DBPlayer) obj;
	}

	@Override
	public boolean write() {
		boolean out = false;

		if (this.obj != null) {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO `");
			sql.append(tableName);
			sql.append("` (ID ,ConfigID ,`Level` ,LevelLimit, Exp, ExpLimit ,HeadSerialNum ,HeadSerialBoxNum,"
					+ "Energy ,EnergyLimit ,Coin, Diamond ,SkillPoint ,VipLevel ,"
					+ "NickName ,AccountID ,CalculateTS ,LastLoginTS ,LastLogoutTS ,LastLoginIP ,feats ,"
					+ "ForcesId, SkillPointTS)");
			sql.append(" values ");
			sql.append(" ('");
			sql.append(this.obj.getID());
			sql.append("',");
			sql.append(this.obj.getConfigID());
			sql.append(",");
			sql.append(this.obj.getLevel());
			sql.append(",");
			sql.append(this.obj.getLevelLimit());
			sql.append(",");
			sql.append(this.obj.getExp());
			sql.append(",");
			sql.append(this.obj.getExpLimit());
			sql.append(",");
			sql.append(this.obj.getHeadSerialNum());
			sql.append(",");
			sql.append(this.obj.getHeadSerialBoxNum());
			sql.append(",");
			sql.append(this.obj.getEnergy());
			sql.append(",");
			sql.append(this.obj.getEnergyLimit());
			sql.append(",");
			sql.append(this.obj.getCoin());
			sql.append(",");
			sql.append(this.obj.getDiamond());
			sql.append(",");
			sql.append(this.obj.getSkillPoint());
			sql.append(",");
			sql.append(this.obj.getVipLevel());
			sql.append(",'");
			sql.append(this.obj.getNickName());
			sql.append("','");
			sql.append(this.obj.getAccountId());
			sql.append("','");
			sql.append(this.obj.getCalculateTS());
			sql.append("','");
			sql.append(this.obj.getLastLoginTS());
			sql.append("','");
			sql.append(this.obj.getLastLogoutTS());
			sql.append("','");
			sql.append(this.obj.getLastLoginIP());
			sql.append("',");
			sql.append(this.obj.getFeats());
			sql.append(",");
			sql.append(this.obj.getForcesId());
			sql.append(",'");
			sql.append(this.obj.getSkillPointTS());
			sql.append("')");

			// System.out.println(sql.toString());

			Connection pc = null;
			Statement stmt = null;
			try {
				pc = DBCPPoolManage.getInstance().getMasterConnection();
				stmt = pc.createStatement();
				stmt.executeUpdate(sql.toString());
				pc.close();
				out = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					pc.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return out;
	}

	@Override
	public boolean update() {
		boolean out = false;

		if (this.obj != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("Update `");
			sql.append(tableName + "`");
			sql.append(" set ");
			sql.append("Level=");
			sql.append(this.obj.getLevel());
			sql.append(", LevelLimit=");
			sql.append(this.obj.getLevelLimit());
			sql.append(", Exp=");
			sql.append(this.obj.getExp());
			sql.append(", ExpLimit=");
			sql.append(this.obj.getExpLimit());
			sql.append(", HeadSerialNum=");
			sql.append(this.obj.getHeadSerialNum());
			sql.append(", HeadSerialBoxNum=");
			sql.append(this.obj.getHeadSerialBoxNum());
			sql.append(", Energy=");
			sql.append(this.obj.getEnergy());
			sql.append(", EnergyLimit=");
			sql.append(this.obj.getEnergyLimit());
			sql.append(", Feats=");
			sql.append(this.obj.getFeats());
			sql.append(", Coin=");
			sql.append(this.obj.getCoin());
			sql.append(", Diamond=");
			sql.append(this.obj.getDiamond());
			sql.append(", SkillPoint=");
			sql.append(this.obj.getSkillPoint());
			sql.append(", LastLoginTS='");
			sql.append(this.obj.getLastLoginTS());
			sql.append("', LastLogoutTS='");
			sql.append(this.obj.getLastLogoutTS());
			sql.append("', SkillPointTS='");
			sql.append(this.obj.getSkillPointTS());
			sql.append("', CalculateTS='");
			sql.append(this.obj.getCalculateTS());
			sql.append("', ForcesId=");
			sql.append(this.obj.getForcesId());
			sql.append(", feats=");
			sql.append(this.obj.getFeats());
			sql.append(", VipLevel=");
			sql.append(this.obj.getVipLevel());
			sql.append(" where ID = '");
			sql.append(this.obj.getID());
			sql.append("'");
			// System.out.println("sql : " + sql.toString());
			Connection pc = null;
			Statement stmt = null;
			try {
				pc = DBCPPoolManage.getInstance().getMasterConnection();
				stmt = pc.createStatement();
				stmt.executeUpdate(sql.toString());
				pc.close();
				out = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					pc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return out;
	}

	@Override
	public ArrayList<DBBase> list(String sql, int count, int number) {
		StringBuilder exeSql = new StringBuilder();
		exeSql.append("Select * From `");
		exeSql.append(tableName + "`");
		if (!sql.equals("")) {
			exeSql.append(" where ");
			exeSql.append(sql);
		}
		exeSql.append(" order by ID desc ");
		exeSql.append(" limit ");
		exeSql.append(count);
		exeSql.append(",");
		exeSql.append(number);

		ArrayList<DBBase> list = new ArrayList<DBBase>();
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		Connection pc = null;
		try {
			pc = DBCPPoolManage.getInstance().getSlaveConnection();
			stmt = pc.createStatement();
			rs = stmt.executeQuery(exeSql.toString());
			while (rs != null && rs.next()) {
				list.add(getModel(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				pc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<DBBase> list(String sql) {
		StringBuilder exeSql = new StringBuilder();
		exeSql.append("Select * From `");
		exeSql.append(tableName + "`");
		if (!sql.equals("")) {
			exeSql.append(" where ");
			exeSql.append(sql);
		}
		exeSql.append(" order by ID desc ");

		ArrayList<DBBase> list = new ArrayList<DBBase>();
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		Connection pc = null;
		try {
			pc = DBCPPoolManage.getInstance().getSlaveConnection();
			stmt = pc.createStatement();
			rs = stmt.executeQuery(exeSql.toString());
			while (rs != null && rs.next()) {
				list.add(getModel(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				pc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public DBBase getModel(String sql) {
		// TODO Auto-generated method stub
		StringBuilder exeSql = new StringBuilder();
		DBPlayer player = null;
		exeSql.append("Select * From `");
		exeSql.append(tableName + "`");
		if (!sql.equals("")) {
			exeSql.append(" where ");
			exeSql.append(sql);
		}
		exeSql.append(" order by ID desc ");
		exeSql.append(" limit ");
		exeSql.append(0);
		exeSql.append(",");
		exeSql.append(1);

		java.sql.Statement stmt = null;
		ResultSet rs = null;
		Connection pc = null;
		try {
			pc = DBCPPoolManage.getInstance().getSlaveConnection();
			stmt = pc.createStatement();
			rs = stmt.executeQuery(exeSql.toString());
			while (rs != null && rs.next()) {
				player = getModel(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				pc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return player;
	}

	@Override
	public void set(DBBase obj) {
		// TODO Auto-generated method stub
		this.obj = (DBPlayer) obj;
	}

	@Override
	public int getCount(String sql) {
		// TODO Auto-generated method stub
		int Counts = 0;

		StringBuilder exeSql = new StringBuilder();
		exeSql.append("Select Count(ID) From `");
		exeSql.append(tableName + "`");
		if (!sql.equals("")) {
			exeSql.append(" where ");
			exeSql.append(sql);
		}

		java.sql.Statement stmt = null;
		ResultSet rs = null;
		Connection pc = null;
		try {
			pc = DBCPPoolManage.getInstance().getSlaveConnection();
			stmt = pc.createStatement();
			rs = stmt.executeQuery(exeSql.toString());

			while (rs != null && rs.next()) {
				Counts = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				pc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return Counts;
	}

	@Override
	public int getCount(String sql, int count, int number) {
		int Counts = 0;

		StringBuilder exeSql = new StringBuilder();
		exeSql.append("Select ID From `");
		exeSql.append(tableName + "`");
		if (!sql.equals("")) {
			exeSql.append(" where ");
			exeSql.append(sql);
		}
		exeSql.append(" order by ID desc ");
		exeSql.append(" limit ");
		exeSql.append(count);
		exeSql.append(",");
		exeSql.append(number);

		java.sql.Statement stmt = null;
		ResultSet rs = null;
		Connection pc = null;
		try {
			pc = DBCPPoolManage.getInstance().getSlaveConnection();
			stmt = pc.createStatement();
			rs = stmt.executeQuery(exeSql.toString());

			while (rs != null && rs.next()) {
				Counts = rs.getInt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				pc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return Counts;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeDB() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isExits(String sql) {
		return getCount(sql) == 0 ? false : true;
	}

	/**
	 * 内部通用方法，生成 数据库结构
	 * 
	 * @param rs
	 * @return
	 */
	private DBPlayer getModel(ResultSet rs) {
		DBPlayer player = null;
		try {
			player = new DBPlayer();
			player.setID(rs.getString("ID"));
			player.setConfigID(rs.getInt("ConfigID"));
			player.setLevel(rs.getInt("Level"));
			player.setLevelLimit(rs.getInt("LevelLimit"));
			player.setExp(rs.getInt("Exp"));
			player.setExpLimit(rs.getInt("ExpLimit"));
			player.setHeadSerialNum(rs.getInt("HeadSerialNum"));
			player.setHeadSerialBoxNum(rs.getInt("HeadSerialBoxNum"));
			player.setEnergy(rs.getInt("Energy"));
			player.setEnergyLimit(rs.getInt("EnergyLimit"));
			player.setCoin(rs.getInt("Coin"));
			player.setDiamond(rs.getInt("Diamond"));
			player.setSkillPoint(rs.getInt("SkillPoint"));
			player.setVipLevel(rs.getInt("VipLevel"));

			player.setNickName(rs.getString("NickName"));
			player.setAccountId(rs.getString("AccountID"));
			player.setCalculateTS(rs.getTimestamp("CalculateTS"));
			player.setLastLoginTS(rs.getTimestamp("LastLoginTS"));
			player.setLastLogoutTS(rs.getTimestamp("LastLogoutTS"));
			player.setLastLoginIP(rs.getString("LastLoginIP"));
			player.setSkillPointTS(rs.getTimestamp("SkillPointTS"));
			player.setFeats(rs.getInt("Feats"));
			player.setForcesId(rs.getInt("ForcesId"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public void set(GameObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<GameObject> listGameObj(String sql, int count, int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameObject> listGameObj(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameObject getModelGameObj(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(HashMap<String, Object> fields) {
		boolean out = false;
		if (fields.size() != 0 && this.obj != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("Update `");
			sql.append(tableName + "`");
			sql.append(" set ");
			StringBuilder fieldsSb = new StringBuilder();
			for (String key : fields.keySet()) {
				fieldsSb.append(key);
				fieldsSb.append("=");
				fieldsSb.append(fields.get(key));
				fieldsSb.append(",");
			}
			String sqlParameter = fieldsSb.toString();
			sql.append(sqlParameter.substring(0,sqlParameter.length() - 2));
			sql.append(" where ID = '");
			sql.append(this.obj.getID());
			sql.append("'");
			
			Connection pc = null;
			Statement stmt = null;
			try {
				pc = DBCPPoolManage.getInstance().getMasterConnection();
				stmt = pc.createStatement();
				stmt.executeUpdate(sql.toString());
				pc.close();
				out = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					pc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return out;
	}
}
