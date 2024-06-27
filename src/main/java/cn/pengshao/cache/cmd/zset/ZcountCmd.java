package cn.pengshao.cache.cmd.zset;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * ZCOUNT 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class ZcountCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] params = getParamsNoKey(args);
        double min = Double.parseDouble(params[0]);
        double max = Double.parseDouble(params[1]);
        return Reply.integer(cache.zcount(key, min, max));
    }

    @Override
    public String name() {
        return "ZCOUNT";
    }
}
