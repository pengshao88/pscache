package cn.pengshao.cache.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cache entry
 *
 * @Author: yezp
 * @date 2024/6/24 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheEntry<T> {

    private T value;

}
