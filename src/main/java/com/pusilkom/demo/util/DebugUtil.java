package com.pusilkom.demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by phai on 7/3/16.
 */
@Component
public class DebugUtil {

    private Gson gson;

    @PostConstruct
    public void initialize() {
        gson = new GsonBuilder().serializeNulls().create();
    }

    public String toString(Object object) {
        return gson.toJson(object);
    }
}
