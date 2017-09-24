package com.github.batulovandrey.yandextranslator.bean;

import org.junit.Test;

import java.util.Collections;

/**
 * Test for {@link TranslationBean}
 *
 * @author Andrey Batulov on 25/09/2017
 */
public class TranslationBeanTest extends BaseJsonParserTest {

    private static final String TEST_FILE = "translation.json";

    @Test
    public void testParseObject() {
        testParse(TEST_FILE, getTranslationBean(), TranslationBean.class);
    }

    private TranslationBean getTranslationBean() {
        return new TranslationBean(200, "en-ru", Collections.singletonList("Здравствуй, Мир!"));
    }
}