package net.mst.languagemanager.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import net.mst.languagemanager.interfaces.ILanguageFile;
import net.mst.languagemanager.interfaces.Languages;
import net.mst.languagemanager.objects.ConfigFile;
import net.mst.languagemanager.objects.JsonConfig;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.HashMap;

public class FileManager {


    ConfigFile config;

    File configFile;


    Gson gson;

    @Getter @Setter
    public String languageDirectory = "./languages/";

    public FileManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }






    public <T extends ILanguageFile> HashMap<Languages, File> loadLanguageFiles(String category, Class<? extends ILanguageFile> clazz) throws Exception {



        File languageDir = new File("./"+languageDirectory+"/"+category);

        if(!languageDir.exists()) {

            languageDir.mkdirs();
            FileWriter fileWriter;
            for(Languages languages : Languages.values()) {

                File file = new File(languageDir.getAbsolutePath(), languages.name()+".json");

                file.createNewFile();

                fileWriter = new FileWriter(file);
                fileWriter.write(gson.toJson(clazz.newInstance()));
                fileWriter.flush();
                fileWriter.close();


            }
        }

        HashMap<Languages, File> langFiles = new HashMap<>();

        BufferedReader reader = null;
        for(final File langFile : languageDir.listFiles()) {

            String language = FilenameUtils.removeExtension(langFile.getName());


            langFiles.put(Languages.valueOf(language.toUpperCase()), langFile);
        }

        return langFiles;
    }

    public void saveLanguageFile(ILanguageFile languageFile, File file) throws IOException {


        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(languageFile));
        fileWriter.flush();
        fileWriter.close();


    }




}
