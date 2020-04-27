package io.yommmm.spider.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author 85374
 * @date
 */
public class HnWJWPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        if (page.getUrl().regex("http://wjw.hunan.gov.cn/wjw/xxgk/rsxx/index.html").match()) {
            List<String> all = page.getHtml().xpath("//*[@class=\"table_list_st\"]/tr/td/a[@target=\"_blank\"]").all();
//            List<String> all = page.getHtml().xpath("//*[@class=\"table_list_st\"]/tr/td").all();
            all.stream().forEach(a -> {
                if (a.indexOf("招聘") > 0 || a.indexOf("计划") > 0) {
                    StringBuilder url = new StringBuilder("http://wjw.hunan.gov.cn");
                    String suffix = a.substring(a.indexOf("href=\"") + 6, a.indexOf(".html\""));
                    url.append(suffix);
                    url.append(".html");
                    page.addTargetRequest(url.toString());
                }
            });
        } else {
            System.out.println(page.getUrl());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HnWJWPageProcessor())
                .addUrl("http://wjw.hunan.gov.cn/wjw/xxgk/rsxx/index.html")
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
    }
}
