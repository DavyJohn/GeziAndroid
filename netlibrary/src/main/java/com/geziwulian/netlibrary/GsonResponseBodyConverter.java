package com.geziwulian.netlibrary;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by yyx on 16/3/2.
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        try {
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            return gson.fromJson(jsonReader, type);
            // return gson.fromJson(reader, type);
        } finally {
            closeQuietly(reader);
        }
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }
}

