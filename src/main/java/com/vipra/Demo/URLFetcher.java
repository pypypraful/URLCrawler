package com.vipra.Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import java.util.HashSet;
import java.util.Set;

public class URLFetcher {

    private static final Logger logger = LogManager.getLogger(URLFetcher.class);

    public Set<String> fetchURL(final String url) {
        final Set<String> links = new HashSet<>();
        try {
            if (url.endsWith(".png") || url.endsWith(".pdf") || url.endsWith(".jpg")) throw new Exception();
            Parser htmlParser = new Parser(url);
            final NodeList tagNodeList = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
            for (int j = 0; j < tagNodeList.size(); j++) {
                final LinkTag loopLink = (LinkTag) tagNodeList.elementAt(j);
                final String loopLinkStr = loopLink.getLink();
                links.add(loopLinkStr);
            }
            return links;
        } catch (Exception e) {
//            logger.error("Failed to Process this URL {}", url);
//            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
