package cn.pengshao.cache.cmd.common;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/18 21:43
 */
@Component
public class InfoCmd implements Cmd {
    static final String NAME = "INFO";
    static final String INFO = "PsCache Server[v1.0.0], created by pengshao." + CRLF
            + "Mock Redis Server, at 2024-06-15 in Shenzhen." + CRLF;

    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        return Reply.bulkString(INFO);
    }

    @Override
    public String name() {
        return NAME;
    }
}
