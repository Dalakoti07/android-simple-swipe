package com.example.learningdagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.learningdagger.car.Car;
import com.example.learningdagger.di.CarComponent;
import com.example.learningdagger.di.DaggerCarComponent;
import com.example.learningdagger.di.DieselEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
//    must be public
    @Inject
    public Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // daggerCarComponent is created when we do build
        CarComponent component = DaggerCarComponent.builder()
                .dieselEngineModule(new DieselEngineModule(100))
                .build();
//        car = component.getCar(); // not using field injection, using method
//        using field injection
        component.inject(this);

        car.drive();
    }
}