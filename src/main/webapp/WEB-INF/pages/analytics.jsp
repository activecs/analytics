<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>

<template:page title="Template">
	<!-- Info boxes -->
	<div class="row">
	  <widget:infoHeader name="infoHeader" id="infoHeader" />
	</div><!-- /.row -->
	
	<div class="row">
	  <div class="col-md-12">
	    <widget:chart name="chart" id="chart"/>
	  </div><!-- /.col -->
	</div><!-- /.row -->
	
	<!-- Main row -->
	<div class="row">
	  <!-- Left col -->
	  <div class="col-md-8">
	       <widget:visitorsReport name="Visitors Report" id="visitorsReport"/>	
	  </div><!-- /.col -->
	
	  <div class="col-md-4">
	       <widget:recentlyAddedToBasketProducts name="Recently Added Products to Basket" id="recantlyAddedProducts" />
	  </div><!-- /.col -->
	</div><!-- /.row -->
	
	<div class='row'>
	  <div class="col-md-8">
        <!-- TABLE: LATEST ORDERS -->
        <widget:latestOrders name="Latest Order" id="latestOrders" />
      </div><!-- /.col -->
	  <div class='col-md-4'>
	    <widget:browserUsage name="Browser Usage" id="browserUsage" />
	  </div><!-- /.col -->
	</div><!-- /.row -->
	
	<div class="row">
	  <div class="col-md-4">
	    <!-- PRODUCT LIST -->
	    
	  </div><!-- /.col -->
	</div><!-- /.row -->
</template:page>