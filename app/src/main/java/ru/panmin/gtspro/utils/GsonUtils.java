package ru.panmin.gtspro.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;
import ru.panmin.gtspro.data.models.Answer;
import ru.panmin.gtspro.data.models.Photo;

public class GsonUtils {

    private GsonUtils() {
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Integer.class, new TypeAdapter<Integer>() {
                    @Override
                    public Integer read(JsonReader reader) throws IOException {
                        if (reader.peek() == JsonToken.NULL) {
                            reader.nextNull();
                            return 0;
                        }
                        String stringValue = reader.nextString();
                        try {
                            return Integer.valueOf(stringValue);
                        } catch (NumberFormatException e) {
                            return 0;
                        }
                    }

                    @Override
                    public void write(JsonWriter writer, Integer value) throws IOException {
                        if (value == null) {
                            writer.nullValue();
                            return;
                        }
                        writer.value(value);
                    }
                })
                .registerTypeAdapter(Long.class, new TypeAdapter<Long>() {
                    @Override
                    public Long read(JsonReader reader) throws IOException {
                        if (reader.peek() == JsonToken.NULL) {
                            reader.nextNull();
                            return 0L;
                        }
                        String stringValue = reader.nextString();
                        try {
                            return Long.valueOf(stringValue);
                        } catch (NumberFormatException e) {
                            return 0L;
                        }
                    }

                    @Override
                    public void write(JsonWriter writer, Long value) throws IOException {
                        if (value == null) {
                            writer.nullValue();
                            return;
                        }
                        writer.value(value);
                    }
                })
                .registerTypeAdapter(Double.class, new TypeAdapter<Double>() {
                    @Override
                    public Double read(JsonReader reader) throws IOException {
                        if (reader.peek() == JsonToken.NULL) {
                            reader.nextNull();
                            return 0.0;
                        }
                        String stringValue = reader.nextString();
                        try {
                            return Double.valueOf(stringValue);
                        } catch (NumberFormatException e) {
                            return 0.0;
                        }
                    }

                    @Override
                    public void write(JsonWriter writer, Double value) throws IOException {
                        if (value == null) {
                            writer.nullValue();
                            return;
                        }
                        writer.value(value);
                    }
                })
                .registerTypeAdapter(Boolean.class, new TypeAdapter<Boolean>() {
                    @Override
                    public Boolean read(JsonReader reader) throws IOException {
                        if (reader.peek() == JsonToken.NULL) {
                            reader.nextNull();
                            return false;
                        }
                        try {
                            return reader.nextBoolean();
                        } catch (Exception e) {
                            String stringValue = reader.nextString();
                            return stringValue.equals("true") || stringValue.equals("yes") || stringValue.equals("1");
                        }
                    }

                    @Override
                    public void write(JsonWriter writer, Boolean value) throws IOException {
                        if (value == null) {
                            writer.nullValue();
                            return;
                        }
                        writer.value(value);
                    }
                })
                .registerTypeAdapter(Answer.class, new TypeAdapter<Answer>() {
                    @Override
                    public Answer read(JsonReader reader) throws IOException {
                        Gson newGson = new Gson();
                        switch (reader.peek()) {
                            case NULL:
                                reader.nextNull();
                                return null;
                            case BEGIN_ARRAY:
                                Answer answer = null;
                                reader.beginArray();
                                switch (reader.peek()) {
                                    case NUMBER:
                                        RealmList<Integer> integerRealmList = new RealmList<>();
                                        integerRealmList.clear();
                                        while (reader.peek() != JsonToken.END_ARRAY) {
                                            integerRealmList.add(reader.nextInt());
                                        }
                                        answer = new Answer(integerRealmList);
                                        break;
                                    case BEGIN_OBJECT:
                                        RealmList<Photo> photoRealmList = new RealmList<>();
                                        photoRealmList.clear();
                                        while (reader.peek() != JsonToken.END_ARRAY) {
                                            photoRealmList.add(newGson.fromJson(reader, Photo.class));
                                        }
                                        answer = new Answer(photoRealmList);
                                        break;
                                }
                                reader.endArray();
                                return answer;
                            case STRING:
                                return new Answer(reader.nextString());
                            case NUMBER:
                                return new Answer(reader.nextInt());
                            case BOOLEAN:
                                return new Answer(reader.nextBoolean());
                            default:
                                return new Answer();
                        }
                    }

                    @Override
                    public void write(JsonWriter writer, Answer value) throws IOException {
                    }
                })
                .setDateFormat(Constants.DATE_TIME_FORMAT)
                .create();
    }

}