package com.lavender.service;//package com.lavender.service;
//
//
//import com.lavender.pojo.MethodStatisticModel;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.aopalliance.intercept.MethodInvocation;
//
//public class ServiceTimeWaste implements MethodInterceptor {
//
//    @Override
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        MethodStatisticModel methodStatisticModel = new MethodStatisticModel ();
//        System.out.println ("計時開始");
//        long start= System.currentTimeMillis();
//
//        Object proceed = invocation.proceed ();
//
//        System.out.println ("執行了方法"+invocation.getMethod ()+"\n返回值爲：" +proceed);
//
//
//        long end = System.currentTimeMillis();
//        System.out.println ("計時結束"+ "耗時：" +(end-start)+"毫秒");
//
//        methodStatisticModel.setSpendTime (String.valueOf ((end-start)));
//        methodStatisticModel.setMethod (splitStr (String.valueOf (invocation.getMethod ())));
//        String sql="insert into `d_statistic`(`spend_time`,`method`) values ('"+
//                methodStatisticModel.getSpendTime () +"'" +","+ "'"+methodStatisticModel.getMethod ()+"')";
//
//        JDBC.update (sql);
//
//        return proceed;
//
//    }
//
//    public String  splitStr(String str){
//        String[] list= str.split ("\\.");
//        String method=list[list.length-3].split ("\\(")[0];
//        return method;
//
//
//    }
//
////    public static void main(String[] args) {
////        String str="public abstract com.lavender.model.ResultModel com.lavender.service.BaseService.delete(java.lang.Object)";
////
////        String[] list = str.split ("\\.");
////        System.out.println (list[list.length-3].split ("\\(")[0]);
////    }
//}
//
