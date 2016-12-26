package Crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class CrawlerLeg {


  private final Set<String> pages = new HashSet<>();
  private static final String UserAgent
      = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) " +
      "Gecko/20070725 Firefox/2.0.0.6";
  private Document htmlDocument;


  /**
   * Main method of the CrawlerLeg. Goes through the document of the given URL
   * and remembers all the links found so that they can be added in the
   * PagesQueue later on.
   *
   * @param url the URL from which we get the document and all the links
   */
  void crawl(String url) {


    Connection connection = Jsoup.connect(url).userAgent(UserAgent);

    try {

      htmlDocument = connection.get();

      Elements links = htmlDocument.select("a[href]");

      for (Element link : links) {

        pages.add(link.absUrl("href"));

      }

    } catch (IOException e) {
      System.out.println("Could not retrieve HTML Document.");
    }


  }


  // Displays the current JSONObject with the URL and Assets information.
  void displayJSON(String url) throws JSONException {

    Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
        .create();

    JSONObject jsonObject = new JSONObject();

    JSONArray jsonArray = new JSONArray();

    Set<String> assets = getAssets();

    if (assets != null) {

      for (String asset : assets) {

        jsonArray.put(asset);

      }
    }

    jsonObject.put("assets", jsonArray);
    jsonObject.put("url", url);

    JsonParser parser = new JsonParser();
    JsonElement jsonElement = parser.parse(jsonObject.toString());

    System.out.println(gson.toJson(jsonElement));

  }


  // Retrieves all the assets on the page (Images, JavaScripts, StyleSheets).
  Set<String> getAssets() {


    Set<String> assets = new HashSet<>();

    if (htmlDocument == null) {

      return null;

    }

    Elements images = htmlDocument.select("img[src]");

    for (Element image : images) {

      assets.add(image.absUrl("src"));

    }

    Elements javaScripts = htmlDocument.select("script[src]");

    for (Element javaScript : javaScripts) {

      assets.add(javaScript.absUrl("src"));

    }

    Elements styleSheets = htmlDocument.select("link[rel=\"stylesheet\"]");

    for (Element styleSheet : styleSheets) {

      assets.add(styleSheet.absUrl("href"));

    }

    return assets;

  }

  /**
   * Retrieves the pages that were found while crawling the current URL.
   *
   * @return a list of URLs that are connected to this current one
   */
  Set<String> getPages() {

    return pages;
  }
}
