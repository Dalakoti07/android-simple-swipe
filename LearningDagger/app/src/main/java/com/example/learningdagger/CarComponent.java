package com.example.learningdagger;

import dagger.Component;

@Component(modules = WheelsModule.class)
public interface CarComponent {
    Car getCar();

    // making a function which would inject all the fields annotated as @Inject in MainActivity, for different Act and frag we have to create
//     different function
    void inject(MainActivity mainActivity);
}
