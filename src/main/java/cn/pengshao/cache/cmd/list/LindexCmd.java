package cn.pengshao.cache.cmd.list;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * LINDEX 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:26
 */
@Component
public class LindexCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String val = getVal(args);
        return Reply.string(cache.lindex(key, Integer.parseInt(val)));
    }

    @Override
    public String name() {
        return "LINDEX";
    }
}
