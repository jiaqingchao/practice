1. 第一天
   1. 线程的概念、启动方式、常用方法
   2. synchroized(Object)
      1. 不能用String常量 Integer Long
      2. 不要使用基础数据类型，有各种各样的问题
   3. 线程同步
      1. synchroized
         1. 锁的是对象，不是代码
         2. this (static)XX.class
         3. 锁定方法，非锁定方法 同时执行
         4. 锁升级
            1. 偏向锁 自旋锁 重量锁
            2. 什么时候使用 偏向锁，什么时候使用重量级锁