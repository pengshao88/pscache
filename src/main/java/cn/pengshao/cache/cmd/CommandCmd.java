package cn.pengshao.cache.cmd;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * cmd : COMMAND
 *
 * @Author: yezp
 * @date 2024/6/18 21:34
 */
@Component
public class CommandCmd extends Cmd {
    public static final String NAME = "COMMAND";

    @Override
    public void exec(ChannelHandlerContext ctx, String[] args) {
        writeByteBuf(ctx, "*2"
                + CRLF + "$7"
                + CRLF + "COMMAND"
                + CRLF + "$4"
                + CRLF + "PING"
                + CRLF);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
