/*
Similarly, you can put some variable in the companion object and access it with the help of the class name. The following is an example of the same:

class ToBeCalled {
    companion object Test {
        var someInteger: Int = 10
        fun callMe() = println("You are calling me :)")
    }
}
fun main(args: Array<String>) {
    print(ToBeCalled.someInteger)
}
This is all about companion object in Kotlin.

*/