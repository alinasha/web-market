class UserComponent {
    constructor(data) {
        this._data = data;
    }

    show() {
        const rootElement = document.getElementById('side-view');
        if (rootElement !== null) {
            rootElement.innerHTML = this._data;

            const userNameLabel = document.getElementById('user-name-label');
            userNameLabel.innerText = User.CURRENT.username;

            this._listen();
        } else {
            console.error('Не получилось отобразить данные пользователя: отсутствует корневой элемент #side-view');
        }
    }

    /**
     * @returns { Promise<UserComponent> }
     */
    static getInstance() {
        if (UserComponent.INSTANCE === null) {
            console.log('Запрос на получение данных о пользователе');
            return axios.get('/html/user_info')
                .then(resp => {
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    UserComponent.INSTANCE = new UserComponent(resp.data.trim());
                    return Promise.resolve(UserComponent.INSTANCE);
                })
                .catch(resp => {
                    console.error('Ошибка получения данных о пользователе: ' + resp.data);
                    return Promise.reject(resp);
                });
        } else {
            return Promise.resolve(UserComponent.INSTANCE);
        }
    }

    _listen() {
        const user_info_logout = document.getElementById("_user-info-logout");
        user_info_logout.addEventListener('click',
            e => {
                document.location.href='/auth/logout';
            });

        const user_button_open_history = document.getElementById("_user-button-open-history");
        user_button_open_history.addEventListener('click',
            e => {
                const history_detailed_view = document.getElementById('history-detailed-view');
                if (history_detailed_view.style.display === 'none') {
                    //показать историю
                    history_detailed_view.style.display = 'block';

                    //теперь это кнопка закрыть истюрию
                    const user_button_open_history = document.getElementById('_user-button-open-history');
                    user_button_open_history.value = 'Close history';

                    //спрятать детальный список о товаре
                    const search_detailed_view = document.getElementById('search-detailed-view');
                    search_detailed_view.style.display = 'none';

                    //спрятать список товаров
                    const search_result_list = document.getElementById('search-result-list');
                    search_result_list.style.display = 'none';

                    HistoryListComponent.getInstance().then(value => {
                        value.show();
                    });
                } else {
                    //спрятать историю
                    history_detailed_view.style.display = 'none';

                    //теперь это кнопка открыть истюрию
                    const user_button_open_history = document.getElementById('_user-button-open-history');
                    user_button_open_history.value = 'Your recent history';

                    //спрятать детальный список о товаре
                    const search_detailed_view = document.getElementById('search-detailed-view');
                    search_detailed_view.style.display = 'none';

                    //показать список товаров
                    const search_result_list = document.getElementById('search-result-list');
                    search_result_list.style.display = 'block';
                }
            });
    }
}

UserComponent.INSTANCE = null;