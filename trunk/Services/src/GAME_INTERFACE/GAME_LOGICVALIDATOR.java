package GAME_INTERFACE;

import GAME_COMPONENTS.GameObject;

/**
 * 游戏逻辑验证接口
 * @author publiclll
 *
 */
public interface GAME_LOGICVALIDATOR {
	/**
	 * 返回当前的游戏对象
	 * @return
	 */
	public GameObject focusObject();
}
