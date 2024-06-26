package cn.pengshao.cache.cmd.set;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * SISMEMBER 命令
 *
 * @Author: yezp
 * @date 2024/6/26 22:19
 */
@Component
public class SismemberCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String val = getVal(args);
        return Reply.integer(cache.sismember(key, val));
    }

    @Override
    public String name() {
        return "SISMEMBER";
    }
}
