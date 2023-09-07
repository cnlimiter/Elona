package cn.evole.mods.elona.util;

/**
 * Name: Elona-forged / FourmUtil
 * Author: cnlimiter
 * CreateTime: 2023/9/7 12:29
 * Description:
 */

public class FormulaUtil {
    public static int cacMaxHp(int constitution, int strength, int will, int life, int lv){
        return life / 100 * (lvHoSei(lv) / 9 * (3 * constitution + strength + will) / 3 + constitution) + 5;
    }

    public static int cacMaxMp(int mana, int magic, int lv, int will, int learning){
        return mana / 100 * (lvHoSei(lv) / 9 * (2 * magic + will + learning) / 3 + magic) ;
    }

    public static int cacMaxSp(int will, int constitution, int featStamina){
        return 100 + (will + constitution) / 5 + featStamina * 8;
    }

    public static int lvHoSei(int lv){
       return limit(lv / 4 + limit(lv / 4, 1, 250) + limit(lv / 4, 1, 500) + limit(lv / 4, 1, 750), 1, 4000);
    }

    public static int limit(int value, int min , int max){
        if (value >= min && value <= max){
            return value;
        }
        else if (value < min){
            return min;
        }
        else {
            return max;
        }
    }
}
