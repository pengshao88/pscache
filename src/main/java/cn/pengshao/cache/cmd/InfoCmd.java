package cn.pengshao.cache.cmd;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/18 21:43
 */
@Component
public class InfoCmd extends Cmd {
    static final String NAME = "INFO";
    static final String INFO = "PsCache Server[v1.0.0], created by pengshao." + CRLF
            + "Mock Redis Server, at 2024-06-15 in Shenzhen." + CRLF;
    @Override
    public void exec(ChannelHandlerContext ctx, String[] args) {
        bulkString(ctx, INFO);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
