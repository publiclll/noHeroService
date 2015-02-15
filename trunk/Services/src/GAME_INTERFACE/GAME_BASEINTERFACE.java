package GAME_INTERFACE;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameElementType;
import GAME_ENUM.GameObjectType;

/**
 * �����ӿ�
 * @author publiclll
 *
 */
public interface GAME_BASEINTERFACE {
	/**
	 * ���ض���ID
	 * @return
	 */
	public String returnGameObjectID();
	/**
	 * ������Ϸ��������
	 * @return
	 */
	public GameObjectType returnGameObjectType();
	
	/**
	 * ������Ϸ���ݿ�����
	 * @return
	 */
	public GameDataType returnGameDataType();
	
	/**
	 * ������ϷԪ������
	 * @return
	 */
	public GameElementType returnGameElementType();
}
