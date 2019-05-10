class SearchResultItemComponent {
    /**
     *
     * @param {ResultShort} item
     * @param {HTMLElement} element
     */
    constructor(item, element) {
        this._item = item;
        this._elem = element;
    }

    /**
     *
     */
    show() {
        SearchResultItemComponent.loadData()
            .then(value => {
                this._elem.innerHTML = value;

                const titles = this._elem.getElementsByClassName('_product-title');
                if (titles.length !== 1) {
                    console.log('Элементов _product-title найдено <' + titles.length + '>, ожидалось <1>')
                } else {
                    titles[0].innerHTML = this._item.title;
                }

                const imgs = this._elem.getElementsByClassName('_product-img');
                if (imgs.length !== 1) {
                    console.log('Элементов _product-img найдено <' + imgs.length + '>, ожидалось <1>')
                } else {
                    if (this._item.imageUrl) {
                        imgs[0].src = this._item.imageUrl;
                    } else {
                        imgs[0].alt = 'image';
                    }
                }

                const prices = this._elem.getElementsByClassName('_product-price');
                if (prices.length !== 1) {
                    console.log('Элементов _product-price найдено <' + prices.length + '>, ожидалось <1>')
                } else {
                    prices[0].innerHTML = this._item.price;
                }
            });
        this._listen();
    }

    /**
     *
     * @returns {Promise<string>}
     */
    static loadData() {
        if (SearchResultItemComponent.DATA === null) {
            console.log('Запрос на получение отображения элемента списка результатов поиска');
            return axios.get('/html/result_list_item')
                .then(resp => {
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    SearchResultItemComponent.DATA = resp.data.trim();
                    return Promise.resolve(SearchResultItemComponent.DATA);
                })
                .catch(resp => {
                    console.error('Ошибка получения данных отображения элемента списка результатов поиска: '
                        + resp.data);
                    return Promise.reject();
                });
        } else {
            return Promise.resolve(SearchResultItemComponent.DATA);
        }
    }

    _listen() {

    }
}

SearchResultItemComponent.DATA = null;
