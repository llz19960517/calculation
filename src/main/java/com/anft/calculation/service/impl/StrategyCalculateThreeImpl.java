package com.anft.calculation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.anft.calculation.service.StrategyCalculate;
import com.anft.calculation.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

import static com.anft.calculation.util.DateTimeUtil.*;

public class StrategyCalculateThreeImpl implements StrategyCalculate {

    private Logger logger= LoggerFactory.getLogger(StrategyCalculateThreeImpl.class);



    public static void main(String[] args) {

        StrategyCalculateThreeImpl strategyCalculateThree=new StrategyCalculateThreeImpl();
        BigDecimal bigDecimal14 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 14:01:00"));
        BigDecimal bigDecimal15 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 15:01:00"));
        BigDecimal bigDecimal16 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 16:01:00"));
        BigDecimal bigDecimal17 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 17:01:00"));
        BigDecimal bigDecimal18 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 18:01:00"));
        BigDecimal bigDecimal19 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 19:01:00"));
        BigDecimal bigDecimal20 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 20:01:00"));
        BigDecimal bigDecimal21 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 21:01:00"));
        BigDecimal bigDecimal23 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-02 23:01:00"));
        BigDecimal bigDecimal01 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-03 01:01:00"));
        BigDecimal bigDecimal03 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-03 03:01:00"));
        BigDecimal bigDecimal05 = strategyCalculateThree.doCalculate(null, DateTimeUtil.getDateByStr("2020-09-02 05:01:00"), DateTimeUtil.getDateByStr("2020-09-03 05:01:00"));

        System.out.println(bigDecimal14);
        System.out.println(bigDecimal15);
        System.out.println(bigDecimal16);
        System.out.println(bigDecimal17);
        System.out.println(bigDecimal18);
        System.out.println(bigDecimal19);
        System.out.println(bigDecimal20);
        System.out.println(bigDecimal21);
        System.out.println(bigDecimal23);
        System.out.println(bigDecimal01);
        System.out.println(bigDecimal03);
        System.out.println(bigDecimal05);
    }



    /**
     * 计费实现了
     * doc      type =3
     * @param rule   规则
     * @param startTime
     * @param endTime                         \
     *
     *       白天：7：00——21：00   每15分钟收费 2元； 晚上：21：00-7：00点 每2小时收费1元；不足一个时间段不收费。
     *         跨昼夜分界点停放且停放时间不足昼夜分界点前的一个计时单位的停车收费，
     *          按分界点前的收费标准和计时单位收费，不得拆分为两个时段计收停车费。
     *
     *
     *
     *
     *
     *
     * @return
     */






