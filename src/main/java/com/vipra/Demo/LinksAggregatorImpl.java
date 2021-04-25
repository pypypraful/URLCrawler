package com.vipra.Demo;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LinksAggregatorImpl implements LinksAggregator{

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    @Override
    public Boolean isVisited(String url) {
        return map.containsKey(url);
    }

    @Override
    public void addLink(String url) {
        map.put(url, 0);
    }

    @Override
    public Set<String> getAllLinks() {
        return map.keySet();
    }
}
