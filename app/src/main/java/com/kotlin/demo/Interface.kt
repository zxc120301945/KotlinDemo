package com.kotlin.demo

/**
 * 接口
 */
class Interface {

    //Kotlin 接口与 Java 8 类似，使用 interface 关键字定义接口，允许方法有默认实现：
    interface MyInterface {
        fun bar()    // 未实现
        fun foo() {  //已实现
            // 可选的方法体
            println("foo")
        }
    }

    //实现接口
    //一个类或者对象可以实现一个或多个接口。
    class Child : MyInterface {
        override fun bar() {
            // 方法体
        }

        //接口已实现方法实现类可实现也可不实现，但是接口未实现方法实现类一定要实现其方法
//        override fun foo() {
//            super.foo()
//        }
    }

    fun demo() {
        val c = Child()
        c.foo()
        c.bar()//不实现就直接使用接口实现的方法，实现了就用实现类的

    }

    //接口中的属性
    //接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性：
    interface MyInterface2{
        var name:String //name 属性, 抽象的
    }

    class MyImpl:MyInterface2{
        override var name: String = "runoob" //重载属性
    }

    fun demo2(){
        val c = MyImpl()
        c.name = "wan"
        print(c.name)
    }

    //函数重写
    //实现多个接口时，可能会遇到同一方法继承多个实现的问题。例如:
    interface A {
        fun foo() { print("A") }   // 已实现
        fun bar()                  // 未实现，没有方法体，是抽象的
    }

    interface B {
        fun foo() { print("B") }   // 已实现
        fun bar() { print("bar") } // 已实现
    }

    class C : A {
        override fun bar() { print("bar") }   // 重写
    }

    class D : A, B {
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }
    //实例中接口 A 和 B 都定义了方法 foo() 和 bar()， 两者都实现了 foo(), B 实现了 bar()。
    //因为 C 是一个实现了 A 的具体类，所以必须要重写 bar() 并实现这个抽象方法。
    //然而，如果我们从 A 和 B 派生 D，我们需要实现多个接口继承的所有方法，并指明 D 应该如何实现它们。
    //这一规则 既适用于继承单个实现（bar()）的方法也适用于继承多个实现（foo()）的方法。
}