    @Override
    public BigDecimal doCalculate(String rule, Date startTime, Date endTime) {
        BigDecimal bigDecimal=BigDecimal.ZERO;
           //得到入场短时间
        //白天




//{"fixedType":"0","priceNum":60,"isInclude":1,"price":0.02,"freeTime":0,"unitFeeOrNot":"1","isSection":0,"type":"3","dayStartTime":""}
//        String day="07:00:00";
//        int freeTimeDayMin=15;
//        double dayPrice=2.00;
//        double nightPrice=1.00;
//        int freeTimeNightMin=120;
//        String night="21:00:00";


                   try {
                       JSONObject jsonObject = JSONObject.parseObject(rule);

                       String day=jsonObject.getString("dayStartTime");
                       int freeTimeDayMin=jsonObject.getInteger("freeTimeDayMin");
                       double dayPrice=jsonObject.getDouble("dayPrice");
                       double nightPrice=jsonObject.getDouble("nightPrice");
                       int freeTimeNightMin=jsonObject.getInteger("freeTimeNightMin");;
                       String night=jsonObject.getString("dayEndTime");
                       String is_lingche=jsonObject.getString("is_lingche");
                       Integer lingcheFreeTime=jsonObject.getInteger("lingcheFreeTime");


                       if(null!=is_lingche && is_lingche.equals("2")){

                           Long fullSs = getFullSs(startTime, endTime);

                           int l = (int) (fullSs / 60);
                           if(l<lingcheFreeTime){
                               return bigDecimal;
                           }

                       }



                       // 判断开始时间是否在白昼区间  是return  true  else  false
                       //总停车时间

                       Date enterTime=startTime;
                       boolean flag=true;
                       while (flag){
                           //  入场时间是白天
                           String enterShortTime = getStringShort(enterTime);

                           // 判断入场时间是晚上还是夜间
                           Date dayStart = getDateByStr(enterShortTime + " " + day);
                           //白天结束时间
                           Date dayEnd = getDateByStr(enterShortTime + " " + night);

                           boolean effectiveDate = isEffectiveDate(enterTime, dayStart, dayEnd);

                           if(effectiveDate){
                               int stopFullMins = DateTimeUtil.getFullMins(enterTime, endTime);
                               if(stopFullMins<freeTimeDayMin){
                                   //不足 一个计费单元    freeTimeDayMin  15
                                   //不计费
                                   flag=false;
                               }else {
                                   //超过一个计费单元
                                   if(!endTime.before(dayEnd)){

                                       // 判断离场时间和夜间的开始时间是否小于  freeTimeDayMin  15  区间
                                       long fullMins = getFullSs(enterTime, dayEnd);
                                       // i
                                       long i = fullMins % (freeTimeDayMin*60);
                                       //白天的结束点
                                       long i1 = (freeTimeDayMin*60 - i)==freeTimeDayMin*60?0:freeTimeDayMin*60-i;
                                       Date date = plusSsByDate(dayEnd, i1);

                                       if(endTime.after(date)){
                                           long fullMins1 = getFullSs(enterTime, date);
                                           long i2 = (fullMins1 / (freeTimeDayMin*60));
                                           //  白天跨晚上
                                           bigDecimal=BigDecimal.valueOf(dayPrice*(i2)).add(bigDecimal);
                                           flag=true;
                                           //撵时间
                                           enterTime=  date;

                                       }else {
                                           long fullMins1 = getFullSs(enterTime, endTime);
                                           long i2 = (fullMins1 / (freeTimeDayMin*60));
                                           bigDecimal=BigDecimal.valueOf(dayPrice*i2).add(bigDecimal);
                                           flag=false;

                                       }


                                   }else {
                                       int num = stopFullMins / freeTimeDayMin;
                                       double v = num * dayPrice;
                                       //总金额
                                       bigDecimal=BigDecimal.valueOf(v).add(bigDecimal);
                                       flag=false;

                                   }
                               }

                           }else {
                               //  入场时间是晚上天
                               int stopFullMins = DateTimeUtil.getFullMins(enterTime, endTime);
                               //  不足一个计费单元
                               if(stopFullMins<freeTimeNightMin){
                                   flag=false;
                               }else {
                                   String hhmmss = getHhmmss(enterTime);
                                   int i3 = CompareTwoString(hhmmss, day);
                                   Date date = dateAddDay(enterTime, 1);

                                   if(i3==1){
                                       date = enterTime;
                                   }

                                   //得到短时间
                                   String stringShort = getStringShort(date);
                                   //得到夜间的结束时间
                                   Date dateByStr = getDateByStr(stringShort + " " + day);
                                   //离场时间小于 夜间结束时间
                                   if(endTime.before(dateByStr)){
                                       int ngNum = stopFullMins / freeTimeNightMin;
                                       double v = ngNum * nightPrice;
                                       bigDecimal=bigDecimal.add(BigDecimal.valueOf(v));
                                       flag=false;

                                   }else {

                                       // 判断离场时间和夜间的开始时间是否小于  freeTimeDayMin  120  区间
                                       long fullMins = getFullSs(enterTime, dateByStr);
                                       // i
                                       long i = (fullMins % (freeTimeNightMin*60));
                                       //白天的结束点
                                       long i1 = (freeTimeNightMin*60 - i)==freeTimeNightMin*60?0:freeTimeNightMin*60-i;
                                       //白天时间点
                                       Date date1 = plusSsByDate(dateByStr, i1);

                                       if(endTime.after(date1)){
                                           int fullMins1 = getFullMins(enterTime, date1);
                                           int i2 = fullMins1 / freeTimeNightMin;
                                           //  白天跨晚上
                                           bigDecimal=BigDecimal.valueOf(nightPrice*i2).add(bigDecimal);
                                           flag=true;
                                           //撵时间
                                           enterTime=  date1;



                                       }else {
                                           int fullMins1 = getFullMins(enterTime, endTime);
                                           int i2 = fullMins1 / freeTimeNightMin;
                                           //  白天跨晚上
                                           bigDecimal=BigDecimal.valueOf(nightPrice*i2).add(bigDecimal);
                                           flag=false;
                                       }

                                   }

                               }



                           }



                       }
                   }catch (Exception e){
                       e.printStackTrace();
                       logger.info("异常信息{}",e.getMessage());
                   }
        return bigDecimal;


    }



}
