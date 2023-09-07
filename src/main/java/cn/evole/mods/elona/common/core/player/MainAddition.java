package cn.evole.mods.elona.common.core.player;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Name: Elona-forged / MainAddition
 * Author: cnlimiter
 * CreateTime: 2023/9/8 0:49
 * Description:
 */
@Data
public class MainAddition implements Serializable {

    @Serial
    private static final long serialVersionUID = 5661800587008421663L;
    //性别
    private int gender;
    //职业
    private int role;
    //种族
    private int origin;
    //头衔
    private int title;
    //父亲
    private int father;
    //母亲
    private int mother;
    //信仰
    private int faith;
    //势力
    private int guild;
}
