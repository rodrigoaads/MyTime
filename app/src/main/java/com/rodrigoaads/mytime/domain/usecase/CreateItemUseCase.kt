package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ActionState

interface CreateItemUseCase {
    suspend operator fun invoke(
        name: String,
        actionUrl: String
    ): ActionState<Nothing>
}