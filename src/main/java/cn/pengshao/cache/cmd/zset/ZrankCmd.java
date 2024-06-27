package cn.pengshao.cache.cmd.zset;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * ZRANK 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class ZrankCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String val = getVal(args);
        Integer zrank = cache.zrank(key, val);
        return zrank == null ? Reply.string(null) : Reply.integer(zrank);
    }

    @Override
    public String name() {
        return "ZRANK";
    }
}
