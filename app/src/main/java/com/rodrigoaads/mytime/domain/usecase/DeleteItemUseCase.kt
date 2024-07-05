package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ActionState

interface DeleteItemUseCase {
    suspend operator fun invoke(id: String): ActionState<Nothing>
}