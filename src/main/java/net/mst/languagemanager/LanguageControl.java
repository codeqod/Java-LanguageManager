// Package not detected, please report project structure on CodeTogether's GitHub Issues
package net.mst.languagemanager;

import lombok.Getter;
import lombok.Setter;
import net.mst.languagemanager.manager.FileManager;
import net.mst.languagemanager.tests.LanguageTests;

public class LanguageControl {


    @Getter @Setter
    public static FileManager fileManager;

    public static void main(String[] args) {
        setFileManager(new FileManager());

        //new LanguageTests();
    }

}
