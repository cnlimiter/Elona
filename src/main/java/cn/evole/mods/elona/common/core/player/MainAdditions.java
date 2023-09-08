package cn.evole.mods.elona.common.core.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.network.FriendlyByteBuf;

import java.io.Serial;
import java.io.Serializable;

/**
 * Name: Elona-forged / MainAddition
 * Author: cnlimiter
 * CreateTime: 2023/9/8 0:49
 * Description:
 */
@Data
@AllArgsConstructor
public class MainAdditions implements Serializable {

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

    public MainAdditions(){
        this.gender = 0;
        this.role = 0;
        this.origin = 0;
        this.title = 0;
        this.father = 0;
        this.mother = 0;
        this.faith = 0;
        this.guild = 0;
    }


    public static MainAdditions readBuf(FriendlyByteBuf buf){
        return new MainAdditions(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(),
                buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()
        );
    }

    public void writeBuff(FriendlyByteBuf buf){
        buf.writeInt(this.gender);
        buf.writeInt(this.role);
        buf.writeInt(this.origin);
        buf.writeInt(this.title);
        buf.writeInt(this.father);
        buf.writeInt(this.mother);
        buf.writeInt(this.faith);
        buf.writeInt(this.guild);
    }
}
