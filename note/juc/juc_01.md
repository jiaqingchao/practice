1. 启动线程的三种方式
   1. 继承Thread
   2. 实现RUnnoble
   3. Executors.newCachedThread
2. **synchronized的底层实现**
   1. JDK早期的
      1. 重量级 - OS
   2. OS后来的改进锁升级的概念
      1. 我就是厕所所长（一，二）//文章，马士兵
   3. 访问sync(Object)  --- hotspot实现
      1. markord 记录这个线程ID(偏向锁)
      2. 如果有线程争用，升级为 自旋锁
      3. 自旋10次后，升级为重量级锁 - OS
   4. 什么情况下使用什么锁
      1. 执行时间短（枷锁代码），线程数量少 使用自旋锁
      2. 执行时间长或线程数量多，使用系统锁