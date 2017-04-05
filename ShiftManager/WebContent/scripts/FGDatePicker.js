//
// Ensure FG namespace...
//
if (!("FG" in window)) {
    FG = {}
};

/**
 * FG DatePicker wrapper
 * 
 * @author ashaikh
 */
FG.DatePicker = function(options) {
    var __this__ = this;
    var __firstTime__ = true;
    this.markedDates = {};
    this.month = options.minDate;
    var datepickOptions = {
        multiSelect : 99,
        monthsToShow : 3,
        monthsToStep : 3,
        dateFormat : "yyyy-mm-dd",
        renderer : $.extend({}, $.datepick.defaultRenderer, {
            highlightedClass : 'fgdpHighlighted',
            selectedClass : 'fgdpSelected',
            defaultClass : 'fgdpDefault',
            todayClass : 'fgdpToday'
        }),
        onShow : function(picker, inst) {
            console.log('onShow');
            if (__firstTime__ == false) {
                window.setTimeout(function() {
                    __this__.$picker.find('a.fgdp').hover(function() {
                        $(this).css('background-color', 'lime');
                    }, function() {
                        $(this).css('background-color', '');
                    });
                }, 10);
                return;
            }
            __firstTime__ = false;
            window.setTimeout(function() {
                __this__.showDate(options.minDate);
            }, 10);
        },
        onChangeMonthYear : function(year, month) {
            console.log('onChangeMonthYear');
            __this__.month = year + "-" + FG.Text.padNum(month, 2) + "-01";
            if ('minDate' in options) {
                var start = FG.Date.monthToOffset(options.minDate);
                var end = FG.Date.monthToOffset(year + "-" + month);
                var diff = (end - start) % 3;
                if (diff != 0) {
                    end = end - diff;
                    var endDate = FG.Date.offsetToMonth(end) + "-01";
                    __this__.showDate(endDate);
                }
            }
        },
        onDate : function(date, current) {
            if (!current) {
                return {};
            }
            var currentYmd = FG.Date.toYmds([ date ])[0];
            var dates;
            var classes = [];
            for ( var className in __this__.markedDates) {
                dates = __this__.markedDates[className];
                if (FG.Arrays.indexOf(dates, currentYmd) >= 0) {
                    classes.push(className);
                }
            }
            classes.push('fgdp');
            classes.push('fgdpid' + currentYmd.replace(new RegExp("-", "g"), ""));
            console.log('onDate');
            return {
                dateClass : classes.join(' ')
            };
        }
    };
    FG.Objects.copyValue(datepickOptions, options, 'prevText', 'Prev Months');
    FG.Objects.copyValue(datepickOptions, options, 'nextText', 'Next Months');
    FG.Objects.copyValue(datepickOptions, options, 'minDate');
    FG.Objects.copyValue(datepickOptions, options, 'maxDate');
    if ('onSelect' in options) {
        datepickOptions.onSelect = function(dates) {
            options.onSelect.apply(__this__, [ FG.Date.toYmds(dates) ]);
        };
    }
    this.$picker = $(options.holder).datepick(datepickOptions);
};

FG.DatePicker.prototype.setDates = function(dates) {
    this.$picker.datepick('setDate', FG.Date.toDates(dates));
};

FG.DatePicker.prototype.setMarkedDates = function(className, dates) {
    this.markedDates[className] = dates;
    this.showDate(this.month);
};

FG.DatePicker.prototype.getMarkedDates = function(className) {
    if (className in this.markedDates) {
        return this.markedDates[className];
    }
    return [];
};

FG.DatePicker.prototype.getDates = function() {
    return FG.Date.toYmds(this.$picker.datepick('getDate'));
};

FG.DatePicker.prototype.addDate = function(ymd) {
    var dates = this.getDates();
    if (FG.Arrays.indexOf(dates, ymd) < 0) {
        dates.push(ymd);
        this.setDates(dates);
    }
};

FG.DatePicker.prototype.removeDate = function(ymd) {
    var dates = this.getDates();
    var pos = FG.Arrays.indexOf(dates, ymd);
    if (pos >= 0) {
        FG.Arrays.removeAt(dates, pos);
        this.setDates(dates);
    }
};

FG.DatePicker.prototype.showDate = function(ymd) {
    var parts = ymd.split("-");
    this.$picker.datepick('showMonth', parts[0], parts[1], parts[2]);
};
