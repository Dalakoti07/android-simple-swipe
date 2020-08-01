// credits https://blog.mindorks.com/using-scoped-functions-in-kotlin-let-run-with-also-apply
// https://blog.mindorks.com/learn-kotlin-apply-vs-with


// some scope function are let,apply, also, with
// these function run in context with object and object can be refered via this or it

/*
    // when using
    let and also reference is done via help of 'it'
    in run and appy reference is done via help of 'this'

    // let and 'return' return result
    // also and apply return same object


    ________________________________________
    |                    |  it   | this   |
    |return  result      |  let  | run    |
    |return same object  |  also | apply  |
    ________________________________________

*/

/*
    LET
    // using let ,we have following usecase
    // we call chains and we want to print the results
    people.map(it.length).filter(it->3).let{
        println(it)
    }

    if we wan to check that object is not null and then perform action on it
    // old way 
    if(value!=null)
        print()
    // using let
    value?.let{
        print()
        or perform some other operations
    }
*/

/*
    RUN
    we use run when we want to initialise object and perform some operations on it

    Person(name,nickName){
        val address:String
        getAddress(){

        }
    }

    val saurabhAddress=Person("saurabh","sd").run{
        this.address="sector 10A,guragaon,haryana ,india"
        this.getAddress()
    }

    // thus we would get the address

*/

/*
    APPLY
    Person(name,nickName){
        val address:String
        val hobbies:String
        getAddress(){

        }
    }

    initialise the object

    // old school way
    heman= Person("heman","he")
    heman.address="NY"
    heman.hobbies="fly"
    print(heman)

    // using apply
    heman=Person("heman","he").apply{
        this.address="NY"
        this.hobbies="fly"
    }

    // we can remove this context as, when using apply context is clear
    
    // we could not use run because it  returns new object but we want the same object thats why this
    // run return last fun, wheras apply return context object

*/

/*
    ALSO
    used when we want to perform some of the side effects like consider following usecase
    names=ListOf("sauranj","sahil","shayanaya")
    val results=names.map{it.length}.filter{it>5}
    // results would be [7,9]
    but if we want to debug the intermediate list then
    
    old school way is
    break the chain and then create two variables and log them

    // use also
    name.map{
        it.length
    }.also{
        print(it)
    }.filter{
        it>3
    }
*/

// foreach
// listOfMindOrks.forEach {
//     Log.d(TAG,it)
//  }