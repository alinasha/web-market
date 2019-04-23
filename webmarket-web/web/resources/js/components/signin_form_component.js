class SignInFormComponent {
    constructor(data) {
        this._data = data;
    }

    show() {
        const rootElement = document.getElementById('side-view');
        if (rootElement !== null) {
            rootElement.innerHTML = this._data;
            this._listen();
        } else {
            console.error('Не получилось отобразить форму авторизации: отсутствует корневой элемент #side-view');
        }
    }

    /**
     * @returns { Promise<SignInFormComponent> }
     */
    static getInstance() {
        if (SignInFormComponent.INSTANCE === null) {
            console.log('Запрос на получение формы авторизации');
            return axios.get('/html/signin_form')
                .then(resp => {
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    SignInFormComponent.INSTANCE = new SignInFormComponent(resp.data.trim());
                    return SignInFormComponent.INSTANCE;
                })
                .catch(resp => {
                    console.error('Ошибка получения данных формы для авторизации: ' + resp.data);
                    return null;
                });
        } else {
            return Promise.resolve(SignInFormComponent.INSTANCE);
        }
    }

    _listen() {
        const signin_to_signup_switch_button = document.getElementById('singin-to-signup-switch-button');
        if (signin_to_signup_switch_button !== null) {
            signin_to_signup_switch_button.addEventListener('click',
                e => {
                SignUpFormComponent.getInstance()
                    .then(value => {
                        value.show();
                    });
                })
        }
    }
}

SignInFormComponent.INSTANCE = null;