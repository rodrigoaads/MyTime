package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.usecase.GetDateUseCase

class GetDateUseCaseImpl(
    private val appRepository: AppRepository
): GetDateUseCase {
    override fun invoke() = appRepository.getDate()
}