package cn.evole.mods.elona.common.core.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.network.FriendlyByteBuf;

import java.io.Serial;
import java.io.Serializable;

/**
 * Name: Elona-forged / PlayerAttributes
 * Author: cnlimiter
 * CreateTime: 2023/9/6 19:55
 * Description:
 */
@Data
@AllArgsConstructor
public class PlayerAttributes implements Serializable {
    @Serial
    private static final long serialVersionUID = 2316145084813546665L;
    //力量
    private int strength;
    //体质
    private int constitution;
    //灵巧
    private int dexterity;
    //感知
    private int perception;
    //学习
    private int learning;
    //意志
    private int will;
    //魔力
    private int magic;
    //魅力
    private int charisma;
    //上次保存的法力
    private int mpLast;
    //最大法力
    private int mp;
    //上次保存的经验
    private int xpLast;
    //最大经验
    private int xp;

    public PlayerAttributes(){
        this.strength = 0;
        this.constitution = 0;
        this.dexterity = 0;
        this.perception = 0;
        this.learning = 0;
        this.will = 0;
        this.magic = 0;
        this.charisma = 0;
        this.mpLast = 0;
        this.mp = 0;
        this.xpLast = 0;
        this.xp = 0;
    }

    public static PlayerAttributes readBuf(FriendlyByteBuf buf){
        return new PlayerAttributes(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(),
                buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(),
                buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void writeBuff(FriendlyByteBuf buf){
        buf.writeInt(this.strength);
        buf.writeInt(this.constitution);
        buf.writeInt(this.dexterity);
        buf.writeInt(this.perception);
        buf.writeInt(this.learning);
        buf.writeInt(this.will);
        buf.writeInt(this.magic);
        buf.writeInt(this.charisma);
        buf.writeInt(this.mpLast);
        buf.writeInt(this.mp);
        buf.writeInt(this.xpLast);
        buf.writeInt(this.xp);
    }

}
