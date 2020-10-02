package com.example.learningdagger.di;

import com.example.learningdagger.car.DieselEngine;
import com.example.learningdagger.car.Engine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DieselEngineModule {
    @Binds
    abstract Engine bindEngine(DieselEngine engine);
}
