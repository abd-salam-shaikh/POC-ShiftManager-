<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Posting</title>
<link type="text/css" rel="stylesheet"
	href="/styles/jquery.datepick.css" />
<link type="text/css" rel="stylesheet" href="/styles/FGShiftPicker.css" />
<link type="text/css" rel="stylesheet" href="/styles/FGDatePicker.css" />
<link type="text/css" rel="stylesheet" href="/styles/styles.css" />
</head>
<body>
	<h1>
		<c:out value="${jobPosting.name}" />
	</h1>
	<div class="page">
		<div id="shiftsview" class="tabview">
			<div class='fieldset'>
				<div class='sectionheader'>Select Shift(s)</div>
				<div id='shiftList'></div>
			</div>
			<div class='fieldset'>
				<div class='sectionheader'>Select Date(s)</div>
				<div id='shiftsCalendar'></div>
				<div class='buttons'>
					<div id='addPositions' class='button'>label.addPositions</div>
				</div>
			</div>
			<div class='fieldset'>
				<div class='sectionheader'>Positions</div>
				<div id='positionsList' style='min-height: 150px'>
					<table id='positionsGrid'>
						<thead>
							<tr>
								<th></th>
								<th>Positions</th>
								<th>Shift</th>
								<th>Date</th>
								<th>Start</th>
								<th>End</th>
							</tr>
						</thead>
						<tbody>
							<tr class='template'>
								<td><a href='#' class='remover'>Remove</a></td>
								<td><input type='text' class='positions' /></td>
								<td><span class='shiftId hidden'></span><span
									class='shiftName'></span></td>
								<td><span class='date'></span></td>
								<td><input type='text' class='time startTime' /></td>
								<td><input type='text' class='time endTime' /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class='buttons'>
					<div id='updatePositions' class='button'>Update</div>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript' src="/scripts/jquery.min.js"></script>
	<script type='text/javascript' src="/scripts/jquery.datepick.js"></script>
	<script type="text/javascript" src="/scripts/FGUtils.js"></script>
	<script type="text/javascript" src="/scripts/FGRepeat.js"></script>
	<script type="text/javascript" src="/scripts/FGShiftPicker.js"></script>
	<script type="text/javascript" src="/scripts/FGDatePicker.js"></script>
	<script type="text/javascript" src="/scripts/FGShiftManager.js"></script>
	<script type="text/javascript">
        var shifts = <c:out escapeXml='false' value='${shiftsJson}'/>;

        var deletedShiftDates = [];

        var positionsGrid = new FG.Repeat({
            holder : '#positionsGrid > tbody',
            templateSelector : 'tr.template',
            itemRemoverSelector : '.remover',
            onAdd : function($el, shiftDate) {
                $el.addClass(shiftDate.rowClass);
                $el.find('.shiftId').text(shiftDate.shiftId);
                $el.find('.shiftName').text(shiftDate.shiftName);
                $el.find('.date').text(shiftDate.date);
                $el.find('.startTime').val(shiftDate.startTime);
                $el.find('.endTime').val(shiftDate.endTime);
                $el.find('.positions').val(shiftDate.shiftCount);
            },
            onRemove : function($el) {
                deletedShiftDates.push({
                    shiftId : $el.find('.shiftId').text(),
                    date : $el.find('.date').text()
                });
            }
        });

        var shiftManager = new FG.ShiftManager({
            datePicker : new FG.DatePicker({
                holder : '#shiftsCalendar',
                minDate : '<c:out value="${startDate}"/>',
                maxDate : '<c:out value="${endDate}"/>'
            }),
            shiftPicker : new FG.ShiftPicker({
                holder : '#shiftList',
                type : 'checkbox',
                refreshLabel : 'Refresh Calendar',
                data : shifts
            }),
            addButton : '#addPositions',
            addButtonLabel : 'Add Positions',
            updateButton : '#updatePositions',
            updateButtonLabel : 'Update',
            grid : positionsGrid,
            url : '/job_posting.do',
            markerClass : 'blue'
        });

        $('#updatePositions').bind('click', function() {
            var shiftDates = {
                action : 'updateShiftDates',
                count : 0
            };
            positionsGrid.$holder.find('.item').each(function(index) {
                var $el = $(this);
                var prefix = 'shiftDates[' + shiftDates.count + ']';
                shiftDates[prefix + '.shiftCount'] = $el.find('.positions').val();
                shiftDates[prefix + '.shiftId'] = $el.find('.shiftId').text();
                shiftDates[prefix + '.date'] = $el.find('.date').text();
                shiftDates[prefix + '.startTime'] = $el.find('.startTime').val();
                shiftDates[prefix + '.endTime'] = $el.find('.endTime').val();
                shiftDates.count++;
            });
            var i, index = shiftDates.count;
            for (i = 0; i < deletedShiftDates.length; i++) {
                var item = deletedShiftDates[i];
                var prefix = 'deletedShiftDates[' + i + ']';
                shiftDates[prefix + '.shiftId'] = item.shiftId;
                shiftDates[prefix + '.date'] = item.date;
            }
            $.post('/job_posting.do', shiftDates, function(result) {
                // completed
                shiftManager.refreshCalendar();
            }, 'json').error(function() {
                // error
            });
        });

        // Kick-off
        tabs.show('shifts');
    </script>
</body>
</html>