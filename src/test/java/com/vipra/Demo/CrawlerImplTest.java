package com.vipra.Demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;

import static org.mockito.Mockito.when;

public class CrawlerImplTest {

    private Crawler crawler;
    final private String url = "http://dummy.com";

    @Mock
    private URLFetcher URLFetcher;

    @Mock
    private LinksAggregator linksAggregator;

    @BeforeEach
    public void setup() {
        System.out.println("Test Running");
        crawler = new CrawlerImpl(URLFetcher, linksAggregator);
    }

    @Test
    public void test_No_Link_Found() {
        when(URLFetcher.fetchURL(url)).thenReturn(new HashSet<>());
        Assertions.assertEquals(linksAggregator.getAllLinks().size(), 1);
    }

    @Test
    public void test_Link_Found() {
//        Document doc = Document.createShell("http://dummy.com");
//        doc.body().attr("href","http://childURL.com");
////        when(URLFetcher.fetchURL(any())).thenReturn(doc);
//        Assertions.assertEquals(linksAggregator.getAllLinks().size(), 2);
    }
}
