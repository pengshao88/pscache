package cn.pengshao.cache.netty;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.cmd.CmdFactory;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
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
    private static final String STR_PREFIX = "+";
    private static final String BULK_PREFIX = "$";
    private static final String OK =  "OK";
    public static final PsCache cache = new PsCache();

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
        Reply<?> reply;
        if (command != null) {
            try {
                reply = command.exec(cache, args);
                log.debug("CMD[{}] => {} => {}", cmd, reply.getType(), reply.getValue());
            } catch (Exception exception) {
                log.error("CMD[{}] => exception:", cmd, exception);
                reply = Reply.error("EXP exception with msg: '" + exception.getMessage() + "'");
                replyContext(ctx, reply);
            }
        } else {
            reply = Reply.error("ERR unsupported command '" + cmd + "'");
        }
        replyContext(ctx, reply);
    }

    private void replyContext(ChannelHandlerContext ctx, Reply<?> reply) {
        switch (reply.getType()) {
            case INT :
                integer(ctx, (Integer) reply.getValue());
                break;
            case ERROR :
                error(ctx, (String) reply.getValue());
                break;
            case SIMPLE_STRING :
                simpleString(ctx, (String) reply.getValue());
                break;
            case BULK_STRING :
                bulkString(ctx, (String) reply.getValue());
                break;
            case ARRAY :
                array(ctx, (String[]) reply.getValue());
                break;
            default:
                simpleString(ctx, OK);
        }
    }

    private void simpleString(ChannelHandlerContext ctx, String value) {
        writeByteBuf(ctx, simpleStringEncode(value));
    }

    private String simpleStringEncode(String content) {
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

    private void error(ChannelHandlerContext ctx, String msg) {
        writeByteBuf(ctx, errorEncode(msg));
    }

    private String errorEncode(String msg) {
        return "-" + msg + CRLF;
    }

    private void integer(ChannelHandlerContext ctx, Integer value) {
        writeByteBuf(ctx, integerEncode(value));
    }

    private String integerEncode(Integer value) {
        return ":" + value + CRLF;
    }

    private void bulkString(ChannelHandlerContext ctx, String value) {
        writeByteBuf(ctx, bulkStringEncode(value));
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

    private void array(ChannelHandlerContext ctx, String[] array) {
        writeByteBuf(ctx, arrayEncode(array));
    }

    private String arrayEncode(Object[] array) {
        StringBuilder sb = new StringBuilder();
        if (array == null) {
            sb.append("*-1" + CRLF);
        } else if (array.length == 0) {
            sb.append("*0" + CRLF);
        } else {
            sb.append("*").append(array.length).append(CRLF);
            for (Object obj : array) {
                if (obj == null) {
                    sb.append("$-1" + CRLF);
                    continue;
                }

                if (obj instanceof String str) {
                    sb.append(bulkStringEncode(str));
                } else if (obj instanceof Integer i) {
                    sb.append(integerEncode(i));
                } else if (obj instanceof Object[] objects) {
                    sb.append(arrayEncode(objects));
                }
            }
        }
        return sb.toString();
    }

    private void writeByteBuf(ChannelHandlerContext ctx, String content) {
        log.debug("wrap byte buffer and reply:{}", content);
        ByteBuf byteBuf = Unpooled.buffer(128);
        byteBuf.writeBytes(content.getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}
