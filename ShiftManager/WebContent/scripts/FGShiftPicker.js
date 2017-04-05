if (!('FG' in window)) {
    FG = {};
}

FG.ShiftPicker = function(options) {
    var __this__ = this;
    this.$shifts = $(options.holder);
    this.data = options.data;
    this.$shifts.html(this.html);
    // This handler will ensure the selection behavior
    var onShiftViewChange = function() {
        var id = $(this).attr('id');
        __this__.shiftIndex = FG.Text.parseInt(FG.Text.substring(id, 'shift'.length, id.length));
        __this__.$shifts.find('.shift.selected').removeClass('selected');
        $(this).addClass('selected');
    };
    var $refresh = this.$shifts.find('.refreshCalendar');
    if ('refreshLabel' in options) {
        $refresh.text(options.refreshLabel);
    } else {
        $refresh.addClass('hidden');
    }
    if ('onRefreshClick' in options) {
        this.onRefreshClick = options.onRefreshClick;
    }
    $refresh.bind('click', function() {
        if ('onRefreshClick' in __this__) {
            __this__.onRefreshClick.apply(__this__, [ __this__.getSelectedShifts() ]);
        }
    });
    var $template = this.$shifts.find('.template');
    var i, $shift, html;
    var nameAndType = '';
    var shiftsById = {};
    for (i = 0; i < options.data.length; i++) {
        html = $template.html().replace(new RegExp("\\$\\{type\\}", "g"), options.type);
        $shift = $(html);
        var $shiftCheck = $shift.find('.shiftCheck');
        $shiftCheck.attr('id', 'shift' + i);
        $shiftCheck.attr('checked', options.data[i].checked);
        if (options.type == 'radio') {
            $shiftCheck.attr('name', 'radio' + this.$shifts[0].id);
        }
        shiftsById[options.data[i].id] = options.data[i];
        $shiftCheck.bind('click', onShiftViewChange);
        $shift.find("label").attr('for', $shiftCheck[0].id);
        $shift.find(".shiftId").text(options.data[i].id);
        $shift.find(".shiftName").text(options.data[i].name);
        $shift.removeClass('template');
        this.$shifts.find('.items').append($shift);
        this.shiftsById = shiftsById;
    }
};

FG.ShiftPicker.prototype.html = '' //
        + '<div class="shiftManager">' //
        + '<span class="items">' //
        + '<span class="template">'//
        + '<span class="shift">'//
        + '  <span        class="shiftId hidden"></span>' //
        + '  <input       class="shiftCheck" type="${type}"/>' //
        + '  <label><span class="shiftName"></span></label>'//
        + '</span>'//
        + '</span>'//
        + '</span>' //
        + '<div class="buttons"><span class="refreshCalendar button">label.refreshCalendar</span></div>'//
        + '</div>';

FG.ShiftPicker.prototype.getSelectedShifts = function() {
    var selected = [];
    this.$shifts.find('.shift').each(function() {
        var $this = $(this);
        if ($this.find('.shiftCheck:checked').length > 0) {
            selected.push($this.find('.shiftId').text());
        }
    });
    return selected;
};

FG.ShiftPicker.prototype.getShiftName = function(shiftId) {
    return this.shiftsById[shiftId].name;
};