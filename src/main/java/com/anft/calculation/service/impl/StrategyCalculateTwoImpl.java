package com.anft.calculation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.anft.calculation.service.StrategyCalculate;
import com.anft.calculation.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anft.calculation.util.DateTimeUtil.*;

public class StrategyCalculateTwoImpl implements StrategyCalculate {

    private Logger logger= LoggerFactory.getLogger(StrategyCalculateTwoImpl.class);
    /**
     * 计费实现了
     * doc
     * @param rule   规则
     * @param startTime
     * @param endTime
     * @return
     */

    @Override
    public BigDecimal doCalculate(String rule, Date startTime, Date endTime) {

        int stopFullMins = DateTimeUtil.getFullMins(startTime, endTime);
        BigDecimal bigDecimal = BigDecimal.ZERO;
        JSONObject jsonObject = JSONObject.parseObject(rule);
        List<JSONObject> list = (List<JSONObject>) jsonObject.get("ruleList");
        Integer freeTime = jsonObject.getInteger("freeTime");
        Integer isInclude = jsonObject.getInteger("isInclude");
        // Integer isFreeRange = jsonObject.getInteger("isFreeRange");
        Integer cycleTime = jsonObject.getInteger("cycleTime");
        //封顶单价
        BigDecimal fixedFee = jsonObject.getBigDecimal("fixedFee");
        BigDecimal price = jsonObject.getBigDecimal("price");
        Integer priceNum = jsonObject.getInteger("priceNum");
        Integer fixedType = jsonObject.getInteger("fixedType");
        List<JSONObject> freeList = (List<JSONObject>) jsonObject.get("freeList");
        if (stopFullMins <= freeTime) {
            //停车时长
            return BigDecimal.ZERO;
        } else {
            if (fixedType == 2) {
                bigDecimal = calNoFreeTimes(startTime, endTime, list, freeList, cycleTime, fixedFee, freeTime, isInclude);

            } else if (fixedType == 0) {
                bigDecimal=noFixedType(startTime,endTime,list,isInclude,freeTime,price,priceNum);
            }
        }


        logger.info("parking  money:::::{}",bigDecimal);
        return bigDecimal;



    }


    /*
     * 没有 封顶周期的
     *
     * */


    public static BigDecimal noFixedType(Date startDate, Date endDate, List<JSONObject> ruleList, Integer isInclude, Integer freeTime,BigDecimal price,Integer priceNum) {
        BigDecimal payMoney = BigDecimal.ZERO;
        //计算停车时长


        Integer parkTimeSec = (int) ((endDate.getTime() - startDate.getTime()) / THOUSAND);

        if (isInclude == 0) {
            parkTimeSec = parkTimeSec - freeTime * Sixty;
        }

        for (JSONObject jsonObject : ruleList) {
            Map<String, Object> jsonMap = JSONObject.toJavaObject(jsonObject, Map.class);
            Integer startMinute = Integer.valueOf(String.valueOf(jsonMap.get("startMinute"))) * Sixty;
            Integer endMinute = Integer.valueOf(String.valueOf(jsonMap.get("endMinute"))) * Sixty;
            BigDecimal ruleMoney = new BigDecimal(String.valueOf(jsonMap.get("price")));
            if (startMinute < parkTimeSec && parkTimeSec <= endMinute) {
                payMoney = payMoney.add(ruleMoney);
            }

        }

        Map<String, Object> maxMinMap = ruleList.get(ruleList.size() - 1);
        Integer endMinute = Integer.valueOf(String.valueOf(maxMinMap.get("endMinute"))) * Sixty;
        if (parkTimeSec > endMinute) {
            BigDecimal ruleMoney = new BigDecimal(String.valueOf(maxMinMap.get("price")));
            payMoney = payMoney.add(ruleMoney);
            //超出多少秒
            Integer chaoSec = parkTimeSec - endMinute;
            Integer chaoMIn=chaoSec/Sixty;
            //超出几个小时
            Integer num= chaoMIn%priceNum==0?chaoMIn/priceNum:chaoMIn/priceNum+1;

            if(chaoMIn>0 && chaoMIn<priceNum){
                num=1;
            }
            BigDecimal totalPrice=price.multiply(new BigDecimal(num+""));
            payMoney=payMoney.add(totalPrice);

        }

        return payMoney;
    }




