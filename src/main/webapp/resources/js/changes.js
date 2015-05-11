$(function() {
	$('#reservation').daterangepicker({ 
		format: 'DD/MM/YYYY',
		startDate: '01/01/2015',
		endDate: moment().format('DD/MM/YYYY'),
		dateLimit: { days: 14 },
		locale: {
            daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
            monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            firstDay: 1
        }
	});
	settings.init();
	salesPerDay.init();
	salesAmountDistribution.init();
});

var common = {
	changeLocale : function(loc) {
		$.get(window.location.pathname, {
			"lang" : loc
		}, function() {
			window.location.reload(true);
		});
	},

	convertFormToJSON : function(form) {
		var array = $(form).serializeArray();
		var json = {};

		$.each(array, function() {
			json[this.name] = this.value || '';
		});

		return JSON.stringify(json);
	}
}

//-----------------
//- SPARKLINE BAR -
//-----------------
var spark = {
	
	initBar : function($selector){
		$($selector).each(function () {
			var $this = $(this);
			$this.sparkline('html', {
				type: 'bar',
			    height: $this.data('height') ? $this.data('height') : '30',
			    barColor: $this.data('color')
			});
		});
	}
}

var registrationService = {

	REGISTRATION_URL : 'registration',
	
	$registrationModal : $(".modal-dialog.registration"),

	validate : function validateForm(e) {
		var loginForm = $('#registrationForm');
		var confirmation = $('#conf_failed');

		confirmation.css({
			'display' : 'none'
		});
		loginForm.validate();
		if (loginForm.valid()) {
			var pass = $('#password').val();
			var passConf = $('#confirm_password').val();
			if (pass == passConf) {
				registrationService.register(e);
			} else {
				confirmation.css({'display' : 'block'});
			}
		}
	},

	register : function sendData(e) {
		var loginForm = $('#registrationForm');
		var input = $("#fileupload");
		if (input.prop('disabled')) {
			input.prop('disabled', false);
		}
		$(".server-error").remove();
		loginForm.submit();
		if (input.prop('disabled')) {
			input.prop('disabled', true);
		}
	},

	response : function parseResponse(response) {
		if (response.isValid) {
			this.$registrationModal.find(".reg").css({
				'display' : 'none'
			});
			this.$registrationModal.find(".reg-success").css({
				'display' : 'block'
			});
		} else {
			$.each(response.errors, function(index, value) {
				var field = $("#" + value.field);
				var errMsg = $("<strong>").html(value.errMsg);
				var errWrap = $("<span>").addClass("error").addClass("server-error").css({
					'display' : 'block'
				});
				errWrap.append(errMsg);
				field.after(errWrap);
			});
		}
	},
	
	handleServerError : function() {
		this.$registrationModal.find(".reg").css({
			'display' : 'none'
		});
		this.$registrationModal.find(".reg-server-error").css({
			'display' : 'block'
		});
	},

	cleanServerErrors : function remove(element) {
		$('.server-error', element.parentNode).each(function() {
			$(this).remove();
		});
	},

	formSubmit : function(e) {
		var formData = new FormData($('#registrationForm')[0]);

		$.ajax({
			url : registrationService.REGISTRATION_URL,
			type : 'POST',
			cache : false,
			contentType : false,
			processData : false,
			data : formData,
			beforeSend : function() {
				$('#loading').css({
					'display' : 'inline'
				});
			},
			complete : function() {
				$('#loading').css({
					'display' : 'none'
				});
			},
			success : function(data) {
				registrationService.response(data);
			},
			error : function() {
				registrationService.handleServerError();
			}
		});
		e.preventDefault();
	}
}

