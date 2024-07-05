package com.rodrigoaads.mytime.di

import com.rodrigoaads.data.AppDatabase
import com.rodrigoaads.data.model.TimeDbModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

object DataModule {
    private fun provideRealm(): Realm {
        val config = RealmConfiguration.create(schema = setOf(TimeDbModel::class))
        return Realm.open(config)
    }

    val dataModule = module {
        single<Realm> { provideRealm() }
        single { AppDatabase(get()) }
    }
}

