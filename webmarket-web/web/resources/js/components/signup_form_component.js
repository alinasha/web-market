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
        //кнопка запрос на регистрацию
        const sign_up_form_submit = document.getElementById("_sign-up-form-submit");
        const sign_up_form_username = document.getElementById("_sign-up-form-username");
        const sign_up_form_email = document.getElementById("_sign-up-form-email");
        const sign_up_form_password = document.getElementById("_sign-up-form-password");
        const sign_up_form_password_repeat = document.getElementById("_sign-up-form-password-repeat");
        sign_up_form_submit.addEventListener('click',
            e => {
                var postData =
                    'username='+encodeURI(sign_up_form_username.value)+'&'
                    +'email='+encodeURI(sign_up_form_email.value)+'&'
                    +'password='+encodeURI(sign_up_form_password.value)+'&'
                    +'passwordRepeat='+encodeURI(sign_up_form_password_repeat.value);

                axios.post('/auth/register', postData)
                    .then(function (res) {
                        if (res != null && res.data != null && res.data.username != null) {

                            //успешная регистрация
                            console.log("successfully registered");
                            showInfoMessageInSignUpForm('You have successfully registered.');
                            //блокируем кнопку регистрации
                            const sign_up_form_submit = document.getElementById("_sign-up-form-submit");
                            sign_up_form_submit.setAttribute("disabled", "true");
                        } else if (res != null && res.data != null && res.data.error != null) {

                            //ошибка регистрации, не правильно введенные данные
                            console.log('registration failure');
                            showErrorMessageInSignUpForm(res.data.error);
                        } else {

                            //ошибка регистрации
                            console.log('registration failure');
                            showErrorMessageInSignUpForm('Your registration attempt failed, try again.');
                        }
                    })
                    .catch(function (res) {

                        //ошибка регистрации
                        console.log('registration failure');
                        showErrorMessageInSignUpForm('Your registration attempt failed, try again.');
                    });
            });


        //кнопка переключение на форму входа
        const signup_to_signin_switch_button = document.getElementById('singup-to-signin-switch-button');
        if (signup_to_signin_switch_button !== null) {
            signup_to_signin_switch_button.addEventListener('click',
                e => {
                    SignInFormComponent.getInstance()
                    .then(value => {
                        value.show();
                    });
                });
        }
    }
}

SignUpFormComponent.INSTANCE = null;

function showInfoMessageInSignUpForm(message) {
    const sign_up_form_info_message = document.getElementById('_sign-up-form-info-message');
    sign_up_form_info_message.innerText = message;
    sign_up_form_info_message.style.color = 'green';

    const sign_up_form_info = document.getElementById('_sign-up-form-info');
    sign_up_form_info.style.display = "block";
}

function showErrorMessageInSignUpForm(message) {
    const sign_up_form_info_message = document.getElementById('_sign-up-form-info-message');
    sign_up_form_info_message.innerText = message;
    sign_up_form_info_message.style.color = 'red';

    const sign_up_form_info = document.getElementById('_sign-up-form-info');
    sign_up_form_info.style.display = "block";
}

function hiddeMessageInSignUpForm() {
    const sign_up_form_info = document.getElementById('_sign-up-form-info');
    sign_up_form_info.style.display = "none";
}