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

    public Integer exists(String[] keys) {
        return keys == null ? 0 : (int) Arrays.stream(keys)
                .filter(map::containsKey).count();
    }

    public Integer incr(String key) {
        String val = get(key);
        int num = 0;
        if (val != null) {
            num = Integer.parseInt(val);
        }
        num++;
        set(key, String.valueOf(num));
        return num;
    }

    public Integer decr(String key) {
        String val = get(key);
        int num = 0;
        if (val != null) {
            num = Integer.parseInt(val);
        }
        num--;
        set(key, String.valueOf(num));
        return num;
    }

    public void mset(String[] keys, String[] vals) {
        if (keys == null || keys.length == 0) {
            return;
        }
        if (vals == null || vals.length == 0) {
            return;
        }

        int minLength = Math.min(keys.length, vals.length);
        for (int i = 0; i < minLength; i++) {
            set(keys[i], vals[i]);
        }
    }

    public String[] mget(String[] keys) {
        return keys == null ? new String[0] : Arrays.stream(keys)
                .map(this::get).toArray(String[]::new);
    }
}
