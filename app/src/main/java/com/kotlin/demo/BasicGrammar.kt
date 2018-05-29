package com.kotlin.demo

/**
 * 基础语法
 */
class BasicGrammar {
    init {

    }

    //函数定义使用关键字fun,参数格式：参数：类型
    fun demo(a: Int, b: Int): Int {//Int参数，返回值int
        return a + b
    }

    //表达式作为函数体，返回值自动推断
    fun demo2(a: String, b: String) = a + b

    //public 方法必须明确写出返回值
    public fun demo3(a: Int): Int = a

    //无返回值的函数
    fun demo4(a: Int): Unit {
        print(a)
    }

    //返回值为Unit，可省略
    public fun demo5(a: Int) {
        print(a)
    }

    //可变长参数函数
    //函数的变长参数可用vararg关键字进行标识
    fun demo6(vararg v: Int) {
        for (aa in v) {
            print(aa)
        }
    }

    //lambda(匿名函数)
    fun demo7(a: Int, b: Int): Int {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        return sumLambda(a, b)
    }

    //定义常量和变量
    //可变变量关键字：var
    //var <标识符> ：<类型> = <初始化值>
    //不可变变量关键字：val，只能赋值一次的变量(类似于Java的final)
    //val <标识符> : <类型> = <初始化值>
    //常量与变量都可以没有初始化值,但是在引用前必须初始化
    var mBianLiang: Int = 1
    var mBianLiang2 = 2
    val CHANG_LIANG: Int = 1
    val CHANG_LIANG2 = 2

    //注释支持多行和单行注释，允许嵌套注释

    //字符串模板
    //$表示一个变量名或者变量值
    //$varName表示变量值
    //${varName.fun()}表示变量的方法返回值
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    //NULL检查机制
    //Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，
    // 有两种处理方式，
    // 1.字段后加!!像Java一样抛出空异常，
    // 2.字段后加?可不做处理返回值为 null或配合?:做空判断处理
    //类型后面加?表示可为空
    var age: String? = "23"
    //抛出空指针异常
    val ages = age!!.toInt()
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1

    //当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null。
    //当 str 中的字符串内容不是一个整数时, 返回 null:
    fun demo8(str: String): Int? {
        return str?.toInt() ?: -1
    }

    //类型检测及自动类型转换
    //我们可以使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
    fun demo9(obj: Any): Int? {
        if (obj is String) {
            // 做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        } else if (obj !is Int) {
            //在这里还有一种方法，与Java中instanceof不同，使用!is,意思是不是这种类型
            return -1
        } else {
            //这里直接被转换成int类型
            return obj
        }
        // 这里的obj仍然是Any类型的引用
        return null

        //或者
        //if (obj !is String)
        //    return null
        //在这个分支中, `obj` 的类型会被自动转换为 `String`
        //  return obj.length

        //或者
        // fun getStringLength(obj: Any): Int? {
        // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
        //  if (obj is String && obj.length > 0)
        //    return obj.length
        //  return null
        //}
    }

    //区间
    //区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
    //区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现。以下是使用区间的一些示例:
    fun demo10(i: Int) {
        for (i in 1..4) print(i) // 输出“1234”
        for (i in 4..1) print(i) // 什么都不输出
        if (i in 1..10) { // 等同于 1 <= i && i <= 10
            println(i)
        }
        // 使用 step 指定步长
        for (i in 1..4 step 2) print(i) // 输出“13”

        for (i in 4 downTo 1 step 2) print(i) // 输出“42”
        // 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            println(i)
        }

        //使用区间
        //使用 in 运算符来检测某个数字是否在指定区间内，区间格式为 x..y ：

        val x = 5
        val y = 9
        if (x in 1..8) {
            println("x 在区间内")
        }

    }

}