//****************************//
//BROWSER USAGE WIDGET      //
//****************************//
var browserUsage = {

	URL : "/browser/usage",

	show : function(elementId) {
		this.init(elementId);
	},

	init : function(elementId) {
		$.ajax({
			url : browserUsage.URL,
			type : 'GET',
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				browserUsage.build(data, elementId);
			},
			error : function() {
				alert('error');
			}
		})
	},

	build : function(data, elementId) {
		var browserUsageData = new Array();
		for ( var browserIndex in data) {
			var browserData = data[browserIndex];
			var browserName = browserData.browser.name;
			var labelColour = this.getLabelColours()[browserIndex];
			var browserUsage = new BrowserUsage(browserData.occurence, labelColour, browserName);
			browserUsageData.push(browserUsage);
		}
		var pieChartCanvas = $("#" + elementId + " .chart-area").get(0).getContext("2d");
		var pieChart = new Chart(pieChartCanvas);
		var doughnut = pieChart.Doughnut(browserUsageData, this.getOptions());

		this.buildLegend(doughnut, $("#" + elementId + " .chart-legend"));
	},

	getLabelColours : function() {
		return [ "#f56954", "#00a65a", "#f39c12", "#00c0ef", "#3c8dbc",
				"#d2d6de" ];
	},

	getOptions : function() {
		var pieOptions = {
			// Boolean - Whether we should show a stroke on each segment
			segmentShowStroke : true,
			// String - The colour of each segment stroke
			segmentStrokeColor : "#fff",
			// Number - The width of each segment stroke
			segmentStrokeWidth : 1,
			// Number - The percentage of the chart that we cut out of the
			// middle
			percentageInnerCutout : 50, // This is 0 for Pie charts
			// Number - Amount of animation steps
			animationSteps : 100,
			// String - Animation easing effect
			animationEasing : "easeOutBounce",
			// Boolean - Whether we animate the rotation of the Doughnut
			animateRotate : true,
			// Boolean - Whether we animate scaling the Doughnut from the centre
			animateScale : false,
			// Boolean - whether to make the chart responsive to window resizing
			responsive : true,
			// Boolean - whether to maintain the starting aspect ratio or not
			// when responsive, if set to false, will take up entire container
			maintainAspectRatio : false,
			// String - A legend template
			legendTemplate : "<% for (var i=0; i<segments.length; i++){%><li><i class=\"fa fa-circle-o\" style=\"color:<%=segments[i].fillColor%>\"></i> <%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%>",
			// String - A tooltip template
			tooltipTemplate : "<%=value %> sessions with <%=label%>"
		};

		return pieOptions;
	},

	buildLegend : function(chart, $element) {
		var legend = chart.generateLegend();
		$element.append(legend);
	}
};

function BrowserUsage(value, color, label) {
	this.value = value;
	this.color = color;
	this.highlight = "#d2d6de";
	this.label = label;
}

//***************************//
//BROWSER USAGE WIDGET END	 //
//***************************//

//******************************//
//RECENTLY ADDED PRODUCTS WIDGET//
//******************************//
var recenlyAddedProductsToBasket = {

	URL : "/event/basket/recently-added",

	show : function(elementId) {
		this.init(elementId);
	},

	init : function(elementId) {
		$.ajax({
			url : recenlyAddedProductsToBasket.URL,
			type : 'GET',
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				recenlyAddedProductsToBasket.build(data, elementId);
			},
			error : function() {
				alert('error');
			}
		})
	},

	build : function(data, elementId) {
		var items = '';
		$.each(data, function(index, product) {
			var currentItem = "<li class='item'><div class='product-img'><img src='/resources/images/image-placeholder.jpg' alt='Product Image'/></div><div class='product-info'>";
			currentItem += "<a href='#' class='product-title'>";
			currentItem += "SKU:" + product.sku;
			currentItem += "<span class='label label-warning pull-right'>";
			currentItem += "$" + product.price;
			currentItem += "</span></a><span class='product-description'>";
			currentItem += product.name;
			currentItem += "</span></div></li>";
			items += currentItem; 
		});
		$("#"+elementId + " .products-list").append(items);
	}
};

//*******************************//
//RECENTLY ADDED PRODUCTS END	 //
//*******************************//

//******************************//
//		LATEST ORDER  WIDGET	//
//******************************//
var latestOrders = {

	URL : "/event/order/latest",

	show : function(elementId) {
		this.init(elementId);
	},

	init : function(elementId) {
		$.ajax({
			url : latestOrders.URL,
			type : 'GET',
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				latestOrders.build(data, elementId);
			},
			error : function() {
				alert('error');
			}
		})
	},

	build : function(data, elementId) {
		var items = '';
		$.each(data, function(index, order) {
			$.each(order.items, function(index, orderItem){
				var currentItem = "<tr><td><a href='#'>" +
				order.id +
				"</a></td><td>" +
				orderItem +
				"</td><td><span class='label label-success'>Placed</span></td><td><div class='sparkbar' data-color='#00a65a' data-height='20'>90,80,90,-70,61,-83,63</div></td></tr>"
			items += currentItem;
			});
		});
		$("#"+elementId + " tbody").append(items);
		spark.initBar($('.sparkbar'));
	}
};

//*******************************//
//	LATEST ORDER  WIDGET END	 //
//*******************************//

//*******************************//
//	VISITORS REPORT  WIDGET 	 //
//*******************************//

