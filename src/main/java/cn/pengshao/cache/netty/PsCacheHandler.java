package cn.pengshao.cache.netty;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.cmd.CmdFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * ps cache handler.
 * in -> out -> handler -> client
 *
 * @Author: yezp
 * @date 2024/6/15 10:39
 */
@Slf4j
public class PsCacheHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 换行符
     */
    private static final String CRLF = "\r\n";
    private final CmdFactory cmdFactory;

    public PsCacheHandler(CmdFactory cmdFactory) {
        this.cmdFactory = cmdFactory;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message)
            throws Exception {
        String[] args = message.split(CRLF);
        log.debug("PsCacheHandler ===> {}", String.join(",", args));
        String cmd = args[2].toUpperCase();

        Cmd command = cmdFactory.getCmd(cmd);
        if (command == null) {
            command = cmdFactory.getCmd("ERROR");
            command.error(ctx, "command=" + cmd + " not found.");
            // simpleString(ctx, OK);
        } else {
            command.exec(ctx, args);
        }
    }
}
