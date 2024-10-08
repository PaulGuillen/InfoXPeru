package com.devpaul.infoxperu.feature.home.home_view.uc

import com.devpaul.infoxperu.core.extension.ResultState
import com.devpaul.infoxperu.domain.models.res.UITResponse
import com.devpaul.infoxperu.feature.home.home_view.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UITUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): ResultState<UITResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val response = repository.uit()
                ResultState.Success(response)
            }
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }
}
