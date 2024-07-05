package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.CreateItemUseCase

class CreateItemUseCaseImpl(
    private val appRepository: AppRepository
): CreateItemUseCase {
    override suspend fun invoke(
        name: String,
        actionUrl: String
    ): ActionState<Nothing> {
        return appRepository.createItem(
            name = name,
            actionUrl = actionUrl
        )
    }
}