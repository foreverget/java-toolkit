package com.thankjava.toolkit3d.cache.ehcache;

import com.thankjava.toolkit.resource.SourceLoader;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheManager {

    /**
     * 配置文件路径
     */
    private String configUri = "ehcache.xml";

    /**
     * 配置文件默认位置是否来至于项目内部资源
     */
    private boolean isConfigFromSources = true;

    private static CacheManager cacheManager = null;

    public EhcacheManager() {
        init();
    }

    public EhcacheManager(String configUri, boolean isConfigFromSources) {
        this.configUri = configUri;
        this.isConfigFromSources = isConfigFromSources;
        init();
    }

    private void init() {
        if (isConfigFromSources) {
            cacheManager = CacheManager.create(SourceLoader.getResourceAsInputStream(configUri));
        } else {
            cacheManager = CacheManager.create(configUri);
        }
    }


    /**
     * 设置缓存
     * <p>Function: setCache</p>
     * <p>Description: </p>
     *
     * @param cacheName 缓存名 ehchace.xml里面配置的缓存策略
     * @param cacheKey  缓存key
     * @param object
     * @author acexy@thankjava.com
     * @date 2016年4月18日 下午5:41:37
     * @version 1.0
     */
    public void setCache(String cacheName, String cacheKey, Object object) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return;
        }
        cache.put(new Element(cacheKey, object));
    }

    public Object getCache(String cacheName, String cacheKey) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Element element = cache.get(cacheKey);
        if (element == null) {
            return null;
        }
        return element.getObjectValue();
    }

    public void remove(String cacheName, String cacheKey) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return;
        }

        cache.remove(cacheKey);
    }

    public void shutdown() {
        cacheManager.shutdown();
    }
}
