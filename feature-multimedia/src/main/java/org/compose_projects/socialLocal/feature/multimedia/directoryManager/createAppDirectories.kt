package org.compose_projects.socialLocal.feature.multimedia.directoryManager

import android.content.Context
import android.os.Environment
import java.io.File

fun createAppDirectories(
    context: Context,
    media: media
) {
    val baseDir = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
        media.appName
    )

    if (!baseDir.exists()) {
        baseDir.mkdirs()
    }

    val imagesDir = File(baseDir, media.directoryImages)
    val videosDir = File(baseDir, media.directoryVideos)
    val audioDir = File(baseDir, media.directoryDocuments)
    val documentsDir = File(baseDir, media.directoryAudios)

    if (!imagesDir.exists()) imagesDir.mkdirs()
    if (!videosDir.exists()) videosDir.mkdirs()
    if (!audioDir.exists()) audioDir.mkdirs()
    if (!documentsDir.exists()) documentsDir.mkdirs()

    createChatDirectories(
        parentDir = imagesDir,
        cg = media.chatGlobal,
        ci = media.chatInbox
    )
    createChatDirectories(
        parentDir = videosDir,
        cg = media.chatGlobal,
        ci = media.chatInbox
    )
    createChatDirectories(
        parentDir = audioDir,
        cg = media.chatGlobal,
        ci = media.chatInbox
    )
    createChatDirectories(
        parentDir = documentsDir,
        cg = media.chatGlobal,
        ci = media.chatInbox
    )

}


private fun createChatDirectories(parentDir: File, cg: String, ci: String) {
    val chatGlobal = File(parentDir, cg)
    val chatInbox = File(parentDir, ci)

    if (!chatGlobal.exists()) chatGlobal.mkdirs()
    if (!chatInbox.exists()) chatInbox.mkdirs()
}