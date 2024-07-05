package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.UpdateNameAndActionUseCase

class UpdateNameAndActionUseCaseImpl(
    private val appRepository: AppRepository
): UpdateNameAndActionUseCase {
    override suspend fun invoke(
        id: String,
        name: String,
        actionUrl: String
    ): ActionState<Nothing> {
        return appRepository.updateNameAndAction(
            id = id,
            name = name,
            actionUrl = actionUrl
        )
    }
}