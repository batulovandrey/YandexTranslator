package com.github.batulovandrey.yandextranslator.model;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.github.batulovandrey.yandextranslator.TranslationService;
import com.github.batulovandrey.yandextranslator.bean.TranslationBean;
import com.github.batulovandrey.yandextranslator.TranslatorApp;
import com.github.batulovandrey.yandextranslator.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andrey Batulov on 25/09/2017
 */

public class MainModel {

    private static final String KEY = "trnsl.1.1.20170722T192921Z.510179ac2d7a8c00.1056d584674f2d7b41216adb06ba9c56c9ab36a1";
    private static final String LANG_DEFAULT = "en-ru";
    private static final String LANG_RU_EN = "ru-en";
    private static final String LANG = "lang";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private final MainPresenter mMainPresenter;

    @Inject
    TranslationService mService;

    @Inject
    SharedPreferences mPreferences;

    public MainModel(MainPresenter mainPresenter) {
        TranslatorApp.getNetComponent().inject(this);
        mMainPresenter = mainPresenter;
    }

    public void setLanguage(boolean isEnRu) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(LANG, isEnRu);
        editor.apply();
        mMainPresenter.showDefaultLanguageChecked(isEnRu);
    }

    public void processTranslation(String sourceText) {
        mMainPresenter.showProgress();
        String lang = isDefaultLanguageChecked() ? LANG_DEFAULT : LANG_RU_EN;
        Call<TranslationBean> call = mService.getTranslation(KEY, sourceText, lang, FORMAT, OPTIONS);
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

    public boolean isDefaultLanguageChecked() {
        boolean isDefaultChecked = mPreferences.getBoolean(LANG, true);
        mMainPresenter.changeLanguage(isDefaultChecked);
        return isDefaultChecked;
    }
}