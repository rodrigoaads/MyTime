package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ActionState

interface UpdateNameAndActionUseCase {
    suspend operator fun invoke(
        id: String,
        name: String,
        actionUrl: String
    ): ActionState<Nothing>
}