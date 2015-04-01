<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>

<div id="${id}" class="box box-primary">
  <div class="box-header with-border">
    <h3 class="box-title">${name}</h3>
    <div class="box-tools pull-right">
      <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
      <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
    </div>
  </div><!-- /.box-header -->
  <div class="box-body">
    <ul class="products-list product-list-in-box">
      
    </ul>
  </div><!-- /.box-body -->
  <div class="box-footer text-center">
    <a href="javascript::;" class="uppercase"></a>
  </div><!-- /.box-footer -->
</div><!-- /.box -->

<script>
$(function(){
	recenlyAddedProductsToBasket.show("${id}")
});
</script>