package com.github.batulovandrey.yandextranslator.dagger;

import com.github.batulovandrey.yandextranslator.model.MainModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Andrey Batulov on 25/09/2017
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainModel model);
}