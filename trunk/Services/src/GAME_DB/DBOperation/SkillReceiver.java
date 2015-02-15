package GAME_DB.DBOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DBKit.DBCPPoolManage;
import GAME_COMPONENTS.Equip;
import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.Skill;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBHeroEquip;
import GAME_DB.DBModel.DBSkill;

public class SkillReceiver extends DataReceiver {
	
	private Skill obj;
	private DBSkill dbObj;
	private String tableName = "game_Skill";

	public SkillReceiver() {
	}

	public SkillReceiver(DBBase obj) {
		this.dbObj = (DBSkill) obj;
	}

	public SkillReceiver(GameObject obj) {
		this.obj = (Skill) obj;
	}
	
	@Override
	public boolean write() {
		boolean out = false;

		if (this.dbObj != null) {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO `");
			sql.append(tableName);
			sql.append("` (ID ,ConfigID,HeroID,Level ,LevelLimit)");
			sql.append(" values ");
			sql.append(" ('");
			sql.append(this.dbObj.getID());
			sql.append("',");
			sql.append(this.dbObj.getConfigID());
			sql.append(",'");
			sql.append(this.dbObj.getHeroID());
			sql.append("','");
			sql.append(this.dbObj.getLevel());
			sql.append("','");
			sql.append(this.dbObj.getLevelLimit());
			sql.append("')");

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
			sql.append("Level=");
			sql.append(this.dbObj.getLevel());
			sql.append(" ,LevelLimit=");
			sql.append(this.dbObj.getLevelLimit());
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
	public boolean update(HashMap<String, Object> fields) {
		// TODO Auto-generated method stub
		return false;
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
		DBSkill skill = null;
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
				skill = getModel(rs);
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
		return skill;
	}

	@Override
	public GameObject getModelGameObj(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(DBBase obj) {
		this.dbObj = (DBSkill) obj;
	}

	@Override
	public void set(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount(String sql) {
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
		boolean out = false;

		if (this.obj != null) {
			StringBuilder sql = new StringBuilder();

			sql.append("DELETE FROM `");
			sql.append(tableName);
			sql.append("` WHERE ID='");
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
	private DBSkill getModel(ResultSet rs) {
		DBSkill skill = null;
		try {
			skill = new DBSkill();
			skill.setID(rs.getString("ID"));
			skill.setHeroID(rs.getString("HeroID"));
			skill.setConfigID(rs.getInt("ConfigID"));
			skill.setLevel(rs.getInt("Level"));
			skill.setLevelLimit(rs.getInt("LevelLimit"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skill;
	}
}
