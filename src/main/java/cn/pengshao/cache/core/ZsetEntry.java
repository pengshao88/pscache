package cn.pengshao.cache.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * zset entry
 *
 * @Author: yezp
 * @date 2024/6/27 21:52
 */
@Data
@AllArgsConstructor
public class ZsetEntry {

    private String value;
    private double score;

}
