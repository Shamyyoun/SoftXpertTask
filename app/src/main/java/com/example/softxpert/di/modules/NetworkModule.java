package com.example.softxpert.di.modules;

import com.example.softxpert.app.Const;
import com.example.softxpert.data.datasources.remote.CarApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {
        // create http ok client for logging purposes
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);

        // create & return retrofit
        return new Retrofit.Builder()
                .baseUrl(Const.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Provides
    @Singleton
    static CarApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(CarApi.class);
    }
}
