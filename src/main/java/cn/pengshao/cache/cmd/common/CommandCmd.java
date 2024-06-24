package cn.pengshao.cache.cmd.common;

import cn.pengshao.cache.cmd.Cmd;
import cn.pengshao.cache.cmd.CmdFactory;
import cn.pengshao.cache.core.PsCache;
import cn.pengshao.cache.core.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * cmd : COMMAND
 *
 * @Author: yezp
 * @date 2024/6/18 21:34
 */
@Component
public class CommandCmd implements Cmd {
    public static final String NAME = "COMMAND";

    @Override
    public Reply<?> exec(PsCache cache, String[] args) {
        return Reply.array(CmdFactory.getCommandNames());
    }

    @Override
    public String name() {
        return NAME;
    }
}
