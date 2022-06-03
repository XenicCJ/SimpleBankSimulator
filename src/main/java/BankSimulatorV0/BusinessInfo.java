package BankSimulatorV0;

//业务类型定义
enum BusinessType{deposit, loan}

//业务信息类，记录业务关键信息
public class BusinessInfo{
    //活期存款日利率，对应年利率0.3%
    public static final double dailyDRate=0.00000822;
    //贷款日利率，对应年利率10%
    public static double dailyLRate=0.000274;
    //贷存比例上限，总贷款额/(总资金+总贷款额)不得超过此比例
    public static double lToDRatio=0.75;
}