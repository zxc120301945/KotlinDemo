package com.kotlin.demo

import android.content.Context
import android.util.AttributeSet

/**
 * 继承
 */
class Inherit {

    //Kotlin 中所有类都继承该 Any 类，它是所有类的超类，对于没有超类型声明的类是默认超类：
    class Example // 从 Any 隐式继承

    //注意：Any 不是 java.lang.Object。
    //如果一个类要被继承，可以使用 open 关键字进行修饰。
    open class Base(p: Int) // 定义基类

    class Demo(p: Int) : Base(p)

    //构造函数
    //子类有主构造函数
    //如果子类有主构造函数， 则基类必须在主构造函数中立即初始化。
    open class Person(var name: String, var age: Int) {// 基类

    }

    class Student(name: String, age: Int, var no: String, var score: Int) : Person(name, age) {

    }

    fun demo() {
        val s = Student("Runoob", 18, "S12346", 89)
        println("学生名： ${s.name}")
        println("年龄： ${s.age}")
        println("学生号： ${s.no}")
        println("成绩： ${s.score}")
    }

    //子类没有主构造函数
    //如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，
    //或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。
    open class Person2(ctx: Context) {
        constructor(ctx: Context,attrs: AttributeSet) : this(ctx){

        }
    }

    class Student2 : Person2 {

        constructor(ctx: Context) : super(ctx) {
        }

        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {

        }
    }

    //重写
    //在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。
    //如果允许子类重写该函数，那么就要手动添加 open 修饰它,
    //子类重写方法使用 override 关键词：
    /**用户基类**/
    open class Person3{
        open fun study(){       // 允许子类重写
            println("Person3 类 我毕业了")
        }
    }

    interface Person4{
        open fun study(){       // 允许子类重写
            println("Person4 接口 我刚出生")
        }
    }

    /**子类继承 Person 类**/
    class Student3 : Person3(),Person4 {

        override fun study() {
            //如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择性地调用父类的实现。
            super<Person3>.study()
            super<Person4>.study()
            println("Student3 子类 我在读大学")
        }
    }

    fun demo2(){
        val s =  Student3()
        s.study()
    }

    //属性重写
    //属性重写使用 override 关键字，属性必须具有兼容类型，每一个声明的属性都可以通过初始化程序或者getter方法被重写：
    open class Foo {
        open val x: Int get(){
            return x
        }
    }

    class Bar1 : Foo() {
        override val x: Int = 1
    }

    //你可以用一个var属性重写一个val属性，但是反过来不行。因为val属性本身定义了getter方法，重写为var属性会在衍生类中额外声明一个setter方法
    //你可以在主构造函数中使用 override 关键字作为属性声明的一部分:
    interface Foo2 {
        val count: Int
    }

    class Bar3(override val count: Int) : Foo2

    class Bar2 : Foo2 {
        override var count: Int = 0
    }

}