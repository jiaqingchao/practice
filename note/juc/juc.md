1. **线程的概念、启动方式、常用方法**
   
2. **启动线程的三种方式**

   1. 继承Thread
   2. 实现RUnnoble
   3. Executors.newCachedThread

3. **synchronized的底层实现**

   1. 保证原子性，不能禁用cpu指令重排序
   2. synchroized(Object)
      1. 不能用String常量 Integer Long
      2. 不要使用基础数据类型，有各种各样的问题
   3. JDK早期的
      1. 重量级 - OS
   4. OS后来的改进锁升级的概念
      1. 我就是厕所所长（一，二）//文章，马士兵
   5. 访问sync(Object)  --- hotspot实现
      1. markord 记录这个线程ID(偏向锁)
      2. 如果有线程争用，升级为 自旋锁
      3. 自旋10次后，升级为重量级锁 - OS
   6. 什么情况下使用什么锁
      1. 执行时间短（枷锁代码），线程数量少 使用自旋锁
      2. 执行时间长或线程数量多，使用系统锁

4. **volatile**

   1. 保证线程可见性
      1. MESI
      2. 缓存一致性协议
   2. 禁止指令重排序
      1. DCL单例
      2. Double Check Lock
      3. Mgro6.java

5. **CAS**(无锁优化，自旋，乐观锁)

   1. Compare And Set/Swap

   2. cas(V,Expected,NewValue)

      1. if V == E

         ​	V = New

         ​	otherwise try again or fail

      2. CPU原语支持

   3. ABA问题

      1. 加version
         1. A 1.0
         2. B 2.0
         3. A 3.0
         4. cas(version)
      2. 如果基础类型--无所谓，引用类型--你的女朋友跟你复合，中间经历了别的女人 

6. **Unsafe **== c c++ 的指针

   1. 直接操作内存
      1. allocateMemory pubXX freeMemory pageSIze
   2. 直接生成类实例
      1. allocatelnstance
   3. 直接操作类或实例变量
      1. objectFieldOffset
      2. getInt
      3. getObject
   4. CAS相关操作
      1. weakcompareAndSetObject Int Long
   5. 分配内存,释放内存
      1. c->malloc free, C++ -> new delete，java->allocateMemory , freeMemory 

7. **ReentantLock vs synchronized**

   1. cas vs sync
   2. trylock
   3. lockinterupptibly
   4. 公平和非公平
   
8. **CyclicBarrier**

   1. 复杂操作
      1. 数据库
      2. 网络
      3. 文件
   2. 并发执行
      1. 线程1 -> 操作1
      2. 线程2 -> 操作2
      3. 线程3 -> 操作3
   3. 全部完成
      1. 继续执行下边的操作

9. **phaser**

   1. 带阶段的CyclicBarrier

10. **ReadWriteLock**

    1. 共享锁
    2. 排它锁

11. **Semaphore**

    1. 限流
    2. 车道和收费站
    
12. **AQS(CLH)源码**

    1. state - 核心属性， voaltile, 使用cas进行修改
    2. state的作用根据子类的实现，表示其具体的作用
    3. 队列为双向链表 - 核心队列
    4. VarHandle
       1. 普通属性也可以做原子操作
       2. 比反射快，直接操纵二进制码

13. **ThreadLocal**

    1. set
       1. Thread.currentThread.map(ThreadLocal,person);
       2. 设到当前前程的Map中，key为ThreadLocal,value为具体的值
    2. 用途
       1. 声明式事务，保证同一个Connection

14. **强软弱虚->引用**

    1. 强
       1. 普通的引用
    2. 软
       1. 大对象的缓存
       2. 常用对象的缓存
    3. 弱
       1. 缓存，没有容器引用指向的时候就需要清除的缓存
       2. ThreadLocal     //ThreadLocal不用后要remove掉
       3. WeakHashMap
    4. 虚
          1. 管理堆外内存
          2. DirectByteBuffer
             1. 直接内存  
             2. 不被jvm虚拟机直接管理的内存
             3. 被操作系统管理
          3. 当对象被回收时，通过Queue可以检测到，然后清理堆外内存
    
