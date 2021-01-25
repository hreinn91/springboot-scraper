package org.hreinn.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
public class ScrapeTest {

  @Autowired
  ScraperService scraperService;

  @Test
  public void scrapeTest() throws IOException {
    // This test creates a scraperService. Logic is in the constructor.
  }
}
