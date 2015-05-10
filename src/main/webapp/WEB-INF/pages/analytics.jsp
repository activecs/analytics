<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>

<template:page title="Template">
	
	<div class="row">
       <div class="col-md-6">
        <div class="box box-primary">
          <div class="box-header">
            <h3 class="box-title">Date picker</h3>
          </div>
          <div class="box-body">
            <!-- Date range -->
            <div class="form-group">
              <label>Date range:</label>
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="fa fa-calendar"></i>
                </div>
                <input type="text" class="form-control pull-right" id="reservation"/>
              </div><!-- /.input group -->
            </div><!-- /.form group -->
          </div><!-- /.box-body -->
        </div><!-- /.box -->
       </div>
       <div class="col-md-6">
         <div class="box box-primary">
           <div class="box-header">
             <h3 class="box-title">Product picker</h3>
           </div>
           <div class="box-body">
               <!-- Date range -->
               <div class="form-group">
                 <label>Select Product</label>
                 <select class="form-control" id="productSelect">
                 </select>
               </div>
           </div><!-- /.box-body -->
         </div><!-- /.box -->
       </div>
    </div>
	
	<!-- Info boxes -->
	<div class="row">
	  <widget:infoHeader name="infoHeader" id="infoHeader" />
	</div><!-- /.row -->
	
	<div class="row">
       <div class="col-md-6">
        <!-- LINE CHART -->
        <div class="box box-info">
          <div class="box-header">
            <h3 class="box-title">Line Chart</h3>
          </div>
          <div class="box-body chart-responsive">
            <div class="chart" id="line-chart" style="height: 300px;"></div>
          </div><!-- /.box-body -->
        </div><!-- /.box -->
      </div><!-- /.col (RIGHT) -->
      <div class="col-md-6">
        <!-- Bar chart -->
        <div class="box box-primary">
          <div class="box-header">
            <i class="fa fa-bar-chart-o"></i>
            <h3 class="box-title">Bar Chart</h3>
          </div>
          <div class="box-body">
            <div id="bar-chart" style="height: 300px;"></div>
          </div><!-- /.box-body-->
        </div><!-- /.box -->
        </div>
    </div>
	
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