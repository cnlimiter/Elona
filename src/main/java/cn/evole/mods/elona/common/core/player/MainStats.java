package cn.evole.mods.elona.common.core.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.network.FriendlyByteBuf;

import java.io.Serial;
import java.io.Serializable;

/**
 * Name: Elona-forged / MainStats
 * Author: cnlimiter
 * CreateTime: 2023/9/8 0:45
 * Description:
 */
@Data
@AllArgsConstructor
public class MainStats implements Serializable {
    @Serial
    private static final long serialVersionUID = 7459301028287297083L;
    //年龄
    private int age;
    //身高
    private int height;
    //体重
    private int weight;



    //生命
    private int life;
    //法力
    private int mana;
    //上次保存的法力
    private int mpLast;
    //最大法力
    private int mp;
    //上次保存的经验
    private int xpLast;
    //最大经验
    private int xp;

    public MainStats(){
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.life = 100;
        this.mana = 100;
        this.mpLast = 0;
        this.mp = 0;
        this.xpLast = 0;
        this.xp = 0;
    }

    public static MainStats readBuf(FriendlyByteBuf buf){
        return new MainStats(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(),
                buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(),
                buf.readInt()
        );
    }

    public void writeBuff(FriendlyByteBuf buf){
        buf.writeInt(this.age);
        buf.writeInt(this.height);
        buf.writeInt(this.weight);
        buf.writeInt(this.life);
        buf.writeInt(this.mana);
        buf.writeInt(this.mpLast);
        buf.writeInt(this.mp);
        buf.writeInt(this.xpLast);
        buf.writeInt(this.xp);
    }
}
