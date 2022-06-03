package BankSimulator;

public class BasicVars {
    //初始资金
    public static double basicFund=100000000;
    //每日支出占总资本比例
    public static double dailyPayRatio=0.0001;
    //贷存比例上限
    public static double lToDRatio=0.75;
    //存款宣传度
    public static double depositAdRate=1;
    //贷款宣传度
    public static double loanAdRate=1;
    //日最大存款客户数
    public static int dailyMaxDClient=200;
    //日最大贷款客户数
    public static int dailyMaxLClient=200;

    //活期存款日利率
    public static double dailyDRate=0.00000822;
    //存款基准时长
    public static int basicDTime=10;
    //存款时长波动率
    public static double dTimeVol=1;
    //基准存款额
    public static double basicDAmount=100000;
    //存款额波动率
    public static double dAmountVol=1;

    //活期贷款日利率
    public static double dailyLRate=0.000274;
    //贷款基准时长
    public static int basicLTime=10;
    //贷款时长波动率
    public static double lTimeVol=1;
    //基准贷款额
    public static double basicLAmount=100000;
    //贷款额波动率
    public static double lAmountVol=1;

    public static void InitStaticValues(){

    }

    public static void main(String[] args) {

    }
}
