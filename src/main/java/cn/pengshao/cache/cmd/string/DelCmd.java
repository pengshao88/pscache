package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/24 23:07
 */
@Component
public class DelCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String[] key = getParams(args);
        return Reply.integer(cache.del(key));
    }

    @Override
    public String name() {
        return "DEL";
    }
}
