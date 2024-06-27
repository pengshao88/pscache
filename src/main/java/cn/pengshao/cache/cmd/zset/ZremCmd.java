package cn.pengshao.cache.cmd.zset;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * ZREM 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class ZremCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] members = getParamsNoKey(args);
        return Reply.integer(cache.zrem(key, members));
    }

    @Override
    public String name() {
        return "ZREM";
    }
}
