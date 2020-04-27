package io.yommmm.spider.demo;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * TODO
 *
 * @author 85374
 * @date
 */
@Slf4j
public class ConsolePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("get page: {}", resultItems.getRequest().getUrl());
//        log.info("url is: {}", resultItems.getRequest().getUrl());
    }
}
