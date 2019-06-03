class ResultDetailed {
    constructor() {
        this._productUrl = '';
        this._resultShort = new ResultShort();
        this._description = '';
        this._imageUrls = [];
        this._parameters = [];
    }


    get productUrl() {
        return this._productUrl;
    }

    set productUrl(value) {
        this._productUrl = value;
    }

    get resultShort() {
        return this._resultShort;
    }

    set resultShort(value) {
        this._resultShort = value;
    }

    get description() {
        return this._description;
    }

    set description(value) {
        this._description = value;
    }

    get imageUrls() {
        return this._imageUrls;
    }

    set imageUrls(value) {
        this._imageUrls = value;
    }

    get parameters() {
        return this._parameters;
    }

    set parameters(value) {
        this._parameters = value;
    }
}

