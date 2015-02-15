package GAME_INTERFACE;

import GAME_ENUM.GameDataType;
import GAME_ENUM.GameElementType;
import GAME_ENUM.GameObjectType;

/**
 * 基础接口
 * @author publiclll
 *
 */
public interface GAME_BASEINTERFACE {
	/**
	 * 返回对象ID
	 * @return
	 */
	public String returnGameObjectID();
	/**
	 * 返回游戏对象类型
	 * @return
	 */
	public GameObjectType returnGameObjectType();
	
	/**
	 * 返回游戏数据库类型
	 * @return
	 */
	public GameDataType returnGameDataType();
	
	/**
	 * 返回游戏元素类型
	 * @return
	 */
	public GameElementType returnGameElementType();
}