15. **容器---容器.png背过**

    1. Vector HashTable
       1. 自带锁 - 基本不用
    2. CopyOnwriteList
    3. ConcurrentHashMap
    4. ConcurrentSkipListMap
    5. BlockingQueue
    6. 目标：为ThreadPol做准备
    
7. **CAS一定比synchronized效率要高？**
       1. 不一定
           2. 看并发量的高低
           3. 看锁定代码执行的时间
           4. 单线程，用HashMap,ArrayList,LinkedList
           5. 高并发、执行时间短，用 ConcurrentHashMap, ConcurrentQueue
           6. 执行时间长，并发量不是特别高，用synchronized
           7. 任何时候，在实际情况下，都需要通过压测来决定到底用那种容器
   
16. **容器2  **

    1. ConcurrentHashMap
       1. Cas操作，用在Tree的节点上的时候特别复杂，所以没有 ConcurrentTreeMap
       2. ConcurrentSkipListMap 
          1. 高并发并且排序
          2. 跳表结构
       3. TreeMap    //红黑树->本身是排好顺序的，有平衡性的操作
    2. CopyOnWriteArrayList
       1. 复制并写
       2. 新建一个Array,length为原Array的length+1，把原来的array复制到新Array,将要添加的数据加到新Array的最后
    3. ConcurrentLinkedQueue
       1. Queue
          1. 添加了许多对线程友好的API
          2. offer("str"); //List.add("str")，返回boolean
          3. strs.poll();//取第一个，并去掉
          4. strs.peek();//取第一个
       2. BlockingQueue
          1. 比Queue多了put()和take();
          2. put(); // 如果满了就会等待
          3. take();//如果为空，就会等待
          4. offer("aaa",1, TimeUnit.SECONDS); //没满直接加，满了，在一秒之后尝试加，没有加进去返回false，
    4. BlockingQueue
       1. LinkedBlockingQueue
          1. 最大长度Integer.MAX_VALUE
       2. ArrayBlockingQueue
          1. 有界的
       3. DelayQueue
          1. 实现在时间上的排序
          2. 根据实现，按等待时间排序
          3. 用处，按时间进行时间调度
          4. 基于PriorityQueue
             1. 不是按顺序装的，内部进行了排序，最小的在上边，树的模型
       4. SynchronousQueue
      1. 线程之间传递任务
          2. 只能传一个，
          3. 容量为0.不能用add()，只能用put()
          4. 线程池中用处最大的一个Queue，很多线程取任务之间，进行任务调度时用的都是SynchronousQueue
       5. TransferQueue
          1. transfer("aaa"); //装了之后等着，被拿走才继续执行
          2. 做一件事情，要求有一个结果才能进行下边的操作执行：订单付账，要等付账结果完成，才能给客户反馈。
          3. 各种各样Queue的组合
          4. 可以给线程传递任务，不只是一个，做一个链表，传好多个
    
