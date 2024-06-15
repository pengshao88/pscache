package cn.pengshao.cache.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
    public static final String STR_PREFIX = "+";
    public static final String OK = "OK";
    public static final String INFO = "PsCache Server[v1.0.0], created by pengshao." + CRLF
                                    + "Mock Redis Server, at 2024-06-15 in Shenzhen." + CRLF;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message)
            throws Exception {
        String[] args = message.split(CRLF);
        log.debug("PsCacheHandler ===> {}", String.join(",", args));
        String cmd = args[2].toLowerCase();

        if ("COMMAND".equals(cmd)) {
            // 返回数组 *2 两个命令 $7 字符串长度
            writeByteBuf(ctx, "*2"
                    + CRLF + "$7"
                    + CRLF + "COMMAND"
                    + CRLF + "$4"
                    + CRLF + "PING"
                    + CRLF);
        } else if ("PING".equals(cmd)) {
            String ret = "PONG";
            if (args.length >= 5) {
                ret = args[4];
            }
            simpleString(ctx, ret);
        } else if ("INFO".equals(cmd)) {
            bulkString(ctx, INFO);
        } else {
            simpleString(ctx, OK);
        }
    }

    private void bulkString(ChannelHandlerContext ctx, String content) {
        // $长度\r\n内容\r\n
        writeByteBuf(ctx, "$" + content.getBytes().length + CRLF + content + CRLF);
    }

    /**
     * 返回简单字符串
     *
     * @param ctx ctx
     * @param content 字符串
     */
    private void simpleString(ChannelHandlerContext ctx, String content) {
        // +内容\r\n
        writeByteBuf(ctx, STR_PREFIX + content + CRLF);
    }

    private void writeByteBuf(ChannelHandlerContext ctx, String content) {
        log.debug("wrap byte buffer and reply:{}", content);
        ByteBuf buffer = Unpooled.buffer(128);
        buffer.writeBytes(content.getBytes());
        ctx.writeAndFlush(buffer);
    }
}
