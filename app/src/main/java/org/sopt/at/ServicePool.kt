package org.sopt.at

import org.sopt.at.service.AuthService

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}