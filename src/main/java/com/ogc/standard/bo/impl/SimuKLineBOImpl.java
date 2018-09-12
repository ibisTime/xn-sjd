package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISimuKLineBO;
import com.ogc.standard.bo.ISimuOrderDetailBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.dao.ISimuKLineDAO;
import com.ogc.standard.domain.ExchangePair;
import com.ogc.standard.domain.SimuKLine;
import com.ogc.standard.domain.SimuOrderDetail;
import com.ogc.standard.enums.ESimuKLinePeriod;

@Component
public class SimuKLineBOImpl extends PaginableBOImpl<SimuKLine>
        implements ISimuKLineBO {

    @Autowired
    private ISimuKLineDAO simuKLineDAO;

    @Autowired
    private ISimuOrderDetailBO simuOrderDetailBO;

    /**
     * 落地K线
     * @param orderDetail 
     * @create: 2018年8月31日 下午5:07:46 lei
     * @history:
     */
    @Override
    public void saveSimuKLine(ExchangePair pair, BigDecimal volume,
            BigDecimal quantity, BigDecimal amount, BigDecimal open,
            BigDecimal close, BigDecimal high, BigDecimal low) {
        SimuKLine simuKLine = new SimuKLine();
        simuKLine.setSymbol(pair.getSymbol());
        simuKLine.setToSymbol(pair.getToSymbol());
        simuKLine.setVolume(volume);
        simuKLine.setQuantity(quantity);
        simuKLine.setAmount(amount);

        simuKLine.setOpen(open);
        simuKLine.setClose(close);
        simuKLine.setHigh(high);
        simuKLine.setLow(low);
        simuKLine.setCreateDatetime(new Date());
        simuKLineDAO.insert(simuKLine);
    }

    @Override
    public List<SimuKLine> querySimuKLineList(SimuKLine condition) {
        return simuKLineDAO.selectList(condition);
    }

    @Override
    public SimuKLine getLatestSimuKLine(String symbol, String toSymbol,
            String period) {
        SimuKLine data = null;

        SimuKLine condition = new SimuKLine();
        condition.setSymbol(symbol);
        condition.setToSymbol(toSymbol);
        condition.setPeriod(period);
        condition.setOrder("create_datetime desc");

        // 获取最新的一条
        List<SimuKLine> simuKLines = querySimuKLineList(condition);
        if (CollectionUtils.isNotEmpty(simuKLines)) {
            data = simuKLines.get(0);
        }
        return data;
    }

    @Override
    public void saveKLineByPeriod(ExchangePair pair, int unitMin) {

        List<SimuOrderDetail> simuOrderDetails = getSimuOrderDetails(pair,
            unitMin);

        // 成交量
        BigDecimal volume = BigDecimal.ZERO;
        // 成交笔数
        BigDecimal quantity = new BigDecimal(simuOrderDetails.size() + "");
        // 成交额
        BigDecimal amount = BigDecimal.ZERO;
        // 开盘价（当前成交单的第一单）
        BigDecimal open = BigDecimal.ZERO;
        // 收盘价（当前成交单的最后一单）
        BigDecimal close = BigDecimal.ZERO;
        // 最高价
        BigDecimal high = BigDecimal.ZERO;
        // 最低价
        BigDecimal low = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(simuOrderDetails)) {

            // 开盘价（当前成交单的第一单）
            open = simuOrderDetails.get(0).getTradedPrice();

            // 收盘价（当前成交单的最后一单）
            close = simuOrderDetails.get(simuOrderDetails.size() - 1)
                .getTradedPrice();

            // 最低价从成交单里取一个默认值
            low = simuOrderDetails.get(0).getTradedPrice();

            // 统计单位时间内的K线信息
            for (SimuOrderDetail simuOrderDetail : simuOrderDetails) {

                volume = volume.add(simuOrderDetail.getTradedCount());

                amount = amount.add(simuOrderDetail.getTradedAmount());

                if (high.compareTo(simuOrderDetail.getTradedPrice()) < 0) {
                    high = simuOrderDetail.getTradedPrice();
                }

                if (low.compareTo(simuOrderDetail.getTradedPrice()) > 0) {
                    low = simuOrderDetail.getTradedPrice();
                }

            }

        } else { // 当前时间单位没有交易产生

            // 获取上一时间单位的K线
            SimuKLine simuKLine = getLatestSimuKLine(pair.getSymbol(),
                pair.getToSymbol(), ESimuKLinePeriod.MIN1.getCode());

            if (null != simuKLine) {

                open = simuKLine.getClose();

                close = simuKLine.getClose();

            }

        }

        saveSimuKLine(pair, volume, quantity, amount, open, close, high, low);
    }

    /**
     * 根据交易对获取前一个时间单位起始到前一分钟结束的成交单
     * @param pair
     * @param LastMin
     * @return 
     * @create: 2018年9月4日 上午3:44:48 lei
     * @history:
     */
    private List<SimuOrderDetail> getSimuOrderDetails(ExchangePair pair,
            int unitMin) {
        List<SimuOrderDetail> simuOrderDetails;

        SimuOrderDetail condition = new SimuOrderDetail();
        condition.setSymbol(pair.getSymbol());
        condition.setToSymbol(pair.getToSymbol());

        // 时间单位起始
        condition.setCreateDatetimeStart(DateUtil.strToDate(
            getTimeByMinute(unitMin) + ":00", DateUtil.DATA_TIME_PATTERN_1));

        // 上一分钟
        String lastMin = getTimeByMinute(-1);
        condition.setCreateDatetimeEnd(
            DateUtil.strToDate(lastMin + ":59", DateUtil.DATA_TIME_PATTERN_1));
        condition.setOrder("create_datetime asc");

        simuOrderDetails = simuOrderDetailBO
            .querySimuOrderDetailList(condition);
        return simuOrderDetails;
    }

    // 根据分钟数获取时间
    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm")
            .format(calendar.getTime());

    }

}
