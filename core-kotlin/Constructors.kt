/*
    Unlike Java, or any other object-oriented language, Kotlin has two types of constructor:
    Primary Constructor
    Secondary Constructor
    
    But it is not necessary to add secondary constructor also. You can have one or both constructors at a time.

    In Kotlin, you can declare the constructor in the class header itself:

    class Person constructor(name: String, age: Int, salary: Int) {
        
    }

    or just
    class Person (name: String, age: Int, salary: Int) {
        
    }

*/

// Secondary constructor
// refer https://blog.mindorks.com/primary-and-secondary-constructors-in-kotlin

/*
Points to Note:
    The init block is always called after the primary constructor
    A class file can have one or more init blocks executing in series.
*/