package org.hreinn.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@SpringBootTest
public class ScrapeTest {

  @Autowired
  ScraperService scraperService;

  @Test
  public void scrapeTest() throws IOException {
    HashMap<String, String> jobDescription = scraperService.getJobDescription();
    log.info("Done");
  }

}
