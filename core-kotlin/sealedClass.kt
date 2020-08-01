// https://blog.mindorks.com/learn-kotlin-sealed-classes
/*
We can also apply the use of Sealed classes in different examples in the Android Project. 
Suppose you want to display different types of Result:

sealed class Result {
     data class Success(val data: Data) : Result()
     data class Error(val exception: Exception) : Result()
}
//Consider Data as a model class which holds the result
Here, we have a data class success object which contains the success Data and a data class Error object 
which contains the state of the error.

This way, Sealed classes help us to write clean and concise code! That’s all the information about 
Sealed classes and their usage in Kotlin.

*/