package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.entity.ItemModel

interface GetItemByIdUseCase {
    suspend operator fun invoke(id: String): ActionState<ItemModel>
}