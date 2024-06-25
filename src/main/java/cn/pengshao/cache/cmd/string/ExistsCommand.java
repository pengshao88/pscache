package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Exists cmd
 *
 * @Author: yezp
 * @date 2024/6/25 21:39
 */
@Component
public class ExistsCommand implements Cmd {

    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String[] keys = getParams(args);
        return Reply.integer(cache.exists(keys));
    }

    @Override
    public String name() {
        return "EXISTS";
    }
}
