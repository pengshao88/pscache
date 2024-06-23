package cn.pengshao.cache.cmd;

import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author: yezp
 * @date 2024/6/18 21:40
 */
@Component
public class PingCmd implements Cmd {
    public static final String NAME = "PING";


    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        String ret = "PONG";
        if(args.length >= 5) {
            ret = args[4];
        }
        return Reply.string(ret);
    }

    @Override
    public String name() {
        return NAME;
    }
}
