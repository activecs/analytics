$(function() {
	$('#reservation').daterangepicker({ 
		format: 'DD/MM/YYYY',
		startDate: '01/01/2015',
		endDate: moment().format('DD/MM/YYYY') 
	});
	settings.init();
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
	
	URL : "/setup/apply",
	
	init : function(){
		$("#productSelect").change(function(){
			if(settings.isValid())
				settings.apply();
		});
		$("#reservation").on('apply.daterangepicker', function(ev, picker) {
			if(settings.isValid())
				settings.apply();
		});
	},
	
	getCurrentSettings : function(){
		
	},
	
	apply : function(){
		var jsonData = 	JSON.stringify(settings.build());	
		$.ajax({
			url : settings.URL,
			type : 'POST',
			data : jsonData,
			contentType: "application/json; charset=utf-8",
			processData : false,
			success : function(data) {
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
		var productId = $("#productSelect").val();
		return new Settings(dateFrom, dateTo, productId);
	},
	
	isValid : function(){
		var data = settings.build();
		if(data.from && data.to)
			return true;
		else
			return false;
	}
};

function Settings (from, to, productId) {
    this.from = from;
    this.to = to;
    this.productId = productId;
}
//******************************//
//		END SETTINGS			//
//******************************//


/*
 * BAR CHART
 * ---------
 */
var bar_data = {
  data: [["January", 10], ["February", 8], ["March", 4], ["April", 13], ["May", 17], ["June", 9]],
  color: "#3c8dbc"
};

$.plot("#bar-chart", [bar_data], {
  grid: {
    borderWidth: 1,
    borderColor: "#f3f3f3",
    tickColor: "#f3f3f3"
  },
  series: {
    bars: {
      show: true,
      barWidth: 0.5,
      align: "center"
    }
  },
  xaxis: {
    mode: "categories",
    tickLength: 0
  }
});
/* END BAR CHART */

// LINE CHART
var line = new Morris.Line({
  element: 'line-chart',
  resize: true,
  data: [
    {y: '2011 Q1', item1: 2666},
    {y: '2011 Q2', item1: 2778},
    {y: '2011 Q3', item1: 4912},
    {y: '2011 Q4', item1: 3767},
    {y: '2012 Q1', item1: 6810},
    {y: '2012 Q2', item1: 5670},
    {y: '2012 Q3', item1: 4820},
    {y: '2012 Q4', item1: 15073},
    {y: '2013 Q1', item1: 10687},
    {y: '2013 Q2', item1: 8432}
  ],
  xkey: 'y',
  ykeys: ['item1'],
  labels: ['Item 1'],
  lineColors: ['#3c8dbc'],
  hideHover: 'auto'
});

