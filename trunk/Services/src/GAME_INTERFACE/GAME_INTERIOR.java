package GAME_INTERFACE;

/**
 * 内政接口
 * @author publiclll
 *
 */
public interface GAME_INTERIOR extends GAME_BASEINTERFACE {
	public void createComplete();
	public void upgradeComplete();
	public void removeComplete();
	public void cancelComplete();
	public void productionComplete(Object obj);
}
