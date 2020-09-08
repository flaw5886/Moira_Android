package com.dgsw.hackathon.moira.datasource

import com.dgsw.hackathon.moira.base.BaseDataSource
import com.dgsw.hackathon.moira.network.remote.AuthRemote
import com.dgsw.hackathon.moira.network.response.data.LoginData
import com.dgsw.hackathon.moira.request.LoginRequest
import com.dgsw.hackathon.moira.request.RegisterRequest
import io.reactivex.Single

class AuthDataSource(
    override val remote: AuthRemote,
    override val cache: Any
): BaseDataSource<AuthRemote, Any>() {

    fun login(loginRequest: LoginRequest): Single<LoginData> = remote.login(loginRequest)

    fun register(registerRequest: RegisterRequest): Single<String> = remote.register(registerRequest)
}