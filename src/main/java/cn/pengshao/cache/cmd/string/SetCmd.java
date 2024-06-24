package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Set command
 *
 * @Author: yezp
 * @date 2024/6/24 22:21
 */
@Component
public class SetCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String val = getVal(args);
        cache.set(key, val);
        return Reply.string(OK);
    }

    @Override
    public String name() {
        return "SET";
    }
}
