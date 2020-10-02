package com.example.learningdagger.di;

import com.example.learningdagger.car.Engine;
import com.example.learningdagger.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {
    @Binds
    abstract Engine bindEngine(PetrolEngine engine);
}
