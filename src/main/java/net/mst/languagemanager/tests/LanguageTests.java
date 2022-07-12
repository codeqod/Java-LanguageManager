package net.mst.languagemanager.tests;

import net.mst.languagemanager.LanguageControl;
import net.mst.languagemanager.interfaces.Languages;
import net.mst.languagemanager.manager.LanguageManager;

public class LanguageTests {

    public LanguageTests(){


        LanguageManager<TestLang> abc = new LanguageManager<>("test", TestLang.class);

        System.out.println(abc.getTranslatedMessage(Languages.GERMAN, "testMessage", "/help", "ungültig"));

    }

}
