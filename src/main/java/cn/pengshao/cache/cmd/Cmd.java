package cn.pengshao.cache.cmd;

import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/18 21:32
 */
public interface Cmd {

    String CRLF = "\r\n";

    String OK = "OK";

    Reply<?> exec(PsCache cache, String[] args);

    String name();

    default String getKey(String[] args) {
        return args[4];
    }

    default String getVal(String[] args) {
        return args[6];
    }

    default String[] getParams(String[] args) {
        int len = (args.length - 3) / 2;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[4 + i * 2];
        }
        return keys;
    }
}
