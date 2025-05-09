package org.sopt.at

import org.sopt.at.service.AuthService
import org.sopt.at.service.UserService

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }

    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
}