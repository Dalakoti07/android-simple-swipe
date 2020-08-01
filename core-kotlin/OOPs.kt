
// OPEN
/*
    Classes in kotlin are final, thus cannot be inherited 

    function are also final in kotlin class so we need to make them open for overriding in inherited class

    Variables (yes u heard it right ) are also final in kotlin class so we need to make them open for overriding in inherited class

    In Kotlin, the classes, the functions, and the variables are final in nature by default i.e. 
    they can’t be inherited from any other class. So, to make it inheritable from other classes, 
    we use the open keyword with the class, function, and variable name.
*/

/*
    Visibility modifier on kotlin


    Simple old stuff for private
    // Private modifier example

    // Visible just inside this file
    private const val noOfStudents = 1000

    // Visible just inside this file
    private class Student() {
        
        // Visible just inside the Student class
        private val newCount = noOfStudents
    }

    // ERROR: newCount is not accessible outside the Student class
    private const val finalCount = newCount

*/

/*
// Protected modifier example

// Visible just inside this file
private const val noOfStudents = 1000

// Visible just inside this file
open private class Student() {

    // Visible just inside the Student class and its subclass
    protected val newCount = noOfStudents
}

// Visible inside this file
private class StudentManager : Student() {
    // Visible inside the StudentManager class
    // newCount is visible because StudentManager is a subclass of Student
    private val finalCount = newCount
}

// ERROR: protected modifier is not allowed for top level declarations
protected const val noOfStudent = 10000

// ERROR: protected modifier is not allowed for top level declarations
protected class Staff()

*/

// INternal modifier a new thing
/*
    Read from here https://blog.mindorks.com/learn-kotlin-visibility-modifiers-private-protected-internal-public
*/


/*
    Constructor
    By default, the visibility of a constructor is set to public. But you can change the visibility of the constructor as per your choice.You need to add an explicit constructor keyword.

    class C private constructor(a: Int) { ... }
    In the above code, the visibility of the constructor is private.


    In this blog, we learned about various visibility modifiers in Kotlin i.e. private, protected, internal, and public. 
    By default, the visibility is set to public and by doing so, it can be accessed from anywhere. 
    If you declare something as private then it will be accessed from that class or file(if declared globally) only. 
    While in case of protected, the visibility is only set to that particular file.
*/

