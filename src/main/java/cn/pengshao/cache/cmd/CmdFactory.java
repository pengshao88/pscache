package cn.pengshao.cache.cmd;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    Map<String, Cmd> cmdMap;

    @PostConstruct
    public void init() {
        cmdMap = cmdList.stream().collect(Collectors.toMap(Cmd::name, cmd -> cmd));
    }

    public Cmd getCmd(String cmdName) {
        return cmdMap.get(cmdName);
    }

    public String[] getCommandNames() {
        return cmdMap.keySet().toArray(new String[0]);
    }
}
