package cn.pengshao.cache.cmd.list;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * RPUSH 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:26
 */
@Component
public class RpushCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] params = getParamsNoKey(args);
        return Reply.integer(cache.rpush(key, params));
    }

    @Override
    public String name() {
        return "RPUSH";
    }
}
