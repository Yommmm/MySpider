package io.yommmm.spider.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * TODO
 *
 * @author 85374
 * @date
 */
public class CnblogsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        if (!page.getUrl().regex("http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html").match()) {
            page.addTargetRequests(
                    page.getHtml().xpath("//*[@id=\"mainContent\"]/div/div/div[@class=\"postTitle\"]/a/@href").all());
        } else {
            page.putField(page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/text()").toString(),
                    page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/@href").toString());
        }
    }

    public static void main(String[] args) {
        Spider.create(new CnblogsSpider()).addUrl("http://www.cnblogs.com/justcooooode/")
                .addPipeline(new ConsolePipeline()).run();
    }

}
