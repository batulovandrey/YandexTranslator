package com.github.batulovandrey.yandextranslator.model;

import android.support.annotation.NonNull;

import com.github.batulovandrey.yandextranslator.ApiClient;
import com.github.batulovandrey.yandextranslator.bean.TranslationBean;
import com.github.batulovandrey.yandextranslator.presenter.MainPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andrey Batulov on 25/09/2017
 */

public class MainModel {

    private static final String KEY = "trnsl.1.1.20170722T192921Z.510179ac2d7a8c00.1056d584674f2d7b41216adb06ba9c56c9ab36a1";
    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private final MainPresenter mMainPresenter;
    private ApiClient mApiClient;

    public MainModel(MainPresenter mainPresenter, ApiClient apiClient) {
        mMainPresenter = mainPresenter;
        mApiClient = apiClient;
    }

    public void processTranslation(String sourceText) {
        mMainPresenter.showProgress();
        Call<TranslationBean> call = mApiClient.getTranslationService()
                .getTranslation(KEY, sourceText, LANG, FORMAT, OPTIONS);
        call.enqueue(new Callback<TranslationBean>() {
            @Override
            public void onResponse(@NonNull Call<TranslationBean> call, @NonNull Response<TranslationBean> response) {
                if (response.isSuccessful()) {
                    TranslationBean bean = response.body();
                    if (bean != null) {
                        List<String> translations = bean.getTranslationList();
                        if (!translations.isEmpty()) {
                            String translation = translations.get(0);
                            mMainPresenter.hideProgress();
                            mMainPresenter.showTranslation(translation);
                        }
                    } else {
                        mMainPresenter.showError();
                    }
                }
            }

            @Override
            public void onFailure(Call<TranslationBean> call, Throwable t) {
                mMainPresenter.hideProgress();
                mMainPresenter.showError();
            }
        });
    }
}