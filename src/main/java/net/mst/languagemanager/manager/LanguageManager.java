package net.mst.languagemanager.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.mst.languagemanager.LanguageControl;
import net.mst.languagemanager.interfaces.ILanguageFile;
import net.mst.languagemanager.interfaces.Languages;
import net.mst.languagemanager.objects.KeyValuePair;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager<T extends ILanguageFile> {

	//private _LanguageFile file;

	/**
	 * Key: Test
	 * 	-> de
	 * 	-> en
	 * 	-> tk
	 * @param <T>
	 */


	String category;
	HashMap<Languages, KeyValuePair<T, File>> languageFiles;

	Class<? extends ILanguageFile> aClass;

	public LanguageManager(String category, Class<T> clazz) {
		this.languageFiles = new HashMap<>();
		this.aClass = clazz;
		this.category = category;

		//this.file = _LanguageFile;
		loadLanguages();

	}


	public void loadLanguages(){

		HashMap<Languages, File> languageFiles = new HashMap<>();
		try {
			languageFiles = LanguageControl.getFileManager().loadLanguageFiles(category, this.aClass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		BufferedReader bufferedReader = null;


		for(Map.Entry<Languages, File> entry : languageFiles.entrySet()) {

			try {

				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(entry.getValue())));

				T langfile = (T) gson.fromJson(bufferedReader, aClass);

				this.languageFiles.put(entry.getKey(), new KeyValuePair<>(langfile, entry.getValue()));
				LanguageControl.getFileManager().saveLanguageFile(langfile, entry.getValue());

			} catch (Exception e) {
				e.printStackTrace();
			}


		}

	}


	public T getTranslatedMessage(Languages languages){
		return this.languageFiles.get(languages).getKey();
	}

	public String getTranslatedMessage(Languages languages, String key, String... variables) {

		String untranslated = null;
		try{
			untranslated = this.getKeyByProperty(key, languages);
		}catch (NoSuchFieldException | IllegalAccessException | SecurityException exception) {}

		for(int i = 0; i < variables.length; i++) {
			untranslated = untranslated.replace("{" + i + "}", variables[i]);
		}

		return untranslated;
	}

	private String getKeyByProperty(String key, Languages languages) throws NoSuchFieldException, IllegalAccessException {

		ILanguageFile languageFile = this.languageFiles.get(languages).getKey();


		Class<?> clazz = languageFile.getClass();

		Field field = clazz.getDeclaredField(key);
		field.setAccessible(true);

		return (String) field.get(languageFile);

	}

	public void addString(String key, Languages language) {



	}
	
}
