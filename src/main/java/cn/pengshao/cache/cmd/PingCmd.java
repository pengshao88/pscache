package cn.pengshao.cache.cmd;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/18 21:40
 */
@Component
public class PingCmd extends Cmd {
    public static final String NAME = "PING";

    @Override
    public void exec(ChannelHandlerContext ctx, String[] args) {
        String ret = "PONG";
        if(args.length >= 5) {
            ret = args[4];
        }
        simpleString(ctx, ret);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
