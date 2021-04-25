package com.vipra.Demo;

import java.util.Set;

public interface LinksAggregator {

    Boolean isVisited(final String url);
    void addLink(final String url);
    Set<String> getAllLinks();
}
