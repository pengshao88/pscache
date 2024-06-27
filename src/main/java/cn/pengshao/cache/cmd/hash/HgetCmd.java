package cn.pengshao.cache.cmd.hash;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * HGET 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class HgetCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String field = getVal(args);
        return Reply.string(cache.hget(key, field));
    }

    @Override
    public String name() {
        return "HGET";
    }
}
