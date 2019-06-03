class SearchResultDetailedViewComponent {
    constructor(data) {
        this._data = data;
        this._item = '';
    }

    show(productId) {
        SearchResultDetailedViewComponent.loadData(productId)
            .then(value => {
                const search_detailed_view = document.getElementById('search-detailed-view');
                if (search_detailed_view !== null) {
                    //показать разметку
                    search_detailed_view.innerHTML = this._data;

                    //set data
                    const product_web_url = document.getElementById('_product-web-url');
                    product_web_url.setAttribute('href', value.origin.productUrl);

                    const product_title = document.getElementById('_product-title');
                    product_title.innerHTML = value.origin.productInList.title;

                    const product_img_a = document.getElementById('_product-img-a');
                    product_img_a.setAttribute('href', value.origin.productInList.imageUrl);
                    const product_img = document.getElementById('_product-img');
                    product_img.setAttribute('src', value.origin.productInList.imageUrl);

                    const product_price = document.getElementById('_product-price');
                    product_price.innerHTML = value.origin.productInList.price;

                    const product_images = document.getElementById('_product-images');
                    var html_images = '';
                    for (var i = 0; i < value.origin.imageUrls.length; i++) {
                        html_images += '<a href="'+value.origin.imageUrls[i]+'"><img src="'+value.origin.imageUrls[i]+'" style="height: 100px; margin: 0 auto; overflow: hidden; padding-right: 5px"/><a/>';
                    }
                    product_images.innerHTML = html_images;

                    const product_parameters = document.getElementById('_product-parameters');
                    var html_parameters = '';
                    html_parameters += '<ul>';
                    for (var i = 0; i < value.origin.parameters.length; i++) {
                        var arr = value.origin.parameters[i].split('|');
                        html_parameters += '<li><p><span style="font-weight: bold;">'+arr[0]+' </span>'+arr[1]+'</p></li>';
                    }
                    html_parameters += '</ul>';
                    product_parameters.innerHTML = html_parameters;

                    const product_description = document.getElementById('_product-description');
                    product_description.innerHTML = value.origin.description;

                    this._listen();
                } else {
                    console.error('Не получилось отобразить детальные данные о товаре: отсутствует корневой элемент #detailed-view-item');
                }
            });
    }

    /**
     * @returns { Promise<SearchResultDetailedViewComponent> }
     */
    static getInstance() {
        if (SearchResultDetailedViewComponent.INSTANCE === null) {
            console.log('Запрос на получение разметки для детальных данных о товаре');
            return axios.get('/html/result_detailed_view')
                .then(resp => {
                    if (resp.status !== 200) {
                        console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                    }
                    SearchResultDetailedViewComponent.INSTANCE = new SearchResultDetailedViewComponent(resp.data.trim());
                    return Promise.resolve(SearchResultDetailedViewComponent.INSTANCE);
                })
                .catch(resp => {
                    console.error('Ошибка получения разметки для детальных данных о товаре: ' + resp.data);
                    return Promise.reject();
                });
        } else {
            return Promise.resolve(SearchResultDetailedViewComponent.INSTANCE);
        }
    }

    /**
     *
     * @returns {Promise<string>}
     */
    static loadData(productId) {
        console.log('Запрос на получение детальных данных о товаре (id:'+productId+')');
        return axios.get('/data/product_detailed?id='+productId)
            .then(resp => {
                if (resp.status !== 200) {
                    console.error('Статус ответа: ' + resp.status + ': ' + resp.statusText);
                }
                return Promise.resolve(resp.data);
            })
            .catch(resp => {
                console.error('Ошибка получения получения детальной информации о товаре (id:'+productId+'): '
                    + resp.data);
                return Promise.reject();
            });
    }

    _listen() {
        const detailed_view_button_back = document.getElementById('_detailed-view-button-back');
        detailed_view_button_back.addEventListener('click',
            e => {
                //спрятать детальный список о товаре
                const search_detailed_view = document.getElementById('search-detailed-view');
                search_detailed_view.style.display = 'none';

                //спрятать историю
                const history_detailed_view = document.getElementById('history-detailed-view');
                history_detailed_view.style.display = 'none';

                //показать список товаров
                const search_result_list = document.getElementById('search-result-list');
                search_result_list.style.display = 'block';
        });
    }
}

SearchResultDetailedViewComponent.INSTANCE = null;