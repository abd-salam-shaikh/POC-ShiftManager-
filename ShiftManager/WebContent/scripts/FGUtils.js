if (!('FG' in window)) {
    FG = {

    };
}

//
// Array utility functions
//
FG.Arrays = {
    indexOf : function(array, value) {
        if ('indexOf' in array) {
            return array.indexOf(value);
        }
        for ( var i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    },

    remove : function(array, value) {
        var pos = this.indexOf(array, value);
        if (pos >= 0) {
            this.removeAt(array, pos);
        }
    },

    removeAt : function(array, index) {
        array.splice(index, 1);
    }
};

//
// Object utility functions
//
FG.Objects = {
    copyValue : function(o1, o2, propName, defaultValue) {
        if (propName in o2) {
            o1[propName] = o2[propName];
        } else if (typeof defaultValue != "undefined") {
            o1[propName] = defaultValue;
        }
    }
}
//
// Date utility functions
//
FG.Date = {
    toYmds : function(dates) {
        var ymds = [];
        for ( var i = 0; i < dates.length; i++) {
            ymds.push($.datepick.formatDate("yyyy-mm-dd", dates[i], {}));
        }
        ymds.sort();
        return ymds;
    },

    toDates : function(ymds) {
        var dates = [];
        for ( var i = 0; i < ymds.length; i++) {
            dates.push($.datepick.parseDate("yyyy-mm-dd", ymds[i]));
        }
        return dates;
    },

    monthToOffset : function(ym) {
        var parts = ym.split("-");
        var y = FG.Text.parseInt(parts[0]);
        var m = FG.Text.parseInt(parts[1]);
        return (y - 2000) * 12 + m;
    },

    offsetToMonth : function(offset) {
        var years = 2000 + Math.floor(offset / 12);
        var month = offset % 12;
        return years + "-" + FG.Text.padNum(month, 2);
    }

};

//
// String utility functions
//

FG.Text = {
    pads : [ '0000000000', '0', '00', '000', '0000', '00000' ],
    substring : function(s, from, to) {
        return s.substr(from, to);
    },
    padNum : function(num, length) {
        var numValue = num + '';
        var diff = length - numValue.length;
        if (diff <= 0) {
            return numValue;
        } else if (diff < this.pads.length) {
            return this.pads[diff] + numValue;
        }
        numValue = this.pads[0] + numValue;
        return numValue.substr(numValue.length - length, numValue.length);
    },
    parseInt : function(numValue) {
        if (numValue == '0') {
            return 0;
        }
        var pos = 0;
        while (pos < numValue.length && numValue.charAt(pos) == '0') {
            pos++;
        }
        if (pos == 0) {
            return parseInt(numValue);
        }
        if (pos == numValue.length) {
            return 0;
        }
        return parseInt(numValue.substr(pos, numValue.length));
    }
};
