package cn.pengshao.cache.plugin;

/**
 * ps cache plugin.
 *
 * @Author: yezp
 * @date 2024/6/15 10:24
 */
public interface PsPlugin {

    void init();

    void startup();

    void shutdown();

}
