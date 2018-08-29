package com.ogc.standard.huobipro;

import java.util.List;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:23:57 
 * @history:
 */
public class TradingViewConfig {

    List<TradingViewExchange> exchanges;

    List<String> supported_resolutions;

    boolean supports_group_request;

    boolean supports_marks;

    boolean supports_search;

    boolean supports_time;

    boolean supports_timescale_marks;

    List<TradingViewSymbolType> symbols_types;

    public List<TradingViewExchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<TradingViewExchange> exchanges) {
        this.exchanges = exchanges;
    }

    public List<String> getSupported_resolutions() {
        return supported_resolutions;
    }

    public void setSupported_resolutions(List<String> supported_resolutions) {
        this.supported_resolutions = supported_resolutions;
    }

    public boolean isSupports_group_request() {
        return supports_group_request;
    }

    public void setSupports_group_request(boolean supports_group_request) {
        this.supports_group_request = supports_group_request;
    }

    public boolean isSupports_marks() {
        return supports_marks;
    }

    public void setSupports_marks(boolean supports_marks) {
        this.supports_marks = supports_marks;
    }

    public boolean isSupports_search() {
        return supports_search;
    }

    public void setSupports_search(boolean supports_search) {
        this.supports_search = supports_search;
    }

    public boolean isSupports_time() {
        return supports_time;
    }

    public void setSupports_time(boolean supports_time) {
        this.supports_time = supports_time;
    }

    public boolean isSupports_timescale_marks() {
        return supports_timescale_marks;
    }

    public void setSupports_timescale_marks(boolean supports_timescale_marks) {
        this.supports_timescale_marks = supports_timescale_marks;
    }

    public List<TradingViewSymbolType> getSymbols_types() {
        return symbols_types;
    }

    public void setSymbols_types(List<TradingViewSymbolType> symbols_types) {
        this.symbols_types = symbols_types;
    }

}
