package Crawler;

import org.junit.Test;

import static org.junit.Assert.*;

public class CrawlerLegTest {

  private CrawlerLeg crawlerLeg;

  @Test
  public void crawl() throws Exception {

    crawlerLeg = initialiseCrawlerLeg();
    assertEquals(54, crawlerLeg.getPages().size());

  }

  @Test
  public void getAssets() throws Exception {

    crawlerLeg = initialiseCrawlerLeg();

    assertEquals(20, crawlerLeg.getAssets().size());

  }

  private CrawlerLeg initialiseCrawlerLeg() {
    crawlerLeg = new CrawlerLeg();

    crawlerLeg.crawl("https://www.gocardless.com");
    return crawlerLeg;
  }

}