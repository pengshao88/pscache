package cn.pengshao.cache.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * cache entries.
 *
 * @Author: yezp
 * @date 2024/6/23 22:23
 */
public class PsCache {

    Map<String, CacheEntry<?>> map = new HashMap<>();

    public void set(String key, String value) {
        map.put(key, new CacheEntry<>(value));
    }

    public String get(String key) {
        CacheEntry<?> cacheEntry = map.get(key);
        if (cacheEntry != null) {
            return (String) cacheEntry.getValue();
        }
        return null;
    }

    public Integer strlen(String key) {
        return get(key) == null ? 0 : get(key).length();
    }

    public Integer del(String... keys) {
        return keys == null ? 0 : (int) Arrays.stream(keys)
                .map(map::remove).filter(Objects::nonNull).count();
    }
}
