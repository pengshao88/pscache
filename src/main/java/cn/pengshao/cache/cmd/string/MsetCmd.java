package cn.pengshao.cache.cmd.string;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Mset 命令
 *
 * @Author: yezp
 * @date 2024/6/25 22:05
 */
@Component
public class MsetCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String[] keys = getKeys(args);
        String[] vals = getVals(args);
        cache.mset(keys, vals);
        return Reply.string(OK);
    }

    @Override
    public String name() {
        return "MSET";
    }
}
