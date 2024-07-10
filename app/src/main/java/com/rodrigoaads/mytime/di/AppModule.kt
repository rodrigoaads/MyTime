package com.rodrigoaads.mytime.di

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.data.AppRepositoryImpl
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeInUseCase
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeUntilUseCase
import com.rodrigoaads.mytime.domain.usecase.CreateItemUseCase
import com.rodrigoaads.mytime.domain.usecase.DeleteItemUseCase
import com.rodrigoaads.mytime.domain.usecase.GetDateUseCase
import com.rodrigoaads.mytime.domain.usecase.GetItemByIdUseCase
import com.rodrigoaads.mytime.domain.usecase.GetListUseCase
import com.rodrigoaads.mytime.domain.usecase.UpdateNameAndActionUseCase
import com.rodrigoaads.mytime.domain.usecase.impl.ChangeTimeInUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.ChangeTimeUntilUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.CreateItemUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.DeleteItemUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.GetDateUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.GetItemByIdUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.GetListUseCaseImpl
import com.rodrigoaads.mytime.domain.usecase.impl.UpdateNameAndActionUseCaseImpl
import com.rodrigoaads.mytime.ui.pages.register.viewmodel.RegisterViewModel
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.TimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory<AppRepository> { AppRepositoryImpl(get()) }
    factory<GetListUseCase> { GetListUseCaseImpl(get()) }
    factory<CreateItemUseCase> { CreateItemUseCaseImpl(get()) }
    factory<UpdateNameAndActionUseCase> { UpdateNameAndActionUseCaseImpl(get()) }
    factory<DeleteItemUseCase> { DeleteItemUseCaseImpl(get()) }
    factory<GetItemByIdUseCase> { GetItemByIdUseCaseImpl(get()) }
    factory<ChangeTimeInUseCase> { ChangeTimeInUseCaseImpl(get()) }
    factory<ChangeTimeUntilUseCase> { ChangeTimeUntilUseCaseImpl(get()) }
    factory<GetDateUseCase> { GetDateUseCaseImpl(get()) }
    viewModelOf(::RegisterViewModel)
    viewModelOf(::TimeViewModel)
}