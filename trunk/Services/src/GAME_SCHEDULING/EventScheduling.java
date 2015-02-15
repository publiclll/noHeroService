package GAME_SCHEDULING;

import java.util.HashMap;

import GAME_ENUM.GameNotifyType;
import GAME_INTERFACE.GAME_EVENT;

/**
 * 事件调度
 * @author publiclll
 *
 */
public class EventScheduling {
	private HashMap<String ,GAME_EVENT> objList;
	private static EventScheduling scheduling;
	public static EventScheduling getInstance()
	{
		if(scheduling == null)
			scheduling = new EventScheduling();
		return scheduling;
	}
	
	public void doNotify(GameNotifyType type ,String objID ,Object obj)
	{
		switch(type)
		{
		case LEVELUP_TYPE://升级
			objList.get(objID).levelUp_Complete(obj);
			break;
		case WORNEQUIP_TYPE:
			objList.get(objID).wornEquip_Complete(obj);
			break;
		case QUALITYUP_TYPE://升品质
			objList.get(objID).qualityUp_Complete(obj);
			break;
		case CHARGE_TYPE://充值
			objList.get(objID).charge_Complete(obj);
			break;
		case HEROUPPHASE_TYPE:
			objList.get(objID).phaseUp_Complete(obj);
			break;
		default:
			break;
		}
	}
	
	private EventScheduling()
	{
		objList = new HashMap<String, GAME_EVENT>();
	}
	
	public void addObj(GAME_EVENT obj)
	{
		objList.put(obj.returnGameObjectID(), obj);
	}
	
	public void removeObj(GAME_EVENT obj)
	{
		objList.remove(obj.returnGameObjectID(), obj);
	}
	
	public void removeAll()
	{
		objList.clear();
	}
}
