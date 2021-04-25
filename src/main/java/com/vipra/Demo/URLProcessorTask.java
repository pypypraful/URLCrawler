package com.vipra.Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class URLProcessorTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(URLProcessorTask.class);

    private final BlockingQueue<String> urlsToBeVisited;
    private final URLFetcher URLFetcher;
    private final LinksAggregator linksAggregator;
    private final String originalUrl;
    private AtomicInteger atomicInteger;

    URLProcessorTask(final BlockingQueue<String> queue, final URLFetcher fetcher,
                     final LinksAggregator aggregator, final String originalUrl,
                     AtomicInteger atomicInteger) {
        this.urlsToBeVisited = queue;
        this.URLFetcher = fetcher;
        this.linksAggregator = aggregator;
        this.originalUrl = originalUrl;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String url = urlsToBeVisited.poll(15, TimeUnit.SECONDS);
                if (url == null) {
                    logger.info("Atomic Integer Value: " + atomicInteger.get());
                    if (atomicInteger.get() == 0){
                        logger.error("No Producer in the Thread found");
                        break;
                    }
                    else continue;
                }
                atomicInteger.incrementAndGet();
                Set<String> extractedLinks = this.URLFetcher.fetchURL(url);
                extractedLinks.stream()
                        .filter(e -> Objects.nonNull(e))
                        .filter(link -> !this.linksAggregator.isVisited(link))
                        .forEach(link -> {
                            if (link.startsWith(originalUrl)) {
                                urlsToBeVisited.add(link);
                            }
                            linksAggregator.addLink(link);
                        });
                atomicInteger.decrementAndGet();
            } catch (Exception e) {
                logger.error("Error while processing the URl, skipping the URL", e);
            }
        }
    }
}
