package com.ogc.standard.huobipro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ogc.standard.common.JsonUtil;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:23:39 
 * @history:
 */
@Controller
public class KLineController {

    @RequestMapping(value = "/kline/config", method = RequestMethod.GET)
    public void doBtcCallback(HttpServletRequest request,
            HttpServletResponse response) {

        TradingViewConfig config = new TradingViewConfig();
        TradingViewExchange exchange = new TradingViewExchange();
        TradingViewSymbolType symbolType = new TradingViewSymbolType();

        PrintWriter writer;

        try {
            writer = response.getWriter();
            writer.append(JsonUtil.Object2Json(config));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
