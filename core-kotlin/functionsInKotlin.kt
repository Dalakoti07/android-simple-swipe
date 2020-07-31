/*

    Lambda fun example
    val donothing :(Int)->Int={ a-> a}

    high order function ,these take functions as arguments and even can return function
    
    object myClass{
        fun passMeFunction( abc: ()->unit ){
            // this function take function named abc as argument and this function abc has no parameters and doesnot return anything 
            
            // execute func
            abc()
        }
    }

    // high order function are very useful as we can use them in case of network service like pass success and failure function to network api call

    // :: is called this operator

    // kotlin extension function
    // we using glide library and thus we can extend the imageView object to make glide call as it would avoid repetitions
    extension function provide us flexibility without making helper functions, which kotlin uses under the hood
    android team has provided lot of extenision function for andorid devs :)

    
*/
