# WebCrawler

Web Crawler with a single thread.

## Commands

```bash
$ javac -d . -cp .:lib/gson-2.8.0.jar:lib/java-json.jar:lib/jsoup-1.10.1.jar: src/Main.java src/Crawler/CrawlerLeg.java src/Crawler/Crawler.java 
$ java -cp .:lib/gson-2.8.0.jar:lib/java-json.jar:lib/jsoup-1.10.1.jar: Main <arg>
```

Where the argument is the URL that represents the starting point for the Web Crawler.

## Description

Given an URL it goes through each link found on the page and gathers all the assets found. The assets included are images, JavaScripts and StyleSheets.

## Tests

There are two basic tests available.
