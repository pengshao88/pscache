package cn.pengshao.cache.cmd.hash;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * HEXISTS 命令
 *
 * @Author: yezp
 * @date 2024/6/26 23:03
 */
@Component
public class HexistsCmd implements Cmd {
    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String key = getKey(args);
        String hKey = getVal(args);
        return Reply.integer(cache.hexists(key, hKey));
    }

    @Override
    public String name() {
        return "HEXISTS";
    }
}
