package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/24 22:30
 */
@Component
public class GetCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        return Reply.string(cache.get(key));
    }

    @Override
    public String name() {
        return "GET";
    }
}
