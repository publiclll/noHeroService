package GAME_SCHEDULING;

import java.util.HashMap;
import GAME_ENUM.GameNotifyType;
import GAME_INTERFACE.GAME_INTERIOR;

/**
 * 内政调度器
 * @author publiclll
 *
 */
public class InteriorScheduling {
	private HashMap<String ,GAME_INTERIOR> objList;
	private static InteriorScheduling scheduling;
	public static InteriorScheduling getInstance()
	{
		if(scheduling == null)
			scheduling = new InteriorScheduling();
		
		return scheduling;
	}
	private InteriorScheduling(){
		objList = new HashMap<String, GAME_INTERIOR>();
	}
	
	public void doNotify(GameNotifyType type ,String objID ,Object obj)
	{
		GAME_INTERIOR fObj = objList.get(objID);
		switch(type)
		{
		case PRODUCTION_TYPE:
			fObj.productionComplete(obj);
			break;
		case UPGREADE_TYPE:
			fObj.upgradeComplete();
			break;
		case REMOVE_TYPE:
			fObj.removeComplete();
			break;
		case BUILD_TYPE:
			fObj.createComplete();
			break;
		case CANNEL_TYPE:
			fObj.cancelComplete();
			break;
		default:
			break;
		}
	}
	
	public void addObj(GAME_INTERIOR obj)
	{
		objList.put(obj.returnGameObjectID(), obj);
	}
	
	public void removeObj(GAME_INTERIOR obj)
	{
		objList.remove(obj.returnGameObjectID(), obj);
	}
	
	public void removeAll()
	{
		objList.clear();
		
	}
}
