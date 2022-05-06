package Bugs;

public class Ant extends HeroBug {
    private static final int MY_HEALTH = 1000;
    private static final int MY_DEFENCE = 30;
    private static final Attack MY_ATTACK = new Attack("bite","",40, 100);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite","",80, 20);
    private static final int MY_SPEED = 5;
    private static final int MY_CHANCE_TO_DODGE= 20;
    private static final boolean MY_RUN_AWAY = false;
    public Ant(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, MY_RUN_AWAY, theName);
    }


    //public static void main(String[] args) {
//        Ant test = new Ant("bob");
//
//        Ant test2 = new Ant("bobert");
//        System.out.println(test.getHealth());
//        System.out.println(test2.getHealth());
//        test2.attack(test);

    //}
}
