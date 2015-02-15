package GAME_DB.DBOperation;

import java.util.ArrayList;

import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;
import GAME_ENUM.GameDataType;
import GAME_INTERFACE.IReadCommand;

public class ReadCommand implements IReadCommand {
	
	private DataReceiver receiver;
	public ReadCommand(GameDataType dbObjType ,DBBase obj)
	{
		switch(dbObjType)
		{
		case PLAYER:
			receiver = new PlayerReceiver(obj);
			break;
		case HERO:
			receiver = new HeroReceiver(obj);
			break;
		case NUMERICAL:
			receiver = new NumericalAttrReceiver();
		case EQUIP:
			receiver = new EquipReceiver(obj);
			break;
		case HEROEQUIP:
			receiver = new  HeroEquipReceiver(obj);
			break;
		case SKILL:
			receiver = new SkillReceiver(obj);
			break;
		default:
			break;
		}
	}

	@Override
	public ArrayList<DBBase> execute(String sql) {
		return receiver.list(sql);
	}

	@Override
	public DBBase executeModel(String sql) {
		return receiver.getModel(sql);
	}

	@Override
	public ArrayList<DBBase> execute(String sql, int count, int number) {
		return receiver.list(sql, count, number);
	}

	@Override
	public int getCount(String sql) {
		return receiver.getCount(sql);
	}

	@Override
	public int getCount(String sql, int count, int number) {
		return receiver.getCount(sql, count, number);
	}

	@Override
	public boolean isExits(String sql) {
		return receiver.isExits(sql);
	}

	@Override
	public ArrayList<GameObject> executeGameObj(String sql) {
		return receiver.listGameObj(sql);
	}

	@Override
	public GameObject executeModelGameObj(String sql) {
		return receiver.getModelGameObj(sql);
	}

	@Override
	public ArrayList<GameObject> executeGameObj(String sql, int count,
			int number) {
		return receiver.listGameObj(sql, count, number);
	}

}
