package com.pusilkom.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by phai on 3/2/16.
 */
@Component
public class I18nUtil {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    public String getMsg(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
