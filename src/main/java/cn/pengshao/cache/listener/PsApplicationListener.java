package cn.pengshao.cache.listener;

import cn.pengshao.cache.plugin.PsPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * plugins entrypoint.
 *
 * @Author: yezp
 * @date 2024/6/15 10:25
 */
@Component
public class PsApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    List<PsPlugin> plugins;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            plugins.forEach(plugin -> {
                plugin.init();
                plugin.startup();
            });
        } else if (event instanceof ContextClosedEvent) {
            plugins.forEach(PsPlugin::shutdown);
        }
    }

}
