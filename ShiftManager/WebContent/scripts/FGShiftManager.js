FG.ShiftManager = function(options) {
    var __this__ = this;
    this.url = options.url;
    this.shiftPicker = options.shiftPicker;
    this.datePicker = options.datePicker;
    this.markerClass = options.markerClass;
    this.grid = options.grid;
    this.shiftDatesById = {};
    $(options.addButton).text(options.addButtonLabel).bind('click', function() {
        var shiftManager = __this__;
        var selectedDates = shiftManager.datePicker.getDates();
        var selectedShiftDates = shiftManager.getSelectedShiftDates(selectedDates);
        var i, j, k, shiftDate, found, shift;
        var selectedShifts = shiftManager.shiftPicker.getSelectedShifts();
        var toAdd = [];
        for (i = 0; i < selectedDates.length; i++) {
            for (j = 0; j < selectedShifts.length; j++) {
                found = null;
                for (k = 0; k < selectedShiftDates.length; k++) {
                    shiftDate = selectedShiftDates[k];
                    if (shiftDate.shiftId == selectedShifts[j] && shiftDate.date == selectedDates[i]) {
                        found = shiftDate;
                        break;
                    }
                }
                if (found == null) {
                    shift = shiftManager.shiftPicker.shiftsById[selectedShifts[j]];
                    toAdd.push({
                        rowClass : 'newrow',
                        shiftId : shift.id,
                        shiftName : shift.name,
                        shiftCount : 1,
                        date : selectedDates[i],
                        startTime : shift.startTime,
                        endTime : shift.endTime
                    });
                }
            }
        }
        $('#positionsGrid').removeClass('hidden');
        for (i = 0; i < toAdd.length; i++) {
            positionsGrid.add(toAdd[i]);
        }
    });
    this.shiftPicker.onRefreshClick = function(selectedShifts) {
        __this__.refreshCalendar(selectedShifts);
    };

};

FG.ShiftManager.prototype.refreshCalendar = function(selectedShifts) {
    var __this__ = this;
    if (typeof selectedShifts == 'undefined') {
        selectedShifts = this.shiftPicker.getSelectedShifts();
    }
    $.post(this.url, {
        action : 'getShiftDates',
        shiftIds : selectedShifts.join(',')
    }, function(json) {
        var shiftManager = __this__;
        var dates = [];
        var shiftDates = [];
        for ( var i = 0; i < selectedShifts.length; i++) {
            if (selectedShifts[i] in json) {
                var items = json[selectedShifts[i]];
                for ( var j = 0; j < items.length; j++) {
                    dates.push(items[j].date);
                    items[j].rowClass = 'editrow';
                    shiftDates.push(items[j]);
                }
            }
        }
        shiftManager.shiftDatesById = json;
        shiftManager.datePicker.setDates([]);
        shiftManager.datePicker.setMarkedDates(shiftManager.markerClass, dates);
        shiftManager.grid.clear();
        for (i = 0; i < shiftDates.length; i++) {
            shiftManager.grid.add(shiftDates[i]);
        }
    }, 'json').error(function() {
        // TODO:
        alert('error!');
    });

};

FG.ShiftManager.prototype.getSelectedShiftDates = function(dates) {
    var i, shiftId, index;
    var shiftDatesById = this.shiftDatesById, shiftDates;
    var selectedShiftDates = [];
    for (i = 0; i < dates.length; i++) {
        for (shiftId in shiftDatesById) {
            shiftDates = shiftDatesById[shiftId];
            for (index = 0; index < shiftDates.length; index++) {
                if (shiftDates[index].date == dates[i]) {
                    selectedShiftDates.push(shiftDates[index]);
                }
            }
        }
    }
    return selectedShiftDates;
};
