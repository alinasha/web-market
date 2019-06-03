(function () {
    //проверка авторизации пользователя
    axios.get('/auth/ping').then(function (response) {
        if (response.data != null && response.data.username != null && response.data.username.length > 0) {
            //если сервер прислал имя пользователя то показываем панель с информацией о пользователе
            User.CURRENT = new User(response.data.username);
            AuthService.INSTANCE = new AuthService(User.CURRENT);
            UserComponent.getInstance()
                .then(value => {
                    value.show();
                });
        } else {

            //если сервер не дал ответа, то показываем форму входа
            SignInFormComponent.getInstance()
                .then(value => {
                    value.show();
                });
        }
    }).catch(function (response) {

        //если сервер ответил, но прислал не логин пользователя то показываем форму входа
        SignInFormComponent.getInstance()
            .then(value => {
                value.show();
            });
    });

    SearchResultListComponent.getInstance()
        .then(value => {
            value.show();
        });
})();
