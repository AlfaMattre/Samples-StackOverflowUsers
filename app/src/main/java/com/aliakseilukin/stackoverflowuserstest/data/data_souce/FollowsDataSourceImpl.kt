package com.aliakseilukin.stackoverflowuserstest.data.data_souce

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FollowsDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) : FollowsDataSource {

    private val _followIds = MutableStateFlow(loadFollows())

    override val followIds: Flow<Set<Int>>
        get() = _followIds.asStateFlow()

    override suspend fun follow(id: Int) {
        val ids = _followIds.value.toMutableSet()
        ids.add(id)
        saveFollow(ids)
        _followIds.value = ids
    }

    override suspend fun unfollow(id: Int) {
        val ids = _followIds.value.toMutableSet()
        ids.remove(id)
        saveFollow(ids)
        _followIds.value = ids
    }

    private fun loadFollows(): Set<Int> {
        return preferences
            .getStringSet(KEY_FOLLOW, emptySet())
            .orEmpty()
            .map { it.toInt() }
            .toSet()
    }

    private fun saveFollow(follows: Set<Int>) {
        preferences.edit {
            putStringSet(KEY_FOLLOW, follows.map { it.toString() }.toHashSet())
        }
    }

    companion object {
        private const val KEY_FOLLOW = "follows_ids"
    }
}