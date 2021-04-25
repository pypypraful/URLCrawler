package com.vipra.Demo;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface Crawler {

    void crawl(final String url) throws ExecutionException, InterruptedException;
}
