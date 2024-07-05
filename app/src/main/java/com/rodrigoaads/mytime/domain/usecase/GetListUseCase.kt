package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ItemModel
import kotlinx.coroutines.flow.Flow

interface GetListUseCase {
    operator fun invoke(): Flow<List<ItemModel>>
}