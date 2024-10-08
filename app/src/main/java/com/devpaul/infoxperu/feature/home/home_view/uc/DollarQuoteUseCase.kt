package com.devpaul.infoxperu.feature.home.home_view.uc

import com.devpaul.infoxperu.core.extension.ResultState
import com.devpaul.infoxperu.domain.models.res.DollarQuoteResponse
import com.devpaul.infoxperu.feature.home.home_view.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DollarQuoteUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): ResultState<DollarQuoteResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val response = repository.dollarQuote()
                ResultState.Success(response)
            }
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }
}
