package cn.pengshao.cache.cmd.zset;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * ZSCORE 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class ZscoreCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String member = getVal(args);
        Double zscore = cache.zscore(key, member);
        return Reply.string(zscore == null ? null : zscore.toString());
    }

    @Override
    public String name() {
        return "ZSCORE";
    }
}
