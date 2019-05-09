class SearchResultListComponent {
    constructor(data) {
        this._data = data;
        /**
         *
         * @type {Array<ResultShort>}
         * @private
         */
        this._items = [];
        this._listen();
    }

    show() {
        const rootElement = document.getElementById('search-result-list');
        if (rootElement !== null) {
            rootElement.innerHTML = this._data;

            const updateMaxHeight = (e) => {
                const list = document.getElementById('search-result-container');
                if (list) {
                    const topOvElem = list.offsetTop;
                    const bottomOfVisibleWindow = window.innerHeight;
                    const toBottomHeight = (bottomOfVisibleWindow - topOvElem);
                    console.log('wh: ' + window.innerHeight);
                    console.log('top off: ' + topOvElem);
                    list.style.maxHeight = toBottomHeight - 30 + 'px';
                    console.log('updateMaxHeight');
                }
            };

            window.onresize = function (event) {
                updateMaxHeight(event);
            };
            updateMaxHeight();

        } else {
            console.error('Не получилось отобразить список результатов поиска: ' +
                'отсутствует корневой элемент #search-result-list');
        }
    }

    /**
     * @returns { Promise<SearchResultListComponent> }
     */
    static getInstance() {
        if (SearchResultListComponent.INSTANCE === null) {
            console.log('Запрос на получение отображения списка результатов поиска');
            return axios.get('/html/result_list')
                .then(resp => {
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    SearchResultListComponent.INSTANCE = new SearchResultListComponent(resp.data.trim());
                    return Promise.resolve(SearchResultListComponent.INSTANCE);
                })
                .catch(resp => {
                    console.error('Ошибка получения данных отображения списка результатов поиска: ' + resp.data);
                    return Promise.reject();
                });
        } else {
            return Promise.resolve(SearchResultListComponent.INSTANCE);
        }
    }

    _listen() {
        const search_button = document.getElementById('search-button');
        if (search_button !== null) {
            search_button.addEventListener('click',
                e => {
                    SearchResultListComponent.getInstance()
                        .then(value => {
                            this._getAndShowSearchResults();
                        });
                })
        }
    }

    /**
     *
     * @private
     */
    _getAndShowSearchResults(){
        const requestText = 'text';
        axios.post('/data/test', requestText)
            .then(resp => {
                if (resp.status !== 200) {
                    console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                }
                this._parseResponseAndSaveNewItems(resp.data.data);
                const elements = this._createAndFillAndShowSearchResutItemComponent();
                const container = document.getElementById('search-result-container');
                SearchResultListComponent._replaceListWithNewResults(container, elements);
            })
            .catch(err => {
                console.error('Ошибка получения результатов поиска: ' + err);
            });
    }

    /**
     *
     * @param {Array} search_result
     * @private
     */
    _parseResponseAndSaveNewItems(search_result) {
        this._items = [];
        if (search_result) {
            for (let i = 0; i < search_result.length; i++) {
                const result_item_data = search_result[i];
                const result_item = new ResultShort();
                if (result_item_data.id) {
                    result_item.productId = result_item_data.id;
                }
                if (result_item_data.title) {
                    result_item.title = result_item_data.title;
                }
                if (result_item_data.price) {
                    result_item.price = result_item_data.price;
                }
                if (result_item_data.image) {
                    result_item.imageUrl = result_item_data.image;
                }
                this._items.push(result_item);
            }
        }
    }

    /**
     *
     * @returns {Array<HTMLSpanElement>}
     * @private
     */
    _createAndFillAndShowSearchResutItemComponent() {
        const elements = [];
        for (let i = 0; i < this._items.length; i++) {
            const result_item = this._items[i];
            const element = document.createElement('span');
            elements.push(element);
            new SearchResultItemComponent(result_item,
                element).show();
        }
        return elements;
    }

    /**
     *
     * @param {HTMLElement} container
     * @param {Array<HTMLSpanElement>} result_item_list
     * @private
     */
    static _replaceListWithNewResults(container, result_item_list) {
        while (container.firstChild) {
            container.removeChild(container.firstChild);
        }

        for (let i = 0; i < result_item_list.length; i++) {
            container.appendChild(result_item_list[i]);
        }
    }
}

SearchResultListComponent.INSTANCE = null;
