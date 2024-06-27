package cn.pengshao.cache.cmd.zset;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ZADD 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class ZaddCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String[] scores = getHKeys(args);
        String[] vals = getHVals(args);
        return Reply.integer(cache.zadd(key, vals, toDouble(scores)));
    }

    double[] toDouble(String[] scores) {
        return Arrays.stream(scores).mapToDouble(Double::parseDouble).toArray();
    }

    @Override
    public String name() {
        return "ZADD";
    }
}
