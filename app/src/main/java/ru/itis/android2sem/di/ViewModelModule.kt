package ru.itis.android2sem.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.itis.android2sem.domain.usecases.GetCatWithTextUseCase
import ru.itis.android2sem.domain.usecases.GetRandomCatUseCase
import ru.itis.android2sem.presentation.features.catdetail.CatDetailViewModel
import ru.itis.android2sem.presentation.features.catlist.CatListViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideCatListViewModel(getRandomCatUseCase: GetRandomCatUseCase) =
        CatListViewModel(getRandomCatUseCase)

    @Provides
    @ViewModelScoped
    fun provideCatDetailViewModel(getCatWithTextUseCase: GetCatWithTextUseCase) =
        CatDetailViewModel(getCatWithTextUseCase)
}