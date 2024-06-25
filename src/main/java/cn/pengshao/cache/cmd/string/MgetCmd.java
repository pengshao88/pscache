package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Mget 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:05
 */
@Component
public class MgetCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String[] keys = getParams(args);
        return Reply.array(cache.mget(keys));
    }

    @Override
    public String name() {
        return "MGET";
    }
}