var visitorsReport = {
	
	URL : "/visitor/report",

	show : function(elementId) {
		this.init(elementId);
	},

	init : function(elementId) {
		$.ajax({
			url : visitorsReport.URL,
			type : 'GET',
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				visitorsReport.build(data, elementId);
			},
			error : function() {
				alert('error');
				visitorsReport.build(1, elementId);
			}
		})
	},

	build : function(data, elementId) {
		var locationMarkers = new Array();
		$.each(data.revisions, function(index, dataItem){
			var description = dataItem.description + " - " + dataItem.amount + " visits";
			locationMarkers.push(new Marker(dataItem.latitude,dataItem.longitude,description));				
		});
		
		$('#' + elementId + ' .map-markers').vectorMap({
		  map: 'world_mill_en',
		  normalizeFunction: 'polynomial',
		  hoverOpacity: 0.7,
		  markersSelectable : true,
		  hoverColor: false,
		  backgroundColor: 'transparent',
		  regionStyle: {
		    initial: {
		      fill: 'rgba(210, 214, 222, 1)',
		      "fill-opacity": 1,
		      stroke: 'none',
		      "stroke-width": 0,
		      "stroke-opacity": 1
		    },
		    hover: {
		      fill: 'rgba(0,0,255,0.3)',
		      "fill-opacity": 0.8,
		      cursor: 'pointer'
		    },
		    selected: {
		      fill: 'yellow'
		    },
		    selectedHover: {
		    }
		  },
		  markerStyle: {
		    initial: {
		      fill: '#00a65a',
		      stroke: '#111'
		    }
		  },
		  markers: locationMarkers
		});
		
		$('#' + elementId + ' .visits').html(data.visits);
		$('#' + elementId + ' .referrals').html(data.referral + '%');
		$('#' + elementId + ' .organic').html(data.organic + '%');
	}	
}

function Marker(latitude, longitude, name) {
	this.latLng = [latitude, longitude];
	this.name = name;
}

//*******************************//
//	VISITORS REPORT  WIDGET END	 //
//*******************************//


//******************************//
//			INFO HEADER  WIDGET	//
//******************************//
var infoHeader = {

	URL : "/event/info",
	
	show : function(elementId) {
		this.init(elementId);
	},
	
	init : function(elementId) {
		$.ajax({
			url : infoHeader.URL,
			type : 'GET',
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				infoHeader.build(data, elementId);
			},
			error : function() {
				alert('error');
			}
		})
	},
	
	build : function(data, elementId) {
		$("#" + elementId +" span.visits").html(data.visits);
		$("#" + elementId +" span.sales").html(data.sales);
		$("#" + elementId +" span.page-views").html(data.pageViews);
		$("#" + elementId +" span.revenue").html(data.revenue);
	}
};

//*******************************//
//		INFO HEADER  WIDGET END	 //
//*******************************//

//******************************//
//			SETTINGS			//
//******************************//
var settings = {
	
	URL : "/settings",
	
	init : function(){
		settings.downloadProducts();
		$("#productSelect").change(function(){
			if(settings.isValid())
				settings.apply();
		});
		$("#reservation").on('apply.daterangepicker', function(ev, picker) {
			if(settings.isValid())
				settings.apply();
		});
	},
	
	setCurrentSettings : function(){
		$.ajax({
			url : settings.URL,
			type : 'GET',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				console.log(data);
				var dateFromTo = data.from + " - " + data.to;
				$('#reservation').val(dateFromTo);
				$("#productSelect option[value='"+data.productSku+"']").prop('selected', true);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(textStatus + '\n' + errorThrown);
			}
		});
	},
	
	downloadProducts : function(){
		$.ajax({
			url : settings.URL + '/products',
			type : 'GET',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				console.log(data);
				$.each(data, function(index, item) {
					product = new Option(item.name, item.sku);
					$("#productSelect").append($(product));
				});
				settings.setCurrentSettings();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(textStatus + '\n' + errorThrown);
			}
		});
	},
	
	apply : function(){
		var jsonData = 	JSON.stringify(settings.build());	
		$.ajax({
			url : settings.URL + '/apply',
			type : 'POST',
			data : jsonData,
			contentType: "application/json; charset=utf-8",
			processData : false,
			success : function(data) {
				location.reload(true);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(textStatus + '\n' + errorThrown);
			}
		})
	},
	
	build : function(){
		var dateFromTo = $('#reservation').val();
		var dateFrom = dateFromTo.substring(0,10);
		var dateTo = dateFromTo.substring(13,24);
		var productSku = $("#productSelect").val();
		return new Settings(dateFrom, dateTo, productSku);
	},
	
	isValid : function(){
		var data = settings.build();
		if(data.from && data.to)
			return true;
		else
			return false;
	}
};

function Settings (from, to, productSku) {
    this.from = from;
    this.to = to;
    this.productSku = productSku;
}
//******************************//
//		END SETTINGS			//
//******************************//


/*
 * SALES AMOUNT BAR CHART
 * ---------
 */

