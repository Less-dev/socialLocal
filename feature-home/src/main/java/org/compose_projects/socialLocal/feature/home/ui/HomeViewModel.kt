/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.compose_projects.socialLocal.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.compose_projects.socialLocal.core.data.data.UserProvider
import org.compose_projects.socialLocal.core.data.repository.UserRepository
import javax.inject.Inject
import org.compose_projects.socialLocal.feature.home.ui.HomeUiState.Loading
import org.compose_projects.socialLocal.feature.home.ui.HomeUiState.Error
import org.compose_projects.socialLocal.feature.home.ui.HomeUiState.Success

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {


    /*
        val uiState: StateFlow<HomeUiState> = userRepository
        .user.map<List<String>, HomeUiState> { Success(data = it) }
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addUsers(name: String) {
        viewModelScope.launch {
            usersRepository.add(name)
        }
    }
     */

    val uiState: StateFlow<HomeUiState> = userRepository
        .user.map<List<UserProvider>, HomeUiState> { Success(data = it) }
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun insertUser(iAm: Boolean, isFriend: Boolean, dataChatID: Int) =
        viewModelScope.launch {
            userRepository.insert(
                userProvider = UserProvider(
                    userID = 0,
                    iAm = iAm,
                    isFriend = isFriend,
                    dataChatID = dataChatID
                )
            )
        }

    fun deleteUser(iAm: Boolean, isFriend: Boolean, dataChatID: Int) =
        viewModelScope.launch {
            userRepository.delete(
                userProvider = UserProvider(
                    userID = 0,
                    iAm = iAm,
                    isFriend = isFriend,
                    dataChatID = dataChatID
                )
            )
        }

    fun updateUser(userID: Int, iAm: Boolean, isFriend: Boolean, dataChatID: Int) =
        viewModelScope.launch {
            userRepository.update(
                userProvider = UserProvider(
                    userID = userID,
                    iAm = iAm,
                    isFriend = isFriend,
                    dataChatID = dataChatID
                )
            )
        }


}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val throwable: Throwable) : HomeUiState
    data class Success(val data: List<UserProvider>) : HomeUiState
}
