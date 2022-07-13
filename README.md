# LanguageManager for Java
This API allows easy and comfortable translation of messages to make your software available to more users and giving them a special experience by using their native language.

## Usage
### Initialize categories
Create a LanguageManager of a subclass of an ILanguageFile and all fields of it will be automatically added.
```
LanguageManager<T> languageManager = new LanguageManager<>("categoryName", Class<T>);
```
By default the files are saved in the current path, you can change it by using `LanguageControl.getFileManager().setLanguageDirectory("path")`
  All messages are stored in Json files in the format `LANGUAGE.json`

### Translate messages
You can use translations by including the target language, the key for the wanted message and optional variables in the format `{number}`.
```
languageManager.getTranslatedMessage(Languages.LANGUAGE, "messageKey", arguments...);
```

## Example
> 
