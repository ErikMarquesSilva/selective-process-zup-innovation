package com.selectiveprocess.config;

import com.selectiveprocess.App;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = App.class)
@ActiveProfiles("integration")
public abstract class BaseIntegrationTest {
}
