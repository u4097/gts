package ru.panmin.gtspro.data.remote;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.realm.RealmList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.panmin.gtspro.BuildConfig;
import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.models.Answer;
import ru.panmin.gtspro.data.models.Photo;
import ru.panmin.gtspro.data.models.requests.AuthRequest;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.data.models.responses.AuthResponse;
import ru.panmin.gtspro.data.models.responses.UserInfoResponse;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.TextUtils;

public interface ApiService {


    String TAG_REQUEST_LOG = "GTSProRequest";
    String TAG_RESPONSE_LOG = "GTSProResponse";

    @POST("auth/")
    Single<AuthResponse> auth(@Body AuthRequest authRequest);

    @GET("user_info/")
    Single<UserInfoResponse> userInfo();

    @GET("address_program/")
    Single<AddressProgramResponse> addressProgram();

    @GET("address_program_without_sku/")
    Single<AddressProgramResponse> addressProgramWithoutSku();

    class Creator {

        public static ApiService newApiService(Context context) {
            PreferencesHelper preferencesHelper = new PreferencesHelper(context);
            Gson gson = new GsonBuilder()
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

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.MINUTES)
                    .writeTimeout(30, TimeUnit.MINUTES);

            okBuilder.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                String token = preferencesHelper.getToken();
                if (!TextUtils.isEmpty(token)) {
                    requestBuilder.header(
                            Constants.HEADER_AUTHORIZATION,
                            String.format("%s %s", Constants.TOKEN_TYPE_BEARER, token)
                    );
                }
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            okBuilder.addInterceptor(new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request(TAG_REQUEST_LOG)
                    .response(TAG_RESPONSE_LOG)
                    .build());

            okBuilder.addInterceptor(new ChuckInterceptor(context));

            @SuppressLint("DefaultLocale") Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL_REST)
                    .client(okBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }

    }

}