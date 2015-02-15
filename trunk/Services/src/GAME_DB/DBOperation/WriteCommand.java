package GAME_DB.DBOperation;

import java.util.HashMap;

import GAME_COMPONENTS.GameObject;
import GAME_DB.DBModel.DBBase;
import GAME_ENUM.GameDataType;
import GAME_INTERFACE.IWriteCommand;

public class WriteCommand implements IWriteCommand {
	
	private DataReceiver receiver;
	public WriteCommand(GameDataType dbObjType ,DBBase obj)
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
			break;
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
	public void setObject(DBBase obj) {
		receiver.set(obj);
	}

	@Override
	public boolean executeAdd() {
		return receiver.write();
	}

	@Override
	public boolean executeUpdate() {
		return receiver.update();
	}

	@Override
	public boolean executeDelete() {
		return receiver.delete();
	}

	@Override
	public void setObject(GameObject gameObj) {
		receiver.set(gameObj);
	}

	@Override
	public boolean executeUpdate(HashMap<String, Object> fields) {
		// TODO Auto-generated method stub
		return receiver.update(fields);
	}

}
