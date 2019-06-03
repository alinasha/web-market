class User {
    constructor(username){
        this._username = username;
        this._password = '';
    }

    get username() {
        return this._username;
    }

    set username(value) {
        this._username = value;
    }

    get password() {
        return this._password;
    }

    set password(value) {
        this._password = value;
    }
}

User.CURRENT = null;