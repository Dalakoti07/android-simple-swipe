package com.example.learningdagger.di;

import com.example.learningdagger.MainActivity;
import com.example.learningdagger.car.Car;

import dagger.Component;

@Component(modules = {WheelsModule.class,DieselEngineModule.class})
public interface CarComponent {
    Car getCar();

    // making a function which would inject all the fields annotated as @Inject in MainActivity, for different Act and frag we have to create
//     different function
    void inject(MainActivity mainActivity);
}
