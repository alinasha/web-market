class SignUpFormComponent {
    constructor(data) {
        this._data = data;
    }

    show() {
        const rootElement = document.getElementById('side-view');
        if (rootElement !== null) {
            rootElement.innerHTML = this._data;
            this._listen();
        } else {
            console.error('Не получилось отобразить форму регистрации: отсутствует корневой элемент #side-view');
        }
    }

    /**
     * @returns { Promise<SignUpFormComponent> }
     */
    static getInstance() {
        if (SignUpFormComponent.INSTANCE === null) {
            console.log('Запрос на получение формы регистрации');
            return axios.get('/html/signup_form')
                .then(resp => {
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    SignUpFormComponent.INSTANCE = new SignUpFormComponent(resp.data.trim());
                    return Promise.resolve(SignUpFormComponent.INSTANCE);
                })
                .catch(resp => {
                    console.error('Ошибка получения данных формы для регистрации: ' + resp.data);
                    return Promise.reject();
                });
        } else {
            return Promise.resolve(SignUpFormComponent.INSTANCE);
        }
    }

    _listen() {
        const signup_to_signin_switch_button = document.getElementById('singup-to-signin-switch-button');
        if (signup_to_signin_switch_button !== null) {
            signup_to_signin_switch_button.addEventListener('click',
                e => {
                    SignInFormComponent.getInstance()
                    .then(value => {
                        value.show();
                    });
                })
        }
    }
}

SignUpFormComponent.INSTANCE = null;
