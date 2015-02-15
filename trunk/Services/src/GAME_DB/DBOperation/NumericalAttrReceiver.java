package GAME_DB.DBOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DBKit.DBCPPoolManage;
import GAME_COMPONENTS.GameObject;
import GAME_COMPONENTS.NumericalAttribute;
import GAME_DB.DBModel.DBBase;
import GAME_DB.DBModel.DBNumericalAttribute;
import GAME_DB.DBModel.DBPlayer;
import GAME_ENUM.GameObjectType;

public class NumericalAttrReceiver extends DataReceiver {

	private NumericalAttribute obj;
	private DBNumericalAttribute dbObj;
	private String tableName = "game_Numericalattr";

	public NumericalAttrReceiver() {
	}

	public NumericalAttrReceiver(DBBase obj) {
		this.dbObj = (DBNumericalAttribute) obj;
	}

	public NumericalAttrReceiver(GameObject obj) {
		this.obj = (NumericalAttribute) obj;
	}

	@Override
	public boolean write() {
		boolean out = false;

		if (this.obj != null) {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO `");
			sql.append(tableName);
			sql.append("` (ID ,objId ,parentObjType ,power ,i_power ,"
					+ " intelligence,i_intelligence ,agile,i_agile ,p_attack ,p_defense ,m_attack,m_defense , "
					+ " p_crit ,r_hp ,r_mp ,hp)");
			sql.append(" values ");
			sql.append(" ('");
			sql.append(this.obj.getID());
			sql.append("','");
			sql.append(this.obj.getObjId());
			sql.append("',");
			switch (this.obj.getParentObjType()) {
			case HERO:
				sql.append(1);
				break;
			case EQUIP:
				sql.append(2);
				break;
			case SKILL:
				sql.append(3);
				break;
			default:
				break;
			}

			sql.append(",");
			sql.append(this.obj.getPower());
			sql.append(",");
			sql.append(this.obj.getI_power());
			sql.append(",");
			sql.append(this.obj.getIntelligence());
			sql.append(",");
			sql.append(this.obj.getI_intelligence());
			sql.append(",");
			sql.append(this.obj.getAgile());
			sql.append(",");
			sql.append(this.obj.getI_agile());
			sql.append(",");
			sql.append(this.obj.getP_attack());
			sql.append(",");
			sql.append(this.obj.getP_defense());
			sql.append(",");
			sql.append(this.obj.getM_attack());
			sql.append(",");
			sql.append(this.obj.getM_defense());
			sql.append(",");
			sql.append(this.obj.getP_crit());
			sql.append(",");
			sql.append(this.obj.getR_hp());
			sql.append(",");
			sql.append(this.obj.getR_mp());
			sql.append(",");
			sql.append(this.obj.getHp());
			sql.append(")");

			//System.out.println(sql.toString());

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
			sql.append("power=");
			sql.append(this.obj.getPower());
			sql.append(", i_power=");
			sql.append(this.obj.getI_power());
			sql.append(", intelligence=");
			sql.append(this.obj.getIntelligence());
			sql.append(", i_intelligence=");
			sql.append(this.obj.getI_intelligence());
			sql.append(", agile=");
			sql.append(this.obj.getAgile());
			sql.append(", i_agile=");
			sql.append(this.obj.getI_agile());
			sql.append(", p_attack=");
			sql.append(this.obj.getP_attack());
			sql.append(", p_defense=");
			sql.append(this.obj.getP_defense());
			sql.append(", m_attack=");
			sql.append(this.obj.getM_attack());
			sql.append(", m_defense=");
			sql.append(this.obj.getM_defense());
			sql.append(", p_crit=");
			sql.append(this.obj.getP_crit());
			sql.append(", r_hp=");
			sql.append(this.obj.getR_hp());
			sql.append(", r_mp=");
			sql.append(this.obj.getR_mp());
			sql.append(", hp=");
			sql.append(this.obj.getHp());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DBBase> list(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBBase getModel(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(DBBase obj) {
		// TODO Auto-generated method stub
		this.dbObj = (DBNumericalAttribute) obj;
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
	public void set(GameObject obj) {
		this.obj = (NumericalAttribute) obj;
	}

	@Override
	public ArrayList<GameObject> listGameObj(String sql, int count, int number) {
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

		ArrayList<GameObject> list = new ArrayList<GameObject>();
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
		StringBuilder exeSql = new StringBuilder();
		exeSql.append("Select * From `");
		exeSql.append(tableName + "`");
		if (!sql.equals("")) {
			exeSql.append(" where ");
			exeSql.append(sql);
		}
		exeSql.append(" order by ID desc ");

		ArrayList<GameObject> list = new ArrayList<GameObject>();
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
	public GameObject getModelGameObj(String sql) {
		StringBuilder exeSql = new StringBuilder();
		NumericalAttribute num = null;
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
				num = getModel(rs);
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
		return num;
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
			sql.append(fieldsSb.toString().substring(0,
					fieldsSb.toString().length() - 2));
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

	/**
	 * 内部通用方法，生成 数据库结构
	 * 
	 * @param rs
	 * @return
	 */
	private NumericalAttribute getModel(ResultSet rs) {
		NumericalAttribute num = null;
		try {
			num = new NumericalAttribute();
			num.setID(rs.getString("ID"));
			num.setObjId(rs.getString("objId"));
			num.setPower(rs.getDouble("power"));
			num.setI_power(rs.getDouble("i_power"));
			num.setIntelligence(rs.getDouble("intelligence"));
			num.setI_intelligence(rs.getDouble("i_intelligence"));
			num.setAgile(rs.getDouble("agile"));
			num.setI_agile(rs.getDouble("i_agile"));
			num.setP_attack(rs.getDouble("p_attack"));
			num.setP_defense(rs.getDouble("p_defense"));
			num.setM_attack(rs.getDouble("m_attack"));
			num.setM_defense(rs.getDouble("m_defense"));
			num.setP_crit(rs.getDouble("p_crit"));
			num.setR_hp(rs.getDouble("r_hp"));
			num.setR_mp(rs.getDouble("r_mp"));
			num.setHp(rs.getDouble("hp"));
			switch (rs.getInt("parentObjType")) {
			case 1:
				num.setParentObjType(GameObjectType.HERO);
				break;
			case 2:
				num.setParentObjType(GameObjectType.EQUIP);
				break;
			case 3:
				num.setParentObjType(GameObjectType.SKILL);
				break;
			case 4:
				// 道具
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
}
