<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>

<div id="${id}">
	<div class="col-md-3 col-sm-6 col-xs-12">
	   <div class="info-box">
	     <span class="info-box-icon bg-aqua"><i class="ion ion-social-usd"></i></span>
	     <div class="info-box-content">
	       <span class="info-box-text">Total Revenue</span>
	       <span class="info-box-number"><small>$</small><span class="revenue"></span></span>
	     </div><!-- /.info-box-content -->
	   </div><!-- /.info-box -->
	 </div><!-- /.col -->
	 <div class="col-md-3 col-sm-6 col-xs-12">
	   <div class="info-box">
	     <span class="info-box-icon bg-red"><i class="ion ion-android-walk"></i></span>
	     <div class="info-box-content">
	       <span class="info-box-text">Visits</span>
	       <span class="info-box-number visits"></span>
	     </div><!-- /.info-box-content -->
	   </div><!-- /.info-box -->
	 </div><!-- /.col -->
	
	 <!-- fix for small devices only -->
	 <div class="clearfix visible-sm-block"></div>
	
	 <div class="col-md-3 col-sm-6 col-xs-12">
	   <div class="info-box">
	     <span class="info-box-icon bg-green"><i class="ion ion-bag"></i></span>
	     <div class="info-box-content">
	       <span class="info-box-text">Sales</span>
	       <span class="info-box-number sales"></span>
	     </div><!-- /.info-box-content -->
	   </div><!-- /.info-box -->
	 </div><!-- /.col -->
	 <div class="col-md-3 col-sm-6 col-xs-12">
	   <div class="info-box">
	     <span class="info-box-icon bg-yellow"><i class="ion ion-android-desktop"></i></span>
	     <div class="info-box-content">
	       <span class="info-box-text">Page Views</span>
	       <span class="info-box-number page-views"></span>
	     </div><!-- /.info-box-content -->
	   </div><!-- /.info-box -->
	 </div><!-- /.col -->
</div>


<script>
$(function(){
	infoHeader.show("${id}")
});
</script>