package cn.pengshao.cache.cmd.list;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * LLEN 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:26
 */
@Component
public class LlenCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.llen(key));
    }

    @Override
    public String name() {
        return "LLEN";
    }
}