18. **ThreadPool**

    1. Executor

       1. 执行器，有执行方法
       2. 定义和运行分开，运行根据自己的定义实现

    2. ExecutorService

       1. 继承了Executor
       2. 完善了整个执行器的生命周期
       3. 线程池的是实现了它

    3. Callable

       1. Callable和Runnable一样，也可以是线程来运行它
       2. Callable.call() 相当于Runnable.run(),不同的是call()有返回值
       3. <u>有返回值得Runnable</u>

    4. Future

       1. 存储执行的将来才会产生的结果
       2. 调用get()方法获取返回值是会阻塞，直到获得返回值

    5. FutureTask

       1. Future + Runnable

    6. CompletableFuture

       1. 产生各种各样的异步任务
       2. 管理多个Future的结果
       3. 可以对多个结果进行组合处理

    7. **ThreadPollExecutor**

       1. corePoolSize:核心线程数
       2. maxmumPoolSize:最大线程数
       3. keepAliveTime:超过一定时间未使用将线程归还给操作系统
       4. unit：时间单位TimeUnit
       5. workQueue：任务队列
       6. threadFactory：线程工厂
       7. handler：线程池满和队列满执行拒绝策略
          1. abort:抛异常
          2. Discard:扔掉不抛异常
          3. DIscardOldest:扔掉排队时间最久的
          4. CallerRuns:调用者处理任务
       8. 来了任务先调用核心线程执行，核心线程满了，将任务放到队列中，队列满了新建线程执行，如果线程数已达到最大，执行拒绝策略
       9. **源码分析**
          1. Worker类
             1. 简单来说，就是当做一个单独的线程类，执行的是自己的任务
             2. 线程中运行，实现Runnable 
             3. 很多线程会抢,实现了AQS
             4. 执行过程中其他线程也可能使用这个worker运行其他的任务，所以应该加锁，AQS还是有必要的
             5. thread，那个thread在执行这个对象
          2. submit方法
          3. execute
             1. 核心线程数不够，起核心线程，
             2. 核心线程数够了，加queue
             3. queue也满了，起非核心线程
          4. addWorker
             1. count++ 任务数加一
             2. addworker 添加任务
             3. start 启动任务

    8. **ForkJoinPool**

       1. 分解汇总的任务
       2. 用很少的线程可以执行很多的任务(子任务) TPE做不到先执行子任务
       3. CPU密集型
       4. WorkStealingPool
       5. ParallelStreamAPI

    9. Executors-->线程池工厂

       1. SingleThreadPool ->单线程线程池

          1. 保证任务顺序执行
          2. 为什么要有单线程线程池？
             1. 任务队列
             2. 生命周期管理

       2. CachedThreadPool

          1. 核心线程0，最大线程Integer.Max_Value，60s灭活时间,SynchronousQueue

       3. FixedThreadPool

          1. 固定核心线程threads，固定最大线程threads，一直存活，LinkedBlockingQueue

       4. cached vs Fixed

          1. 用 cached
             1. 不确定任务量平稳还是不平稳，任务来了要保证有人做这个任务
          2. 用Fixed
             1. 任务来的比较平稳，认为固定的线程完全可以处理
          3. 阿里都不用，自己估算，进行精确定义

       5. ScheduledPool->定时任务线程池

          1. 复杂的定时任务quzrtz用的最多，简单的用Timer就行，cron比较强大不过涉及shell脚本
          2. scheduleAtFixedRate()
             1. 隔多少时间执行一次,
             2. 任务、延迟时间，隔多少时间，时间单位
          3. 面试:假如提供一个闹钟服务，订阅这个服务的人特别多，10亿人，怎么优化

       6. WorkStealingPool

          1. 多个work queue

          2. 采用workStealing算法

          3. push(),pop()，添加自己的任务，从自己的队列取任务，**不用加锁**

          4. poll(),使用workSteal算法从其他线程的任务队列偷任务，**加锁**

          5. 每个线程都有自己的队列，如果自己的执行完了，就会去其他的队列的最后边偷

          6. 底层是ForkJoinPool提供了一个方便的接口

          7. ```java
             return new ForkJoinPool (
                  Runtime.getRuntime().availableProcessors(),
                  ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                  null, true);
             }
             ```

       7. ForkJoinPool

          1. 分叉汇总com.jqc.juc.c_026_01_ThreadPool.T12_ForkJoinPool

       8. ParallelStreamAPI

          1. 底层是ForkJoinPool
          2. com.jqc.juc.c_026_01_ThreadPool.T13_ParallelStreamAPI

    10. concurrent vs paraller 并发vs并行

       1. 并发是指任务提交，并行值任务执行
       2. 并行是并发的子集

    11. 线程池的种类

        1. ThreadPoolExecutor
        2. ForkJoinPool

19. **JMH**

    1. Java Microbenchmark Harness
    2. 2013年首发
       1. 由JIT的开发人员开发
       2. 归于OpenJDK
    
20. **Disruptor**

    1. introduction - 介绍
       1. disruptor - 分裂、瓦解
       2. 一个线程中每秒处理600万订单
       3. 2011年的Duke奖  <!--最优秀的框架奖-->
       4. 速度最快的MQ
       5. 性能极高，无锁cas，单机支持高并发
