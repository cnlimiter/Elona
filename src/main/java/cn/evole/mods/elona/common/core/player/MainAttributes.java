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
public class MainAttributes implements Serializable {
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


    public MainAttributes(){
        this.strength = 100;
        this.constitution = 100;
        this.dexterity = 100;
        this.perception = 100;
        this.learning = 100;
        this.will = 100;
        this.magic = 100;
        this.charisma = 100;
    }

    public static MainAttributes readBuf(FriendlyByteBuf buf){
        return new MainAttributes(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(),
                buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()
                );
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
    }

}
