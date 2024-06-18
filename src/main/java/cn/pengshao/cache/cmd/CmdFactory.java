package cn.pengshao.cache.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * cmd 工厂
 *
 * @Author: yezp
 * @date 2024/6/18 21:50
 */
@Component
public class CmdFactory {

    @Autowired
    List<Cmd> cmdList;

    public Cmd getCmd(String cmdName) {
        for (Cmd cmd : cmdList) {
            if (cmd.getName().equals(cmdName)) {
                return cmd;
            }
        }
        return null;
    }
}