var salesAmountDistribution = {
		
	URL : "/sales/distribution",
	
	init : function(){
		$.ajax({
			url : salesAmountDistribution.URL,
			type : 'GET',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				var chartData = [];
				$.each(data, function(index, item) {
					chartData.push(new SalesDistribution(item.amount, item.dayNumber));
				});
				salesAmountDistribution.build(chartData);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(textStatus + '\n' + errorThrown);
			}
		});
	},
	
	build : function(chartData){
		var bar = Morris.Bar({
			element: "bar-chart",
			data: chartData,
			xkey: 'amount',
			ykeys: ['dayNumber'],
			labels: ['number of days'],
			hideHover: 'auto',
			postUnits: [' days'],
			hoverCallback: function (index, options, content, row) {
			  return row.dayNumber + " days we sold " + row.amount + " products";
			}
		});
	} 	
};

function SalesDistribution(amount, dayNumber) {
	this.amount = amount;
	this.dayNumber = dayNumber;
}

function SalesDistributionChartData(data) {
	this.data = data;
	this.color = "#3c8dbc"
}

var bar_data = {
  data: [["January", 10], ["February", 8], ["March", 4], ["April", 13], ["May", 17], ["June", 9]],
  color: "#3c8dbc"
};


/* SALES AMOUNT BAR CHART */

// SALES PER DAY LINE CHART

var salesPerDay = {
		
	URL : "/sales/per-day",
	
	init : function(){
		$.ajax({
			url : salesPerDay.URL,
			type : 'GET',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				var chartData = [];
				$.each(data, function(index, item) {
					chartData.push(new SalesPerDayData(item.date, item.amount, item.product.name));
				});
				salesPerDay.build(chartData);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(textStatus + '\n' + errorThrown);
			}
		});
	},
	
	build : function(chartData){
		var line = new Morris.Line({
		  element: 'line-chart',
		  resize: true,
		  data: chartData,
		  xkey: 'date',
		  ykeys: ['amount'],
		  labels: ['sold amount'],
		  xlabels: ['day'],
		  postUnits: [' pcs'],
		  lineColors: ['#3c8dbc'],
		  hideHover: 'auto',
		  gridIntegers: true,
		  ymin: 0
		});
	} 	
};

function SalesPerDayData(date, amount, label) {
	this.date = date;
	this.amount = amount;
	this.label = label;
}

// END SALES PER DAY LINE CHART

//-----------------------
//- MONTHLY SALES CHART -
//-----------------------

// Get context with jQuery - using jQuery's .get() method.
var salesChartCanvas = $("#salesChart").get(0).getContext("2d");
// This will get the first returned node in the jQuery collection.
var salesChart = new Chart(salesChartCanvas);

var salesChartData = {
  labels: ["January", "February", "March", "April", "May", "June", "July"],
  datasets: [
    {
      label: "Digital Goods",
      fillColor: "rgba(60,141,188,0.9)",
      strokeColor: "rgba(60,141,188,0.8)",
      pointColor: "#3b8bba",
      pointStrokeColor: "rgba(60,141,188,1)",
      pointHighlightFill: "#fff",
      pointHighlightStroke: "rgba(60,141,188,1)",
      data: [28, 48, 40, 19, 86, 27, 90]
    }
  ]
};

var salesChartOptions = {
  //Boolean - If we should show the scale at all
  showScale: true,
  //Boolean - Whether grid lines are shown across the chart
  scaleShowGridLines: false,
  //String - Colour of the grid lines
  scaleGridLineColor: "rgba(0,0,0,.05)",
  //Number - Width of the grid lines
  scaleGridLineWidth: 1,
  //Boolean - Whether to show horizontal lines (except X axis)
  scaleShowHorizontalLines: true,
  //Boolean - Whether to show vertical lines (except Y axis)
  scaleShowVerticalLines: true,
  //Boolean - Whether the line is curved between points
  bezierCurve: true,
  //Number - Tension of the bezier curve between points
  bezierCurveTension: 0.3,
  //Boolean - Whether to show a dot for each point
  pointDot: false,
  //Number - Radius of each point dot in pixels
  pointDotRadius: 4,
  //Number - Pixel width of point dot stroke
  pointDotStrokeWidth: 1,
  //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
  pointHitDetectionRadius: 20,
  //Boolean - Whether to show a stroke for datasets
  datasetStroke: true,
  //Number - Pixel width of dataset stroke
  datasetStrokeWidth: 2,
  //Boolean - Whether to fill the dataset with a color
  datasetFill: true,
  //String - A legend template
  legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%=datasets[i].label%></li><%}%></ul>",
  //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
  maintainAspectRatio: false,
  //Boolean - whether to make the chart responsive to window resizing
  responsive: true
};

//Create the line chart
salesChart.Line(salesChartData, salesChartOptions);

//---------------------------
//- END MONTHLY SALES CHART -
//---------------------------
