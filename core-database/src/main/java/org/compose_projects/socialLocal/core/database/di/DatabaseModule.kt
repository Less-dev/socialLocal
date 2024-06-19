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

package org.compose_projects.socialLocal.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.compose_projects.socialLocal.core.database.AppDatabase
import org.compose_projects.socialLocal.core.database.CONSTANTS.databaseName
import org.compose_projects.socialLocal.core.database.daos.ChatDao
import org.compose_projects.socialLocal.core.database.daos.DataChatDao
import org.compose_projects.socialLocal.core.database.daos.MultimediaDao
import org.compose_projects.socialLocal.core.database.daos.ProfileDao
import org.compose_projects.socialLocal.core.database.daos.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            databaseName
        ).build()


    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()


    @Provides
    fun provideMultimediaDao(appDatabase: AppDatabase): MultimediaDao =
        appDatabase.multimediaDao()


    @Provides
    fun provideDataChatDao(appDatabase: AppDatabase): DataChatDao =
        appDatabase.dataChatDao()


    @Provides
    fun provideChatDao(appDatabase: AppDatabase): ChatDao =
        appDatabase.chatDao()

    @Provides
    fun provideProfileDao(appDatabase: AppDatabase): ProfileDao =
        appDatabase.profileDao()



}
