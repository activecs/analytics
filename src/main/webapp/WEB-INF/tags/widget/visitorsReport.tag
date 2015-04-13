<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>

<!-- MAP & BOX PANE -->
<div id="${id}" class="box box-success">
  <div class="box-header with-border">
    <h3 class="box-title">${name}</h3>
    <div class="box-tools pull-right">
      <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
      <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
    </div>
  </div><!-- /.box-header -->
  <div class="box-body no-padding">
    <div class="row">
      <div class="col-md-9 col-sm-8">
        <div class="pad">
          <!-- Map will be created here -->
          <div class="map-markers" style="height: 325px;"></div>
        </div>
      </div><!-- /.col -->
      <div class="col-md-3 col-sm-4">
        <div class="pad box-pane-right bg-green" style="min-height: 280px">
          <div class="description-block margin-bottom">
            <div class="sparkbar pad" data-color="#fff">90,70,90,70,75,80,70</div>
            <h5 class="description-header visits">0</h5>
            <span class="description-text">Visits</span>
          </div><!-- /.description-block -->
          <div class="description-block margin-bottom">
            <div class="sparkbar pad" data-color="#fff">90,50,90,70,61,83,63</div>
            <h5 class="description-header referrals">0%</h5>
            <span class="description-text">Referrals</span>
          </div><!-- /.description-block -->
          <div class="description-block">
            <div class="sparkbar pad" data-color="#fff">90,50,90,70,61,83,63</div>
            <h5 class="description-header organic">0%</h5>
            <span class="description-text">Organic</span>
          </div><!-- /.description-block -->
        </div>
      </div><!-- /.col -->
    </div><!-- /.row -->
  </div><!-- /.box-body -->
</div><!-- /.box -->

<script>
$(function(){
	visitorsReport.show("${id}");
});
</script>