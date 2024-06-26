package cn.pengshao.cache.core;

import java.security.SecureRandom;
import java.util.*;

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

    // ===============  1. String start ===========
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

    // ===============  1. String end ===========

    // ===============  2. list start ===========

    public Integer lpush(String key, String[] vals) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if(entry == null) {
            entry = new CacheEntry<>(new LinkedList<>());
            this.map.put(key, entry);
        }

        LinkedList<String> exist = entry.getValue();
        Arrays.stream(vals).forEach(exist::addFirst);
        return vals.length;
    }

    public String[] lpop(String key, Integer count) {
        LinkedList<String> exist = getList(key);
        if (exist == null) {
            return null;
        }

        int minLength = Math.min(count, exist.size());
        String[] ret = new String[minLength];
        for (int i = 0; i < minLength; i++) {
            ret[i] = exist.removeFirst();
        }
        return ret;
    }

    private LinkedList<String> getList(String key) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if(entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public Integer rpush(String key, String[] vals) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if(entry == null) {
            entry = new CacheEntry<>(new LinkedList<>());
            this.map.put(key, entry);
        }

        LinkedList<String> exist = entry.getValue();
        Arrays.stream(vals).forEach(exist::addLast);
        return vals.length;
    }

    public String[] rpop(String key, Integer count) {
        LinkedList<String> exist = getList(key);
        if (exist == null) {
            return null;
        }

        int minLength = Math.min(count, exist.size());
        String[] ret = new String[minLength];
        for (int i = 0; i < minLength; i++) {
            ret[i] = exist.removeLast();
        }
        return ret;
    }

    public Integer llen(String key) {
        LinkedList<String> exist = getList(key);
        return exist == null ? 0 : exist.size();
    }

    public String lindex(String key, int index) {
        LinkedList<String> exist = getList(key);
        if (exist == null) {
            return null;
        }

        if (index >= exist.size()) {
            return null;
        }
        return exist.get(index);
    }

    public String[] lrange(String key, int start, int end) {
        LinkedList<String> exist = getList(key);
        if (exist == null) {
            return null;
        }
        int size = exist.size();
        if (start >= size || start > end) {
            return null;
        }

        if (end >= size) {
            end = size - 1;
        }
        int minLength = Math.min(size, end - start + 1);
        return Arrays.stream(exist.toArray(new String[0]))
                .skip(start)
                .limit(minLength)
                .toArray(String[]::new);
    }
    // ===============  2. list end ===========

    // ===============  3. set start ===========
    public Integer sadd(String key, String[] vals) {
        if (vals == null || vals.length == 0) {
            return 0;
        }

        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashSet<>());
            this.map.put(key, entry);
        }
        LinkedHashSet<String> exist = entry.getValue();
        return (int) Arrays.stream(vals).filter(exist::add).count();
    }

    public String[] smembers(String key) {
        LinkedHashSet<String> exist = getSet(key);
        if (exist == null) {
            return new String[0];
        }

        return exist.toArray(String[]::new);
    }

    private LinkedHashSet<String> getSet(String key) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public Integer scard(String key) {
        LinkedHashSet<String> exist = getSet(key);
        if (exist == null) {
            return 0;
        }

        return exist.size();
    }

    SecureRandom random = new SecureRandom();

    /**
     * 随机弹出
     *
     * @param key key
     * @param count 弹出个数
     * @return 弹出数组
     */
    public String[] spop(String key, int count) {
        LinkedHashSet<String> exist = getSet(key);
        if (exist == null) {
            return new String[0];
        }

        int minLength = Math.min(count, exist.size());
        String[] ret = new String[minLength];
        int index = 0;
        while (index < minLength) {
            int randomIndex = random.nextInt(exist.size());
            String[] array = exist.toArray(String[]::new);
            String obj = array[randomIndex];
            exist.remove(obj);
            ret[index++] = obj;
        }
        return ret;
    }

    public Integer srem(String key, String[] vals) {
        LinkedHashSet<String> exist = getSet(key);
        if (exist == null) {
            return 0;
        }

        return vals == null ? 0 : (int) Arrays.stream(vals)
                .filter(exist::remove).count();
    }

    public Integer sismember(String key, String val) {
        LinkedHashSet<String> exist = getSet(key);
        if (exist == null) {
            return 0;
        }

        return exist.contains(val) ? 1 : 0;
    }

    // ===============  3. set end ===========
}
