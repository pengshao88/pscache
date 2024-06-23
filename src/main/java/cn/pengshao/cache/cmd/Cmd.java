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

}
