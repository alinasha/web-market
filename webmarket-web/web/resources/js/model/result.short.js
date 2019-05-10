class ResultShort {
    constructor() {
        this._productId = '';
        this._title = '';
        this._imageUrl = '';
        this._price = '';
    }

    get productId() {
        return this._productId;
    }

    set productId(value) {
        this._productId = value;
    }

    get title() {
        return this._title;
    }

    set title(value) {
        this._title = value;
    }

    get imageUrl() {
        return this._imageUrl;
    }

    set imageUrl(value) {
        this._imageUrl = value;
    }

    get price() {
        return this._price;
    }

    set price(value) {
        this._price = value;
    }
}

