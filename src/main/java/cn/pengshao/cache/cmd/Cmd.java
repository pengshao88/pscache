package cn.pengshao.cache.cmd;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/18 21:32
 */
@Slf4j
public abstract class Cmd {

    protected static final String CRLF = "\r\n";
    protected static final String STR_PREFIX = "+";
    protected static final String BULK_PREFIX = "$";
    protected static final String OK =  "OK";

    public abstract void exec(ChannelHandlerContext ctx, String[] args);

    public abstract String getName();

    public void error(ChannelHandlerContext ctx, String msg) {
        writeByteBuf(ctx, errorEncode(msg));
    }

    protected static String errorEncode(String msg) {
        return "-" + msg + CRLF;
    }

    protected void bulkString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, bulkStringEncode(content));
    }

    private String bulkStringEncode(String content) {
        String ret;
        if (content == null) {
            ret = "$-1";
        } else if (content.isEmpty()) {
            ret = "$0";
        } else {
            ret = BULK_PREFIX + content.getBytes().length + CRLF + content;
        }
        return ret + CRLF;
    }

    protected void simpleString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, stringEncode(content));
    }

    private String stringEncode(String content) {
        String ret;
        if (content == null) {
            ret = "$-1";
        } else if (content.isEmpty()) {
            ret = "$0";
        } else {
            ret = STR_PREFIX + content;
        }
        return ret + CRLF;
    }

    protected void writeByteBuf(ChannelHandlerContext ctx, String content) {
        log.debug("wrap byte buffer and reply:{}", content);
        ByteBuf buffer = Unpooled.buffer(128);
        buffer.writeBytes(content.getBytes());
        ctx.writeAndFlush(buffer);
    }

}
