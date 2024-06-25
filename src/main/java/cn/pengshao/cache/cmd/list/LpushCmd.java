package cn.pengshao.cache.cmd.list;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * LPUSH 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:26
 */
@Component
public class LpushCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] params = getParamsNoKey(args);
        return Reply.integer(cache.lpush(key, params));
    }

    @Override
    public String name() {
        return "LPUSH";
    }
}
