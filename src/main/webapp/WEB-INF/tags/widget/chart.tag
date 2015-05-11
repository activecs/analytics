<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>

<div id="${id}" class="box distribution">
  <div class="box-header with-border">
    <h3 class="box-title">Distribution report</h3>
  </div><!-- /.box-header -->
  <div class="box-footer">
    <div class="row">
      <div class="col-sm-3 col-xs-6">
        <div class="description-block border-right">
          <h5 class="description-header min">0</h5>
          <span class="description-text">MIN</span>
        </div><!-- /.description-block -->
      </div><!-- /.col -->
      <div class="col-sm-3 col-xs-6">
        <div class="description-block border-right">
          <h5 class="description-header max">0</h5>
          <span class="description-text">MAX</span>
        </div><!-- /.description-block -->
      </div><!-- /.col -->
      <div class="col-sm-3 col-xs-6">
        <div class="description-block border-right">
          <h5 class="description-header sigma">0</h5>
          <span class="description-text">SIGMA</span>
        </div><!-- /.description-block -->
      </div><!-- /.col -->
      <div class="col-sm-3 col-xs-6">
        <div class="description-block">
          <h5 class="description-header dispersion">0</h5>
          <span class="description-text">DISPERSION</span>
        </div><!-- /.description-block -->
      </div>
    </div><!-- /.row -->
  </div><!-- /.box-footer -->
</div><!-- /.box -->

<script>
$(function(){

});
</script>