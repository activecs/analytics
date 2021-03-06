<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>

<div id="${id}" class="box box-default">
	<div class="box-header with-border">
	  <h3 class="box-title">${name}</h3>
	  <div class="box-tools pull-right">
	    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
	    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
	  </div>
	</div>
	
	<div class="box-body">
	  <div class="row">
	    <div class="col-md-8">
	      <div class="chart-responsive">
	        <canvas class="chart-area" height="150"></canvas>
	     </div>
	   </div>
	   <div class="col-md-4">
	     <ul class="chart-legend clearfix">
	     </ul>
	   </div>
	 </div>
	</div>
	
	<div class="box-footer no-padding">
	  <ul class="nav nav-pills nav-stacked">
	  </ul>
	</div>
</div>

<script>
$(function(){
	browserUsage.show("${id}");	
});
</script>