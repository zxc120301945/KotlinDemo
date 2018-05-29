package com.kotlin.demo

/**
 * 扩展
 */
class Expand {

    //kotlin 可以对一个类的属性和方法进行扩展，且不需要继承或使用 Decorator 模式。
    //扩展是一种静态行为，对被扩展的类代码本身不会造成任何影响。

    //扩展函数
    //扩展函数可以在已有类中添加新的方法，不会对原类做修改，扩展函数定义形式：
//    fun receiverType.functionName(params){
//        body
//    }

    //扩展关键字:
    //receiverType：表示函数的接收者，也就是函数扩展的对象
    //functionName：扩展函数的名称
    //params：扩展函数的参数，可以为NULL

    class User(var name:String)

    /**扩展函数**/
    fun User.Print(){
        print("用户名 $name")
    }

    fun demo(){
        var user = User("Runoob")
        user.Print()
    }

    // MutableList 添加一个swap 函数：
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]     //  this 对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun demo2(){
        val l = mutableListOf(1, 2, 3)
        // 位置 0 和 2 的值做了互换
        l.swap(0, 2) // 'swap()' 函数内的 'this' 将指向 'l' 的值

        println(l.toString())

    }

    //this关键字指代接收者对象(receiver object)(也就是调用扩展函数时, 在点号之前指定的对象实例)。

    //扩展函数是静态解析的
    //扩展函数是静态解析的，并不是接收者类型的虚拟成员，在调用扩展函数时，具体被调用的的是哪一个函数，
    //由调用函数的的对象表达式来决定的，而不是动态的类型决定的:
    open class C

    class D: C()

    fun C.foo() = "c"   // 扩展函数 foo

    fun D.foo() = "d"   // 扩展函数 foo

    fun printFoo(c: C) {
        println(c.foo())  // 类型是 C 类
    }

    fun demo3(){
        printFoo(D())
    }

    //若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
    class E {
        fun foo() { println("成员函数") }
    }

    fun E.foo() { println("扩展函数") }

    fun demo4(){
        var e = E()
        e.foo()
    }

    //扩展一个空对象
    //在扩展函数内， 可以通过 this 来判断接收者是否为 NULL,这样，即使接收者为 NULL,也可以调用扩展函数。
    //例如:
    fun Any?.toString(): String {
        if (this == null) return "null"
        // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
        // 解析为 Any 类的成员函数
        return toString()
    }
    fun demo5(){
        var t = null
        println(t.toString())
    }

    //>扩展属性
    //除了函数，Kotlin 也支持属性对属性进行扩展:
    val <T> List<T>.lastIndex: Int
        get() = size - 1

    //扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中。
    //初始化属性因为属性没有后端字段（backing field），所以不允许被初始化，只能由显式提供的 getter/setter 定义。
    //val Foo.bar = 1 // 错误：扩展属性不能有初始化器
    //扩展属性只能被声明为 val。

    //伴生对象的扩展
    //如果一个类定义有一个伴生对象 ，你也可以为伴生对象定义扩展函数和属性。
    //伴生对象通过"类名."形式调用伴生对象，伴生对象声明的扩展函数，通过用类名限定符来调用：
    class MyClass {
        companion object { }  // 将被称为 "Companion"
    }

    //扩展方法
    fun MyClass.Companion.foo() {
        println("伴随对象的扩展函数")
    }

    //扩展属性
    val MyClass.Companion.no: Int
        get() = 10

    fun demo6() {
        println("no:${MyClass.no}")
        MyClass.foo()
    }

    //扩展的作用域
    //通常扩展函数或属性定义在顶级包下:
    //package foo.bar

    //fun Baz.goo() { …… }

    //要使用所定义包之外的一个扩展, 通过import导入扩展的函数名进行使用:
    //package com.example.usage

    //import foo.bar.goo // 导入所有名为 goo 的扩展
                         // 或者
    //import foo.bar.*   // 从 foo.bar 导入一切

    //fun usage(baz: Baz) {
    //    baz.goo()
    //}

    //扩展声明为成员
    //在一个类内部你可以为另一个类声明扩展。
    //在这个扩展中，有个多个隐含的接受者，其中扩展方法定义所在类的实例称为分发接受者，而扩展方法的目标类型的实例称为扩展接受者。
    class G {
        fun bar() { println("G bar") }
    }

    class F {
        fun baz() { println("F baz") }

        fun G.foo() {
            bar()   // 调用 G.bar
            baz()   // 调用 F.baz
        }

        fun caller(g: G) {
            g.foo()   // 调用扩展函数
        }
    }

    fun demo7() {
        val f: F = F()
        val g: G = G()
        f.caller(g)

    }

    //在 C 类内，创建了 D 类的扩展。此时，C 被成为分发接受者，而 D 为扩展接受者。
    //从上例中，可以清楚的看到，在扩展函数中，可以调用派发接收者的成员函数。
    //假如在调用某一个函数，而该函数在分发接受者和扩展接受者均存在，则以扩展接收者优先，要引用分发接收者的成员你可以使用限定的 this 语法。
    class I {
        fun bar() { println("I bar") }
    }

    class H {
        fun bar() { println("H bar") }  // 与 D 类 的 bar 同名

        fun H.foo() {
            bar()         // 调用 D.bar()，扩展接收者优先
            this@H.bar()  // 调用 C.bar()
        }

        fun caller(h: H) {
            h.foo()   // 调用扩展函数
        }
    }

    fun demo8() {
        val h: H = H()
        val i: I = I()
        h.caller(h)

    }

    //以成员的形式定义的扩展函数, 可以声明为 open , 而且可以在子类中覆盖.
    //也就是说, 在这类扩展函数的派 发过程中, 针对分发接受者是虚拟的(virtual), 但针对扩展接受者仍然是静态的。
    open class A {
    }

    class A1 : A() {
    }

    open class B {
        open fun A.foo() {
            println("D.foo in C")
        }

        open fun A1.foo() {
            println("D1.foo in C")
        }

        fun caller(a: A) {
            a.foo()   // 调用扩展函数
        }
    }

    class B1 : B() {
        override fun A.foo() {
            println("A.foo in B1")
        }

        override fun A1.foo() {
            println("A1.foo in B1")
        }

    }


    fun demo9() {
        B().caller(A())   // 输出 "D.foo in C"
        B1().caller(A())  // 输出 "D.foo in C1" —— 分发接收者虚拟解析
        B().caller(A1())  // 输出 "D.foo in C" —— 扩展接收者静态解析

    }
}