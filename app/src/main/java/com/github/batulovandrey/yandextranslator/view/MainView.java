package com.github.batulovandrey.yandextranslator.view;

/**
 * @author Andrey Batulov on 25/09/2017
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void showError();

    void showTranslation(String translatedText);

    void showDefaultLanguageChecked(boolean show);

    void showConnectionError();
}