package Crawler;

import org.json.JSONException;

import java.util.*;

public class Crawler {

  private Set<String>  pagesVisited       = new HashSet<>();
  private List<String> pagesToVisitQueue  = new LinkedList<>();


  /**
   * Given an URL it does a breadth first search for links that start from this
   * point.
   *
   * @param url the starting URL
   */
  public void searchURL(String url) {

    // Adds the beginning sign '[' and the ending one when necessary ']' of the
    // JSONArray. 
    startCreatingJson();

    pagesToVisitQueue.add(url);

    // Loops and visits each page added to the queue.
    while (!pagesToVisitQueue.isEmpty()) {

      String currentUrl = pagesToVisitQueue.remove(0);

      CrawlerLeg crawlerLeg = new CrawlerLeg();

      if (!pagesVisited.contains(currentUrl) && isValidUrl(currentUrl) &&
          notAnchor(currentUrl)) {

        crawlerLeg.crawl(currentUrl);
        pagesToVisitQueue.addAll(crawlerLeg.getPages());
        pagesVisited.add(currentUrl);

        try {
          crawlerLeg.displayJSON(currentUrl);
        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    }

  }

  // Verifies if the URL represents an anchor on the page (a link to a fragment
  // on the same page).
  private boolean notAnchor(String currentUrl) {

    return !currentUrl.contains("#");

  }

  // Adds the starting point for the JSONArray and also starts a thread that
  // waits for the shutdown of the program to close the respective array.
  private void startCreatingJson() {
    System.out.println("[");

    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      public void run() {
        System.out.println("]");
      }
    }, "Shutdown-thread"));
  }

  // Verifies if the given URL is a valid one.
  private boolean isValidUrl(String currentUrl) {
    return currentUrl != null && currentUrl.length() != 0;
  }

}
