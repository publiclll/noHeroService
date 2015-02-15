package GAME_DB.DBOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DBKit.DBCPPoolManage;
import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Hero;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBHero;
import GAME_DB.DBModel.DBPlayer;


public class HeroReceiver extends DataReceiver {
	private Hero obj;
	private DBHero dbObj;
	private String tableName = "game_Hero";
	
	public HeroReceiver(){}
	
	public HeroReceiver(DBBase obj)
	{
		this.dbObj = (DBHero)obj;
	}
	
	public HeroReceiver(GameObject obj)
	{
		this.obj = (Hero)obj;
	}
	
	@Override
	public boolean write() {
		boolean out = false;

		if (this.dbObj != null) {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO `");
			sql.append(tableName);
			sql.append("` (ID ,ConfigID,PlayerID ,StarLevel ,StarLevelLimit ,PhaseLevel ,"
					+ " PhaseLeveLimit,Level ,LevelLimit,Exp ,ExpLimit)");
			sql.append(" values ");
			sql.append(" ('");
			sql.append(this.dbObj.getID());
			sql.append("',");
			sql.append(this.dbObj.getConfigID());
			sql.append(",'");
			sql.append(this.dbObj.getPlayerID());
			sql.append("',");
			sql.append(this.dbObj.getStarLevel());
			sql.append(",");
			sql.append(this.dbObj.getStarLevelLimit());
			sql.append(",");
			sql.append(this.dbObj.getPhaseLevel());
			sql.append(",");
			sql.append(this.dbObj.getPhaseLevelLimit());
			sql.append(",");
			sql.append(this.dbObj.getLevel());
			sql.append(",");
			sql.append(this.dbObj.getLevelLimit());
			sql.append(",");
			sql.append(this.dbObj.getExp());
			sql.append(",");
			sql.append(this.dbObj.getExpLimit());
			sql.append(")");

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

		if (this.dbObj != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("Update `");
			sql.append(tableName + "`");
			sql.append(" set ");
			sql.append("StarLevel=");
			sql.append(this.dbObj.getStarLevel());
			sql.append(", StarLevelLimit=");
			sql.append(this.dbObj.getStarLevelLimit());
			sql.append(", PhaseLevel=");
			sql.append(this.dbObj.getPhaseLevel());
			sql.append(", PhaseLeveLimit=");
			sql.append(this.dbObj.getPhaseLevelLimit());
			sql.append(", Level=");
			sql.append(this.dbObj.getLevel());
			sql.append(", LevelLimit=");
			sql.append(this.dbObj.getLevelLimit());
			sql.append(", Exp=");
			sql.append(this.dbObj.getExp());
			sql.append(", ExpLimit=");
			sql.append(this.dbObj.getExpLimit());
			sql.append(" where ID = '");
			sql.append(this.dbObj.getID());
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
	public ArrayList<GameObject> listGameObj(String sql, int count, int number) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<GameObject> listGameObj(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBBase getModel(String sql) {
		StringBuilder exeSql = new StringBuilder();
		DBHero hero = null;
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
				hero = getModel(rs);
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
		return hero;
	}

	@Override
	public GameObject getModelGameObj(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(DBBase obj) {
		// TODO Auto-generated method stub
		this.dbObj = (DBHero)obj;
	}

	@Override
	public void set(GameObject obj) {
		this.obj = (Hero)obj;
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
		// TODO Auto-generated method stub
		return getCount(sql) == 0 ? false : true;
	}

	@Override
	public boolean update(HashMap<String, Object> fields) {
		boolean out = false;
		if (fields.size() != 0 && this.dbObj != null) {
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
			sql.append(sqlParameter.substring(0,sqlParameter.length() - 1));
			sql.append(" where ID = '");
			sql.append(this.dbObj.getID());
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
	/**
	 * 内部通用方法，生成 数据库结构
	 * 
	 * @param rs
	 * @return
	 */
	private DBHero getModel(ResultSet rs) {
		DBHero hero = null;
		try {
			hero = new DBHero();
			hero.setID(rs.getString("ID"));
			hero.setPlayerID(rs.getString("PlayerID"));
			hero.setConfigID(rs.getInt("ConfigID"));
			hero.setLevel(rs.getInt("Level"));
			hero.setLevelLimit(rs.getInt("LevelLimit"));
			hero.setExp(rs.getInt("Exp"));
			hero.setExpLimit(rs.getInt("ExpLimit"));
			hero.setStarLevel(rs.getInt("StarLevel"));
			hero.setStarLevelLimit(rs.getInt("StarLevelLimit"));
			hero.setPhaseLevel(rs.getInt("PhaseLevel"));
			hero.setPhaseLevelLimit(rs.getInt("PhaseLevelimit"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hero;
	}
}
