package com.vipra.Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CrawlerImpl implements Crawler {

    private final URLFetcher URLFetcher;
    private final LinksAggregator linksAggregator;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final Logger logger = LogManager.getLogger(CrawlerImpl.class);

    public CrawlerImpl(URLFetcher URLFetcher, LinksAggregator linksAggregator) {
        this.URLFetcher = URLFetcher;
        this.linksAggregator = linksAggregator;
    }

    @Override
    public void crawl(final String url) throws ExecutionException, InterruptedException {
        BlockingQueue<String> urlsToBeVisited = new LinkedBlockingQueue<>();
        List<Future> futureList = new ArrayList<>();
        urlsToBeVisited.add(url);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            Future<?> submit = executorService.submit(new URLProcessorTask(urlsToBeVisited, URLFetcher,
                    linksAggregator, url, atomicInteger));
            futureList.add(submit);
        }
        for (int i = 0; i < futureList.size(); i++) {
            futureList.get(i).get();
        }
        executorService.shutdownNow();

        logger.info("Count: {}", linksAggregator.getAllLinks().size());
    }
}
