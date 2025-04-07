package com.lukiienko.paymentsapp.di

import com.lukiienko.paymentsapp.data.repository.ReceiptRepositoryImpl
import com.lukiienko.paymentsapp.domain.repository.ReceiptRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideReceiptRepository(client: HttpClient): ReceiptRepository {
        return ReceiptRepositoryImpl(client)
    }
}
