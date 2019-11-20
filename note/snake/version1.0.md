1.new 窗口，大小，不能改变大小，并显示
2.添加window监听事件，关闭事件,键盘监听事件
3.new出来一个蛇（使用数组，一个对象是蛇的一部分）
4.根据键盘控制蛇的方向，上、下、左、右
5.蛇不能向相反方向
6.蛇可以通过键盘控制移动
    a. sp[0]right,sp[1]right,sp[2]right
    b. sp[0]up,sp[1]right,sp[2]right
    c. sp[0]up,sp[1]up,sp[2]right
    d. sp[0]left,sp[1]up,sp[2]up
    e. sp[0]down,sp[1]left,sp[2]up
    f. sp[0]down,sp[1]dowm,sp[2]left
    g. sp[0]down,sp[1]dowm,sp[2]dowm
7.new出来随机的一个点的食物
8.蛇头碰撞到食物，蛇的数组长度加1，食物死亡
9.食物死亡时重新new出一个食物
10.食物new出的位置不能和蛇身体重合

限制
1.食物位置不能在窗口外边
2.蛇头碰到窗口边缘，死亡
3.蛇头碰到蛇身体，死亡


//TODO
方向键按的快可以造成蛇方向改变为反方向  