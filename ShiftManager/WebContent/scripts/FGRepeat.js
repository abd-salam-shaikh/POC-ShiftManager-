if (!('FG' in window)) {
    FG = {

    };
}

FG.Repeat = function(options) {
    this.$holder = $(options.holder);
    this.$template = this.$holder.find(options.templateSelector);
    if ('itemRemoverSelector' in options) {
        this.itemRemoverSelector = options.itemRemoverSelector;
    }
    if ('onAdd' in options) {
        this.onAdd = options.onAdd;
    }
    if ('onRemove' in options) {
        this.onRemove = options.onRemove;
    }
    if ('data' in options) {
        for ( var i = 0; i < options.data.length; i++) {
            this.add(options.data[i]);
        }
    }
};

FG.Repeat.prototype.add = function(item) {
    var el = this.$template[0].cloneNode(true);
    var $el = $(el);
    $el.removeClass('template').addClass('item');
    var __this__ = this;
    $el.find(this.itemRemoverSelector).bind('click', function() {
        __this__.remove($(this));
        return false;
    });
    if ('onAdd' in this) {
        this.onAdd($el, item);
    }
    this.$holder.append($el);
};

FG.Repeat.prototype.remove = function(el) {
    var $el = $(el);
    if ($el.hasClass('item') == false) {
        $el = $el.parents('.item');
    }
    if ($el.length > 0) {
        if ('onRemove' in this) {
            this.onRemove($el);
        }
        $el.remove();
    }
};

FG.Repeat.prototype.clear = function() {
    var __this__ = this;
    this.$holder.find('>.item').each(function() {
        __this__.remove($(this));
    });
};