package com.store.service.impl;

import com.store.cache.JedisUtil;
import com.store.service.CacheService;

import java.util.Set;

public class CacheServiceImpl implements CacheService {
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
        for (String key : keySet) {
            jedisKeys.del(key);
        }

    }
}
