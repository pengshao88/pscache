package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Decr cmd
 *
 * @Author: yezp
 * @date 2024/6/25 21:52
 */
@Component
public class DecrCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.decr(key));
    }

    @Override
    public String name() {
        return "DECR";
    }
}