    /**
     * 针对有封顶周期的
     *
     * @param startDate 车辆驶入时间
     * @param endDate   车辆离场时间
     * @param freeList  免费时间段
     * @param cycleTime 封顶周期时间段
     * @return
     */
    public static BigDecimal calNoFreeTimes(Date startDate, Date endDate, List<JSONObject> ruleList, List<JSONObject> freeList, Integer cycleTime, BigDecimal fixedFee, Integer freeTime, Integer isInclude) {

        BigDecimal payMoney = BigDecimal.ZERO;
        //1  判断停车时长是否大于封顶周期
        //换算成秒 更加精确
        Long cycleTimeMin = Long.valueOf(cycleTime * Sixty);
        Long parkTimes = (endDate.getTime() - startDate.getTime()) / THOUSAND;

        //大于封顶周期
        //算出封顶周期个数
        Integer cycleTimeNum = (int) (parkTimes / cycleTimeMin);
        Long startTime2 = (Long.valueOf(cycleTimeNum * cycleTime) * Sixty * THOUSAND + startDate.getTime());
        Date startDate2 = new Date(startTime2);
        if (cycleTimeNum == 0) {
            startDate2 = startDate;
        }

        //封顶单价*封顶周期个数
        BigDecimal money = fixedFee.multiply(new BigDecimal(cycleTimeNum + ""));
        payMoney = payMoney.add(money);
        //算出周期外金额
        //计算停车时长=总停车时长-不收费时长
        /**
         * 周期外的总停车时长
         */
        Integer totalSec = (int) ((endDate.getTime() - startDate2.getTime()) / THOUSAND);
        Integer sec = calSec(startDate2, endDate, freeList);
        //收费时长  总时长-免费时长
        Integer charge = totalSec - sec;
        if (isInclude == 0) {
            charge = charge - freeTime * Sixty;
        }
        //免费时长计费
        if (charge > freeTime * 60) {
            for (JSONObject jsonObject : ruleList) {
                Map<String, Object> jsonMap = JSONObject.toJavaObject(jsonObject, Map.class);
                Integer startMinute = Integer.valueOf(String.valueOf(jsonMap.get("startMinute"))) * Sixty;
                Integer endMinute = Integer.valueOf(String.valueOf(jsonMap.get("endMinute"))) * Sixty;
                BigDecimal ruleMoney = new BigDecimal(String.valueOf(jsonMap.get("price")));
                if (startMinute < charge && charge <= endMinute) {
                    payMoney = payMoney.add(ruleMoney);
                }

            }

        }


        return payMoney;

    }




    /**
     * 计算重叠的秒
     *
     * @param startDate2
     * @param endDate
     * @param freeList
     * @return
     */
    public static Integer calSec(Date startDate2, Date endDate, List<JSONObject> freeList) {

        Integer sec = 0;

        if (null == freeList) {
            return sec;
        }
        List<HashMap<String, Date>> parkIngDateList = getParkIngDateList(startDate2, endDate);
        for (int i = 0; i < parkIngDateList.size(); i++) {

            String currDate = DateTimeUtil.formatDate(parkIngDateList.get(i).get("begin"), MEDIATIME_PATTERN);
            Date startDate1 = parkIngDateList.get(i).get("begin");
            Date endDate1 = parkIngDateList.get(i).get("end");
            for (JSONObject jsonObject : freeList) {
                Map<String, Object> jsonMap = JSONObject.toJavaObject(jsonObject, Map.class);
                String beginTimeFree = jsonMap.get("beginTime").toString();
                String endTimeFree = jsonMap.get("endTime").toString();
                try {
                    Date startTime = new SimpleDateFormat(LONGTIME_PATTERN).parse(currDate + " " + beginTimeFree + ":00");
                    Date endTime = new SimpleDateFormat(LONGTIME_PATTERN).parse(currDate + " " + endTimeFree + ":00");
                    //计算重叠的秒
                    sec = sec + DateTimeUtil.calcOverlapDays(startDate1, endDate1, startTime, endTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        }
        return sec;

    }


}
