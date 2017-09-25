package com.github.batulovandrey.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Andrey Batulov on 25/09/2017
 */

public class ApiClient {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private static Retrofit sRetrofit;
    private static TranslationService sService = null;

    public TranslationService getTranslationService() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            sService = sRetrofit.create(TranslationService.class);
        }
        return sService;
    }
}