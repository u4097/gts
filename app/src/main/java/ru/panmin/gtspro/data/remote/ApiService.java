package ru.panmin.gtspro.data.remote;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
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
import ru.panmin.gtspro.data.models.requests.AuthRequest;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.data.models.responses.AuthResponse;
import ru.panmin.gtspro.data.models.responses.UserInfoResponse;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.GsonUtils;
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
            Gson gson = GsonUtils.getGson();

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