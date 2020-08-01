/*
    It is used where we need only one instance of the class like NetworkService, DatabaseService, etc.
    Generally, it is done because it takes the resource of the system to create these objects again and again. 
    So it is better to create only once and use again and again the same object.

    Properties of Singleton Class
    Following are the properties of a typical singleton class:
    1.  Only one instance: The singleton class has only one instance and this is done by providing an instance of the class, within the class. Also, outer classes and subclasses should be prevented to create the instance.
    2.  Globally accessible: The instance of the singleton class should be globally accessible so that each class can use it.


*/

/*
Rules for making a class Singleton
The following rules are followed to make a Singleton class:

    1.  A private constructor
    2.  A static reference of its class
    3.  One static method
    4.  Globally accessible object reference
    5.  Consistency across multiple threads

*/

/*
// below is code for java
public class Singleton {
    private static Singleton instance = null;
    private Singleton() {
        
    }
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

*/

// Now code for kotlin
object SingletonExample
// pretty much single line
/*  Explanation
    In Kotlin, we need to use the object keyword to use Singleton class. 
    The object class can have functions, properties, and the init method. 
    The constructor method is not allowed in an object so we can use the init method if some initialization is 
    required and the object can be defined inside a class. 
    The object gets instantiated when it is used for the first time.
 */

/*
 object Singleton{
    
    init {
        println("Singleton class invoked.")
    }
    var variableName = "I am Var"
    fun printVarName(){
        println(variableName)
    }

}

fun main(args: Array<String>) {     
    Singleton.printVarName()
    Singleton.variableName = "New Name"
        
    var a = A()
}

class A {

    init {
        println("Class init method. Singleton variableName property : ${Singleton.variableName}")
        Singleton.printVarName()
    }
}
*/

/*
    Object extending a class
    You can use an object in Kotlin to extend 
    some class or implement some interface just like a normal class. Let’s have an example of the same:

    fun main(args: Array<String>) {
    var a = A()
    Singleton.printVarName()
    }

    open class A {

        open fun printVarName() {
            print("I am in class printVarName")
        }

        init {
            println("I am in init of A")
        }
    }

    object Singleton : A() {

        init {
            println("Singleton class invoked.")
        }

        var variableName = "I am Var"
        override fun printVarName() {
            println(variableName)
        }
    }
output
    I am in init of A
    I am in init of A
    Singleton class invoked.
    I am var
*/


// credits https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
// we have a problem what if we want to pass params to create singleton class, as we cannot have construct in it 
// and only way is to do initial stuff in init{} block

/*
    Well Lazy initialization means that you do not initialize objects until the first time they are used.

    Early initialization is just reverse, you initialize a singleton upfront at the time of class loading.

    If we particularly talk about Android, we know that in Android we generally need to pass a context 
    instance to init block of a singleton. This can be done using Early initialization and Lazy initialization. 
    In early initialization, all the components are initialized in the Application.onCreate() using the init() functions. 
    But this results in slowing down the application startup by blocking the main thread. 
    So, it is generally advised to use the lazy initialization way. 
    In lazy initialization, we use the context as an argument to a function returning the instance of the singleton. 
    We can achieve this by using a SingletonHolder class. 
    Also, to make it thread-safe, we need to have a way of synchronization and double-checked locking.

    open class SingletonHolder<out T: Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
                val checkInstanceAgain = instance
                if (checkInstanceAgain != null) {
                    checkInstanceAgain
                } else {
                    val created = creator!!(arg)
                    instance = created
                    creator = null
                    created
                }
            }
        }
    }

    The above code is the most efficient code for double-checked locking system and the code is somehow similar 
    to the lazy() function in Kotlin and that’s why it is called lazy initialization. 
    So, whenever you want a singleton class with arguments then you can use the SingletonHolder class.

    Here, in the above code, in place of the creator function which is passed as an argument to the SingletonHolder, a custom lambda can also be declared inline or we can pass a reference to the private constructor of the singleton class. So, the code will be:

    class YourManager private constructor(context: Context) {
        init {
            // do something with context
        }

        companion object : SingletonHolder<YourManager, Context>(::YourManager)
    }
    Now, the singleton can be easily invoked and initialized by writing the below code and this is lazy as well as thread-safe :)

    YourManager.getInstance(context).doSomething()
*/
