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
        return false;
    }
}