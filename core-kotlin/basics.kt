lateinit varName:String

fun main() {

    // valid statements
    // val name:String? =null
    // println(name)

    // invalid statements
    // val name:String =null// error in this statment
    // println(name)

    // if(::varName.isInitialsed){
    //     println(varname)
    // }

    /*
    What are these late init and lazy variables
    LateInit is simple as name suggest the varaible which we dont want to initialise now but want to initialise
    at some other point like simple object with null and then assign value to it
    We can check if the late init var is initialised or not
    

    Use case of late init var 
    creating a val serverKey:String
    and then getting from api fetch, now in these use cases we either make it hold a ddefault value like "key"
    or just make it null and then init in some later point of code and then use it, checking for null before using it

    Same above thing can be established with help of late init var which is not initialised as its created, but 
    initiaated at later point of time
        Used when we are using dependency injection like from dagger which inject the dependency by itself at runtime 
    */

    /*
    Use case of lazy object, suppose we are creating a heavy object like retrofit instance and returning other properties along with it
    now creating of retrofit instance is a heavy task and thus it would delay the creattion of netwerok instance and 
    it may happedn that retrofit instance is not used instacnlty from created object thus we want to speed thing up thus we do this lazy init
    object would be initialsed when accessed like in seg tree we have lazy propagation
     */

        // in kotlin we have when instead of switch

        // kotlin has for and while loop, we have until, step keyword

        // no 'new' keyword in kotlin 
        // use constructir keyword to create a constructor

        // kotlin class can have primary constructor or simple constuctor see personPrimaryconstructor below
        // u can make init block refer code below

        // to make constructor feild like name and des in Person object available for member fun to operate on the make them var/val

        // listOf create immutabel list in kotlin, use mutableListOf for mutable list purpose
        // immutableList has a fun called plus which return new immutable list with elem added in it

        // same for immutableMap it also has plus api and return new immutable map
        
        
}

class Person(name:String,des:String){
    init{
        // some code that u want to run as soon as construct is called 
    }
}