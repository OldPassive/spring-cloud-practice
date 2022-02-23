package io.gitee.oldpassive.spring.cloud;

import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ZonedDateTimeTest {

  @Test
  void test_get_zoned_datetime() {
    log.info(ZonedDateTime.now().toString());
    Assertions.assertTrue(true);
  }
}
