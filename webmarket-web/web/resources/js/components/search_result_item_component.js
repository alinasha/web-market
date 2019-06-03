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

                const product_item = this._elem.getElementsByClassName('product-item');
                if (product_item.length !== 1) {
                    console.log('Элементов product-item найдено <' + product_item.length + '>, ожидалось <1>')
                } else {
                    product_item[0].addEventListener('click',
                        e => {
                            const search_detailed_view = document.getElementById('search-detailed-view');
                            //показать детальный список о товаре
                            search_detailed_view.style.display = 'block';
                            //спрятать список товаров
                            const search_result_list = document.getElementById('search-result-list');
                            search_result_list.style.display = 'none';
                            //спрятать историю
                            const history_detailed_view = document.getElementById('history-detailed-view');
                            history_detailed_view.style.display = 'none';

                            SearchResultDetailedViewComponent.getInstance()
                                .then(value1 => {
                                    search_detailed_view.innerHTML = value1._data;

                                    //set empty data
                                    const product_title = document.getElementById('_product-title');
                                    product_title.innerHTML = 'Please wait...';
                                    const product_img = document.getElementById('_product-img');
                                    var emptyImage = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0CAYAAADL1t+KAAAGKklEQVR42u3VMQ0AMAgAsKFjEhCJOYRhgZekNdH4nfUAgNNC6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCFzoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQhc6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNCFDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQhQ4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHShA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAhd6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIXegAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQhc6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQAQChAwBCBwCEDgBCBwCEDgAIHQAQOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIXOgAIHQAQOgAgdABA6AAgdABA6ACA0AEAoQOA0AEAoQMAQgcAhA4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAQChA4DQhQ4AQgcAhA4ACB0AEDoACB0AEDoAIHQAQOgAIHQAQOgAgNABAKEDgNABAKEDAEIHAIQOAEIHAIQOAAgdABA6AAgdABA6ACB0AEDoACB0AEDoAIDQAYCdAdw7AbYCOHpWAAAAAElFTkSuQmCC';
                                    product_img.setAttribute('src', emptyImage);
                                    const product_price = document.getElementById('_product-price');
                                    product_price.innerHTML = 'USD 0.00';
                                    const product_images = document.getElementById('_product-images');
                                    var html_images = '';
                                    for (var i = 0; i < 5; i++) {
                                        html_images += '<a href="#"><img src="'+emptyImage+'" style="height: 100px; margin: 0 auto; overflow: hidden; padding-right: 5px"/><a/>';
                                    }
                                    product_images.innerHTML = html_images;
                                    const product_parameters = document.getElementById('_product-parameters');
                                    var html_parameters = '';
                                    html_parameters += '<ul>';
                                    for (var i = 0; i < 4; i++) {
                                        html_parameters += '<li><p><span style="font-weight: bold;">Key_'+i+' </span>Value_'+i+'</p></li>';
                                    }
                                    html_parameters += '</ul>';
                                    product_parameters.innerHTML = html_parameters;
                                    const product_description = document.getElementById('_product-description');
                                    product_description.innerHTML = '...';

                                    //load detailed and show
                                    value1.show(this._item.productId);
                            });
                        });
                }

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
