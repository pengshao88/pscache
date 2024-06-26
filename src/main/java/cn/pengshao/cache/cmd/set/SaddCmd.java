package cn.pengshao.cache.cmd.set;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * SADD 命令
 *
 * @Author: yezp
 * @date 2024/6/26 22:19
 */
@Component
public class SaddCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.sadd(key, vals));
    }

    @Override
    public String name() {
        return "SADD";
    }
}
