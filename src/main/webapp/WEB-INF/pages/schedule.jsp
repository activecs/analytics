<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<template:page title="Schedule">

	<div class="col-md-8">
	
	    <h1 class="page-header">
	        <spring:message code="events"/>
	        <small></small>
	        <button  type="button" class="btn btn-primary pull-right add-event"><spring:message code="event.add.new"/></button>
	        
	    </h1>
		
		<form class="add-event form-horizontal" role="form">
			<legend><spring:message code="event.add.new"/></legend>
			
			<div class="form-group">
				<p class="col-sm-2"></p>
			    <label for="eventTitle" class="col-sm-2 control-label"><spring:message code="event.title"/></label>
			    <div class="input-group col-sm-6">
			      <input type="text" class="form-control validate[required,minSize[5],maxSize[18]]" name="title" placeholder='<spring:message code="event.title"/>'>
			    </div>
			</div>
			
			<div class="form-group">
				<p class="col-sm-2"></p>
			    <label for="eventPlace" class="col-sm-2 control-label"><spring:message code="event.place"/></label>
			    <div class="input-group col-sm-6">
			      <input type="text" class="form-control validate[required,minSize[5],maxSize[18]] datepicker" name="place" placeholder='<spring:message code="event.place"/>'>
			    </div>
			</div>
			
			<div class="form-group">
				<p class="col-sm-2"></p>
                <label for="eventDate" class="col-sm-2 control-label"><spring:message code="event.date"/></label>
                <div class="input-group date form_datetime col-sm-6" data-link-field="eventDate">
                    <input class="form-control validate[required]" name="dateTime" type="text" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove icon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
            </div>
			
			<button type="submit" class="btn btn-success">
				<span class="glyphicon glyphicon-plus"></span><spring:message code="event.create"/>
			</button>
			<div class="col-sm-6" ></div>
			<button type="button" class="btn btn-danger cancel">
				<span class="glyphicon glyphicon-remove"></span><spring:message code="event.cancel"/>
			</button>
			    
		</form>
	    
	    <!-- Posts -->
		<div id="posts">
		</div>
	</div>
	
</template:page>