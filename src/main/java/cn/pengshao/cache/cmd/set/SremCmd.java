package cn.pengshao.cache.cmd.set;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * SREM 命令
 *
 * @Author: yezp
 * @date 2024/6/26 22:19
 */
@Component
public class SremCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.srem(key, vals));
    }

    @Override
    public String name() {
        return "SREM";
    }
}
