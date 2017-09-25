package com.github.batulovandrey.yandextranslator.presenter;

/**
 * @author Andrey Batulov on 25/09/2017
 */

public interface MainPresenter {

    void showProgress();

    void hideProgress();

    void showError();

    void showTranslation(String translatedText);

    void processTranslation(String sourceText);
}