package com.demo.aevicedemo.di;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.demo.aevicedemo.db.AppDatabase;
import com.demo.aevicedemo.db.DbHelper;
import com.demo.aevicedemo.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {
        return new DemoVMFactory(viewModelSubComponent.build());
    }
}
