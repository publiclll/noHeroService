package GAME_ADAPTER;

import GAME_DB.GameProtocolModel.ProtocolBase;
import GAME_COMPONENTS.GameObject;

/**
 * Э���������������Ϸ�����Э�����Ļ���ת��
 * @author publiclll
 *
 */
public class ProtocolAdapter {
	private static ProtocolAdapter adapter = null;
	private ProtocolAdapter(){}
	
	public static ProtocolAdapter getInstance()
	{
		if(adapter == null)
			adapter = new ProtocolAdapter();
		return adapter;
	}
	
	public ProtocolBase createObject(GameObject obj){
		switch(obj.getObjType())
		{
		case PLAYER:
			break;
		case RESULT:
			break;
		default:
			break;
		}
		return new ProtocolBase();
	}
	
	public GameObject GameObject(ProtocolBase obj ,GameObject gameObj)
	{
		switch(gameObj.getObjType())
		{
		case PLAYER:
			break;
		case RESULT:
			break;
		default :
			break;
		}
		return new GameObject();
	}
}
