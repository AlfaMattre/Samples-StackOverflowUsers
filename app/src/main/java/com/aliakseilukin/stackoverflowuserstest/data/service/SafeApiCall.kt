package com.aliakseilukin.stackoverflowuserstest.data.service

import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultState<T> {
    return try {
        ResultState.Success(apiCall())
    } catch (e: CancellationException) {
        throw e
    } catch (e: IOException) {
        ResultState.Error(e.message.orEmpty())
    } catch (e: HttpException) {
        ResultState.Error(e.message.orEmpty())
    } catch (e: Exception) {
        ResultState.Error(e.message.orEmpty())
    }
}