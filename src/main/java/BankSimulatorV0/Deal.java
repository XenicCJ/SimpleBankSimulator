package BankSimulatorV0;

//客户类，记录客户信息
public class Deal {
    //已接待客户/业务总量
    private static int clientAmount=0;
    //本客户或业务号码
    private int clientNo;
    //业务类型
    public BusinessType businessType;
    //业务额度（随时间变化）
    private double money;
    //业务开始天
    private int startDay;
    //业务结束天
    private int endDay;

    public Deal(BusinessType bt, double money, int startDay, int endDay){
        //初始化客户序号，修改客户总数
        Deal.clientAmount++;
        this.clientNo=clientAmount;
        //初始化其他信息
        businessType = bt;
        this.money=money;
        this.startDay=startDay;
        this.endDay=endDay;
    }

    public static int getClientAmount() {
        return clientAmount;
    }

    public int getClientNo() {
        return clientNo;
    }

    public double getMoney() {
        return money;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    //用中文返回业务是存款还是贷款
    public String getBusinessTypeInCN(){
        if(businessType==BusinessType.deposit){
            return "存款";
        }else if(businessType==BusinessType.loan){
            return "贷款";
        }else {
            return "无效";
        }
    }

    public void printAllClientInfo(){
        System.out.println("客户号："+clientNo+", 业务类型："+getBusinessTypeInCN()+", 业务金额："+money+", 业务开始日："+startDay+", 业务结束日："+endDay);
    }
}