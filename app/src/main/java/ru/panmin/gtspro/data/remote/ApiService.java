package ru.panmin.gtspro.data.remote;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.realm.RealmList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.models.Sku;
import ru.panmin.gtspro.data.models.requests.AuthRequest;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.data.models.responses.AuthResponse;
import ru.panmin.gtspro.data.models.responses.UserInfoResponse;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.GsonUtils;
import ru.panmin.gtspro.utils.TextUtils;

public interface ApiService {

    @POST("auth/")
    Single<AuthResponse> auth(@Body AuthRequest authRequest);

    @GET("user_info/")
    Single<UserInfoResponse> userInfo();

    @GET("address_program/")
    Single<AddressProgramResponse> addressProgram();

    @GET("address_program_without_sku/")
    Single<AddressProgramResponse> addressProgramWithoutSku();

    @GET("address_program/sku/{trade_point_id}/")
    Single<RealmList<Sku>> skuByTradePointId(
            @Path("trade_point_id") String tradePointId
    );

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