package com.vipra.Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@RestController
public class DemoApplication {

	private static final Logger logger = LogManager.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) throws ExecutionException, InterruptedException {
		logger.info("Running Now");
		String URLs = String.join("<br/>", performActivity());
		return String.format("Following are the links for %s found<br/> %s!", name, URLs);
	}

	public Set<String> performActivity() throws ExecutionException, InterruptedException {
		String url = "https://monzo.com";
		final LinksAggregator linksAggregator = new LinksAggregatorImpl();
		Crawler crawler = new CrawlerImpl(new URLFetcher(), linksAggregator);
		crawler.crawl(url);
		return linksAggregator.getAllLinks();
	}

}
            