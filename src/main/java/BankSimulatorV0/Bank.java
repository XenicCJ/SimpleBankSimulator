package BankSimulatorV0;

import java.util.LinkedList;
import java.util.Scanner;

public class Bank{
    //天数
    private int day=1;
    //银行现有资金，即银行持有的现金
    private double money=1000000;
    //银行存款总额，即正在进行中业务的存款总额
    private double depositMoney=0;
    //银行贷款总额
    private double loanMoney=0;
    //保存每天新客户的信息
    private LinkedList<Deal> newDealLinkedList =new LinkedList<Deal>();
    //业务正在进行中的客户信息
    private LinkedList<Deal> dealLinkedList =new LinkedList<Deal>();
    //输入流
    private Scanner sc=new Scanner(System.in);

    /**
     * 输出银行每天开始时基本信息
     * 当参数flag为0时输出当天开始信息
     * 当参数flag为1时输出当天结束信息
     */
    private void printDailyBankInfo(){
        System.out.println("第"+day+"天开始了！");
        System.out.println("银行资金："+String.format("%.2f",money)+"元");
        System.out.println("银行存款总额："+String.format("%.2f",depositMoney)+"元");
        System.out.println("银行贷款总额："+String.format("%.2f",loanMoney)+"元");
        System.out.println("业务进行中客户总数："+ dealLinkedList.size());
    }

    /**
     * 处理队列中的业务信息，业务信息跑批
     * 若业务结算失败则延期一天
     */
    private void dealOngoingBusiness(){
        for (Deal deal : dealLinkedList) {
            //若业务还未结束，则直接进入下一次循环
            if(deal.getEndDay()>day){
                continue;
            }
            //若业务到结束日或超过结束日,则进入结算审批
            if(deal.businessType==BusinessType.deposit){
                //取款业务,若银行总资金足够则批准，不足则延期
                //计算总取款金额
                double withdrawTotal= deal.getMoney()*(1+BusinessInfo.dailyDRate*(day- deal.getStartDay()));
                //若银行总资金不足时，延期处理
                if(withdrawTotal< deal.getMoney()){
                    System.out.println("拒绝本次取款业务请求，银行持有资金不足，业务信息：");
                    deal.printAllClientInfo();
                    continue;
                }
                //减去总资金
                money-=withdrawTotal;
                //减去存款额
                depositMoney-= deal.getMoney();
            }else if (deal.businessType==BusinessType.loan){
                //还款业务，假设每个客户是守约的，默认批准
                //计算贷款本金加利息总额
                double loanTotal= deal.getMoney()*(1+BusinessInfo.dailyLRate*(day- deal.getStartDay()));
                //增加总资金
                money+=loanTotal;
                //减贷款总额
                loanMoney-= deal.getMoney();
            }
            //业务已批准，将业务从表中删除
            dealLinkedList.remove(deal);
        }
    }

    /**
     * 接收一天的新业务信息，存放在newClientLinkedList中
     */
    private void getDailyNewBusiness(){
        //清空新用户链表信息
        newDealLinkedList.clear();
        //临时变量区
        int clientAmount;
        BusinessType bt=BusinessType.deposit;
        int temp;
        double money;
        int length;
        Deal newDeal;
        //处理新客户信息
        System.out.println("输入要接待的客户数量：");
        clientAmount=sc.nextInt();
        for(int i=0;i<clientAmount;i++){
            System.out.println("输入第"+(i+1)+"位客户的业务类型（1表示存款，2表示贷款）：");
            temp=sc.nextInt();
            if(temp==1){
                bt=BusinessType.deposit;
            }else if (temp==2){
                bt=BusinessType.loan;
            }
            System.out.println("输入第"+(i+1)+"位客户的业务金额：");
            money=sc.nextDouble();
            System.out.println("输入第"+(i+1)+"位客户的业务持续天数：");
            length=sc.nextInt();
            newDeal =new Deal(bt,money,this.day,this.day+length);
            newDealLinkedList.add(newDeal);
            //输出新增业务信息
            System.out.print("新增业务记录完成，新增业务信息：");
            newDeal.printAllClientInfo();
        }
        System.out.println("新客户信息接收完毕");
    }

    /**
     * 银行新业务审查处理岗，判断是否接受新客户的业务，若接受则进行业务，不接受则输出原因
     */
    private void dealDailyNewBusiness(){
        for (Deal deal : newDealLinkedList) {
            if(deal.businessType==BusinessType.deposit){
                //存款业务无条件接受
                //增加总资金
                money+= deal.getMoney();
                //增加存款总额
                depositMoney+= deal.getMoney();
            }else if (deal.businessType==BusinessType.loan){
                //贷款业务，在不得超过贷存比的情况下放贷款
                //计算进行此次贷款后的贷存比
                double afterBusinessRatio=(loanMoney+ deal.getMoney())/(money+loanMoney);
                //若贷存比超过规定则拒绝
                if(afterBusinessRatio>BusinessInfo.lToDRatio){
                    System.out.println("拒绝本次贷款业务请求，贷后贷存比超过规定贷存比，业务信息：");
                    deal.printAllClientInfo();
                    continue;
                }
                //未拒绝，进行业务处理
                //减总资金
                money-= deal.getMoney();
                //加贷款总额
                loanMoney+= deal.getMoney();
            }
            //将客户加入进行中业务集合
            dealLinkedList.add(deal);
        }
    }

    /**
     * 进入下一天
     */
    private void enterNextDay(){
        day++;
    }


    //开始程序运行的方法
    /*
    每天开始时输入客户数量
    然后输入各个客户的业务信息
    按e跳过当天，按q结束运行
     */
    public void start(){
        boolean end=false;
        //不结束则一直运行
        while(true){
            //输出当天开始时信息
            printDailyBankInfo();
            //处理正在进行的业务
            dealOngoingBusiness();
            //获取新业务信息
            getDailyNewBusiness();
            //处理新业务信息
            dealDailyNewBusiness();
            //日加一进入下一天
            enterNextDay();
        }
    }
}