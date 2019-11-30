> 此工程是边缘服务，主要的作用是联合各个模块对外统一输出

举例：
用户下在JD下订单，调用的是此服务，由此服务向各个微服务模块发起请求，包括订单的锁定，库存的扣减以及
账户余额的扣减等等一系列操作。

> Hmily框架基本使用（TCC模式），⚠️亲试注意的几个地方

1. `org.dromara.hmily.started = true` 发起者为true，参与者为false（必须），不允许一个服务既是发起者，又是参与者，所以要抽出一个类似站点的服务来充当发起者角色。
2. 建议所有的发起者跟参与者都要标上`@Hmily`和`@Transactionan`注解
3. confirm跟cancel方法失败后也会记录在事务日志表中，这个不用担心
4. 在所有的参与者中，不允许将异常吞掉，这样就没办法回滚了
5. 对于发起者一开始就需要先新增然后才去远程调用服务的操作，我们可以单独抽出一个service来处理远程操作，例子：`com.zhangjianbing.story.service.impl.OrderServiceImpl.orderPay`