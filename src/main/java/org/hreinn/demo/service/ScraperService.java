package org.hreinn.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ScraperService {

  public static final String JOB_LIST_URL = "https://www.kambi.com/find-job";
  public static final String JOB_DETAIL_URL = "https://www.kambi.com/job-detail?jobID=";

  private final Set<String> jobIDs = new HashSet<>();
  private final HashMap<String, String> jobDescription = new HashMap<>();

  public ScraperService(){
    try {
      doScrape();
    } catch (IOException e){
      log.error("Failed to connect to: " + JOB_LIST_URL);
      log.error("Scrape error: " + e.getMessage());
    }
  }


  public void doScrape() throws IOException {

    Elements listParser = Jsoup.connect(JOB_LIST_URL).get().select("li");
    Set<String> jobSet = new HashSet<>();
    listParser
      .stream()
      .filter(element -> element.toString().contains("jobID"))
      .forEach(element -> {
        log.info(element.text());
      });
//    Jsoup.connect("http://example.com").get();
//    doc.select("p").forEach(System.out::println);
  }

}
