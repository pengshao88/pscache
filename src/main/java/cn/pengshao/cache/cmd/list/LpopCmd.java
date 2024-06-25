package cn.pengshao.cache.cmd.list;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * LPOP 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:26
 */
@Component
public class LpopCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        int count = Integer.parseInt(getVal(args));
        return Reply.array(cache.lpop(key, count));
    }

    @Override
    public String name() {
        return "LPOP";
    }
}
