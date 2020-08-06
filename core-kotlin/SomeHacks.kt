// make permission calls in onStart as it would cover some of the edge cases

// splash screen using rx-java https://blog.mindorks.com/how-to-implement-splash-screen-in-android

/*
    Main Thread blocking: If you are using Service then there are chances that your Main thread will be 
    blocked because Service runs on the Main thread. But, in case of IntentService, there is no involvement of the Main thread. 
    Here, the tasks are performed in the form of Queue i.e. on the First Come First Serve basis.

    Stop Service: If you are using Service, then you have to stop the Service after using it otherwise the 
    Service will be there for an infinite period of time i.e. until your phone is in normal state. 
    So, to stop a Service you have to use stopService() or stopSelf(). But in the case of IntentService, 
    there is no need of stopping the Service because the Service will be automatically stopped once the work is done.

    Interaction with the UI: If you are using IntentService, then you will find it difficult to interact 
    with the UI of the application. 
    If you want to out some result of the IntentService in your UI, then you have to take help of some Activity.

*/

// application lifecycle
/*
    For example, if you are using some data receiving and sending operation in your app and you want to manage 
    and hold the session whenever the app is running then you should handle the operations that are to be done 
    when your application will be in the background state. If your application is in the background state then 
    you should not hold the session because this will result in more resource utilization and this, in turn, 
    will use more battery and memory. So, in the onPause() state, you should release the session and in the 
    onResume() state, you should again build and session and do the rest operations.

    By doing so, your application will have a good impact on your users and your users will recommend the app 
    to their friends. So, Android application life cycle is very important to make an Android application.
*/

