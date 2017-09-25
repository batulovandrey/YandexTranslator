package com.github.batulovandrey.yandextranslator;

import android.app.Application;

import com.github.batulovandrey.yandextranslator.dagger.AppModule;
import com.github.batulovandrey.yandextranslator.dagger.DaggerNetComponent;
import com.github.batulovandrey.yandextranslator.dagger.NetComponent;
import com.github.batulovandrey.yandextranslator.dagger.NetModule;

/**
 * @author Andrey Batulov on 25/09/2017
 */

public class TranslatorApp extends Application {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}