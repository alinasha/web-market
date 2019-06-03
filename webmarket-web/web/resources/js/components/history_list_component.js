class HistoryListComponent {
    constructor(data) {
        this._data = data;
    }

    show() {
        HistoryListComponent.loadData().then(value => {
            const search_detailed_view = document.getElementById('history-detailed-view');
            if (search_detailed_view != null) {
                //показать разметку
                search_detailed_view.innerHTML = this._data;

                //set data
                const history_list = document.getElementById("history-list");
                for (var i = 0; i < value.length; i++) {
                    var html_item = '<table class="history-item">';
                    html_item +=    '  <tr>';
                    html_item +=    '    <td class="history-item__article-image-url">';
                    html_item +=    '      <div class="history-item__id" style="display: none">'+value[i].id+'</div>';
                    html_item +=    '      <div class="history-item__article-id" style="display: none">'+value[i].article_id+'</div>';
                    html_item +=    '      <img src="'+value[i].article_image_url+'" style="height: 80px; margin: 0 auto; overflow: hidden;"/>';
                    html_item +=    '    </td>';
                    html_item +=    '    <td>';
                    html_item +=    '      <table class="table-center">';
                    html_item +=    '        <tr>';
                    html_item +=    '          <td>';
                    html_item +=    '            <div class="history-item__article-title"><a href="'+value[i].article_product_url+'">'+value[i].article_title+'</a></div>';
                    html_item +=    '          </td>';
                    html_item +=    '        </tr>';
                    html_item +=    '        <tr>';
                    html_item +=    '          <td>';
                    html_item +=    '            <div class="history-item__article-price">'+value[i].article_price+'</div>';
                    html_item +=    '          </td>';
                    html_item +=    '        </tr>';
                    html_item +=    '      </table>';
                    html_item +=    '    </td>';
                    html_item +=    '    <td>';
                    html_item +=    '      <div class="history-item__last-viewed">'+value[i].lastViewed+'</div>';
                    html_item +=    '    </td>';
                    html_item +=    '  </tr>';
                    html_item +=    '</table>';
                    history_list.innerHTML += html_item;
                }

                this._listener();
            } else {
                console.error('Не получилось отобразить историю просмотров о товаре: отсутствует корневой элемент #history-detailed-view');
            }
        });
    }


    /**
     * @returns { Promise<HistoryListComponent> }
     */
    static getInstance() {
        if (HistoryListComponent.INSTANCE === null) {
            console.log('Запрос на получение разметки для истории');
            return axios.get('/html/history_view')
                .then(resp=>{
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    HistoryListComponent.INSTANCE = new HistoryListComponent(resp.data.trim());
                    return Promise.resolve(HistoryListComponent.INSTANCE);
                })
                .catch(resp=>{
                    console.error('Ошибка получения разметки для истории: ' + resp.data)
                    return Promise.reject();
                });
        } else {
            return Promise.resolve(HistoryListComponent.INSTANCE);
        }
    }

    /**
     *
     * @returns {Promise<string>}
     */
    static loadData() {
        console.log('Запрос на получение данных истории');
        return axios.get('/data/user_history')
            .then(resp => {
                if (resp.status !== 200) {
                    console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                }
                return Promise.resolve(resp.data);
            })
            .catch(resp => {
                console.error('Ошибка получения истории: ' + resp.data);
                return Promise.reject();
            });
    }

    _listener() {

    }
}

HistoryListComponent.INSTANCE = null;