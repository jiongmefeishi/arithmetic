package cn.zqtao.design;

/**
 * @auther: zqtao
 * @description: 工厂模式和策略模式在实际业务中的应用
 * <p>
 * 美团返奖策略
 * <p>
 * 具体：用工厂模式来生产出不同的策略（n 种策略），同时使用策略模式来执行不同的策略
 * <p>
 * 工厂模式：
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法是一个类的实例化延迟到其子类。
 * <p>
 * 策略模式：
 * 定义一系列算法，将每个算法都封装起来，并且它们可以转换。策略模式是一种对象行为模式。
 * @version: 1.0
 */
public class Factory_Strategy {

    //抽象策略
    abstract class RewardStrategy {
        public abstract void reward(long userId);

        public void insertRewardAndSettlement(long userId, int reward) {
        }

        ; //更新用户信息以及结算
    }

    //新用户返奖具体策略A
    public class newUserRewardStrategyA extends RewardStrategy {
        @Override
        public void reward(long userId) {
        }  //具体的计算逻辑，...
    }

    //老用户返奖具体策略A
    public class OldUserRewardStrategyA extends RewardStrategy {
        @Override
        public void reward(long userId) {
        }  //具体的计算逻辑，...
    }

    //抽象工厂
    public abstract class StrategyFactory<T> {
        abstract RewardStrategy createStrategy(Class<T> c);
    }

    //具体工厂创建具体的策略
    public class FactorRewardStrategyFactory extends StrategyFactory {
        @Override
        RewardStrategy createStrategy(Class c) {
            RewardStrategy product = null;
            try {
                product = (RewardStrategy) Class.forName(c.getName()).newInstance();
            } catch (Exception e) {
            }
            return product;
        }
    }


    // 通过工厂模式生产出具体的策略之后，根据我们之前的介绍，很容易就可以想到使用策略模式来执行我们的策略

    /*class RewardContext {
        private RewardStrategy strategy;

        public RewardContext(RewardStrategy strategy) {
            this.strategy = strategy;
        }

        public void doStrategy(long userId) {
            int rewardMoney = strategy.reward(userId);
            insertRewardAndSettlement(long userId, int reward){
                insertReward(userId, rewardMoney);
                settlement(userId);
            }
        }
    }

    class InviteRewardImpl {
        //返奖主流程
        public void sendReward(long userId) {
            FactorRewardStrategyFactory strategyFactory = new FactorRewardStrategyFactory();  //创建工厂
            Invitee invitee = getInviteeByUserId(userId);  //根据用户id查询用户信息
            if (invitee.userType == UserTypeEnum.NEW_USER) {  //新用户返奖策略
                NewUserBasicReward newUserBasicReward = (NewUserBasicReward) strategyFactory.createStrategy(NewUserBasicReward.class);
                RewardContext rewardContext = new RewardContext(newUserBasicReward);
                rewardContext.doStrategy(userId); //执行返奖策略
            }if(invitee.userType == UserTypeEnum.OLD_USER){}  //老用户返奖策略，...
        }
    }*/
}
