package cn.pengshao.cache.cmd.list;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * LRANGE 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:26
 */
@Component
public class LrangeCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] params = getParamsNoKey(args);
        int start = Integer.parseInt(params[0]);
        int end = Integer.parseInt(params[1]);
        return Reply.array(cache.lrange(key, start, end));
    }

    @Override
    public String name() {
        return "LRANGE";
    }
}
