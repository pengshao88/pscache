package cn.pengshao.cache.cmd;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * error
 *
 * @Author: yezp
 * @date 2024/6/18 21:57
 */
@Component
public class ErrorCmd extends Cmd {
    static final String NAME = "ERROR";
    @Override
    public void exec(ChannelHandlerContext ctx, String[] args) {

    }

    @Override
    public String getName() {
        return NAME;
    }
}
