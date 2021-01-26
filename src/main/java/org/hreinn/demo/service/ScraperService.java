package org.hreinn.demo.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ScraperService {

  public static final String JOB_LIST_URL = "https://www.kambi.com/find-job";
  public static final String JOB_DETAIL_URL = "https://kambi.workbuster.com/jobs/";

  @Getter
  private final Set<String> jobIDs = new HashSet<>();
  @Getter
  private final HashMap<String, String> jobDescription = new HashMap<>();

  public ScraperService() {
    doScrape();
  }

  // Regex pattern for recognizing a URL
  private static final Pattern urlPattern = Pattern.compile(
    "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
      + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
      + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


  public void doScrape() {
    try {
      Elements listParser = Jsoup.connect(JOB_LIST_URL)
        .maxBodySize(0)
        .timeout(0)
        .get().select("li");
      Set<String> jobIdSet = new HashSet<>();
      listParser.stream()
        .filter(element -> element.toString().contains("jobID"))
        .forEach(element -> {
          String raw = element.toString();
          Matcher matcher = urlPattern.matcher(raw);
          while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end();
            String jobID = raw.substring(start, end).split("=")[1];
            jobIDs.add(jobID);

            try {
              String description = Jsoup.connect(JOB_DETAIL_URL + jobID).get().text();
              jobDescription.put(jobID, description);
            } catch (IOException e) {
              log.error("Failed to connect to: " + JOB_DETAIL_URL);
              log.error("Scrape error: " + e.getMessage());
            }
          }
        });
    } catch (IOException e) {
      log.error("Failed to connect to: " + JOB_LIST_URL);
      log.error("Scrape error: " + e.getMessage());
    }

  }

  public String getDescription(String jobId){
    return "";
  }

}
