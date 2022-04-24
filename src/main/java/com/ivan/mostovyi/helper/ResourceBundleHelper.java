package com.ivan.mostovyi.helper;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ResourceBundleHelper {

    public String getLocalizedString(String key, String locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", new Locale(locale));
        return resourceBundle.getString(key);
    }

}
