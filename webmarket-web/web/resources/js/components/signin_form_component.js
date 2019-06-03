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
                    return Promise.resolve(SignInFormComponent.INSTANCE);
                })
                .catch(resp => {
                    console.error('Ошибка получения данных формы для авторизации: ' + resp.data);
                    return Promise.reject(resp);
                });
        } else {
            return Promise.resolve(SignInFormComponent.INSTANCE);
        }
    }

    _listen() {
        //кнопка запрос на авторизацию
        const sign_in_form_submit = document.getElementById("_sign-in-form-submit");
        const sign_in_form_username = document.getElementById("_sign-in-form-username");
        const sign_in_form_password = document.getElementById("_sign-in-form-password");
        sign_in_form_submit.addEventListener('click',
            e => {
            var postData =
                'username='+encodeURI(sign_in_form_username.value)+'&'
                +'password='+encodeURI(sign_in_form_password.value);

            axios.post('/auth/login', postData)
                .then(function (res) {
                    console.log(res);
                    if (res != null && res.data != null && res.data.username != null) {

                        //при успешной авторизации
                        User.CURRENT = new User(res.data.username);
                        UserComponent.getInstance()
                            .then(value => {
                                value.show();
                            })
                    } else {
                        console.log('authentication failure');
                        showErrorMessageInSignInForm();
                    }
                })
                .catch(function (res) {
                    console.log("authentication failure");
                    showErrorMessageInSignInForm();
                });
        });

        //кнопка переключение на форму регистрации
        const signin_to_signup_switch_button = document.getElementById('singin-to-signup-switch-button');
        if (signin_to_signup_switch_button !== null) {
            signin_to_signup_switch_button.addEventListener('click',
                e => {
                    SignUpFormComponent.getInstance()
                    .then(value => {
                        value.show();
                    });
                });
        }
    }
}

SignInFormComponent.INSTANCE = null;

function showErrorMessageInSignInForm() {
    const sign_in_form_error = document.getElementById('_sign-in-form-error');
    sign_in_form_error.style.display = "block";
}

function hideErrorMessageInSignInForm() {
    const sign_in_form_error = document.getElementById('_sign-in-form-error');
    sign_in_form_error.style.display = "none";
}