class AuthService {
    /**
     *
     * @param { User } user
     */
    constructor(user) {
        this._user = user;
    }

    /**
     *
     * @returns {boolean}
     */
    isLogined() {
        return this._user != null;
    }
}

AuthService.INSTANCE = null;