package TESTER;

import GAME_DIRECTOR.Director;
import GAME_REGISTER.Register;


public class Tester {

	public static void main(String[] args) {
		
		
		//System.out.println("是否存在：" + Register.getInstance().checkRole("20150210"));
		
		Director director = Register.getInstance().CreatePlayer("20150210", 1, "TTDDSS");
		director.getHero();
		//director.initEquip(1);
		//director.initEquip(2);
		//director.initEquip(3);
		//director.initEquip(4);
		//director.initEquip(5);
		//director.initEquip(6);
		//director.heroUpPhase(1);
		//director.wornEquip(1, 2);
		//director.wornEquip(1, 4);
		//director.wornEquip(1, 5);
		//director.wornEquip(1, 6);
		//director.wornEquip(1, 1);
		//director.wornEquip(1, 3);
		
		//director.showPlayerInfo();
		//director.heroUpPhase(1);
		//director.showPlayerInfo();
		//director.showPlayerInfo();
		/*
		HashMap<String ,String> hs = new HashMap<String, String>();
		hs.put("name", "abc");
		hs.put("sex", "1");
		for(String key : hs.keySet())
		{
			System.out.println( key +"=" + hs.get(key) );
		}*/
		/*
		Hero h = ConfigScheduling.getInstance().getHero(1);
		System.out.println(h.getName());
		for(Skill s : h.getSkillList().values())
		{
			System.out.println("  " + s.getName() + "(" + s.isUsed() + ")");
		}
		System.out.println("升到下一品质需要装备：");
		for(Equip e :h.getUpPhaseEquipList().values()){
			System.out.println("  " + e.getName());
		}
		System.out.println("升到下一品质需要金币：");
		System.out.println("  " + h.getUpPhaseLevelNeedCoint());
		*/
	}

}
