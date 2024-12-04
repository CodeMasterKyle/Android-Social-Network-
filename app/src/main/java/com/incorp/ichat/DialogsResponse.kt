package com.incorp.ichat

// DialogsResponse.kt
data class DialogsResponse(
    val error: Boolean,
    val error_code: Int,
    val messageCreateAt: String,
    val chats: List<Chat>
)

data class Chat(
    val error: Boolean,
    val error_code: Int,
    val id: String,
    val fromUserId: String,
    val toUserId: String,
    val withUserId: String,
    val withUserUsername: String,
    val withUserFullname: String,
    val withUserPhotoUrl: String,
    val lastMessage: String,
    val newMessagesCount: String
)
