package com.lym;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lym.dao.FundInfoDao;
import com.lym.dao.FundStockReDao;
import com.lym.domain.FundInfo;
import com.lym.domain.FundStockRe;
import com.tustrip.network.HttpUtil;
import com.tustrip.network.Request;
import com.tustrip.network.Response;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuyanmin on 2021/2/14.
 */
@Log4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FundSpiderTest {

    private static String URL_1 = "http://fund.eastmoney.com/js/fundcode_search.js";
    private static String URL_2 = "http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jjcc&code=%s&topline=10&year=%s";

    @Autowired
    private FundInfoDao fundInfoDao;
    @Autowired
    private FundStockReDao fundStockReDao;

    @Test
    public void test() throws Exception {
        int year = 2020;

        Response response = HttpUtil.get(Request.build().setUrl(URL_1));
        String body = response.getBody();
        body = body.substring(8, body.length() - 1);
        JSONArray jsonArray = JSONObject.parseArray(body);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray tmp = jsonArray.getJSONArray(i);
            String fundCode = tmp.getString(0);

            FundInfo fundInfo = fundInfoDao.getByFundCode(fundCode);
            if (fundInfo != null) {
                continue;
            }

            String fundName = tmp.getString(2);
            String fundType = tmp.getString(3);
            fundInfo = new FundInfo()
                    .setFundCode(fundCode)
                    .setFundName(fundName)
                    .setFundType(fundType);
            fundInfoDao.insert(fundInfo);

            String url = String.format(URL_2, fundCode, year);
            log.info(url);
            Document doc = Jsoup.connect(url).get();
            if (doc.selectFirst("h4.t") == null) {
                log.info("基金代码: " + fundCode + "，基金名称: " + fundName + "，无股票数据");
                continue;
            }

            String latestDate = doc.selectFirst("h4.t").select("label").get(1).selectFirst("font.px12").text();
            Elements trs = doc.selectFirst("tbody").select("tr");
            for (Element tr : trs) {
                Elements tds = tr.select("td");
                String seqNo = tds.get(0).text();
                String stockCode;
                if (tds.get(1).selectFirst("a") != null) {
                    stockCode = tds.get(1).selectFirst("a").text();
                } else {
                    stockCode = tds.get(1).selectFirst("span").text();
                }
                String stockName;
                if (tds.get(2).selectFirst("a") != null) {
                    stockName = tds.get(2).selectFirst("a").text();
                } else {
                    stockName = tds.get(2).selectFirst("span").text();
                }

                String percentage, count, fundMoney;
                if (tds.size() > 7) {
                    percentage = tds.get(6).text();
                    count = tds.get(7).text().replace(",", "");
                    fundMoney = tds.get(8).text().replace(",", "");
                } else {
                    percentage = tds.get(4).text();
                    count = tds.get(5).text().replace(",", "");
                    fundMoney = tds.get(6).text().replace(",", "");
                }

                FundStockRe fundStockRe = new FundStockRe()
                        .setSeqNo(Integer.parseInt(seqNo))
                        .setFundCode(fundCode)
                        .setStockCode(stockCode)
                        .setStockName(stockName)
                        .setLatestDate(latestDate)
                        .setPercentage(percentage)
                        .setCount(Float.parseFloat(count))
                        .setFundMoney(Float.parseFloat(fundMoney));
                fundStockReDao.insert(fundStockRe);
            }
        }
    }
}
