<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/template" prefix="template" %>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>
<%@ attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
  <head>
  <title>${title}</title>
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
  </head>
  <body class="skin-blue">
    <div class="wrapper">
        <header class="main-header">
            <%@ include file="/WEB-INF/jspf/header.jspf"%>
        </header>
        
        <!-- Left side column. contains the logo and sidebar -->
        <aside class="main-sidebar">
            <%@ include file="/WEB-INF/jspf/sidebar.jspf"%>
        </aside>
        
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
	        <!-- Content Header (Page header) -->
	        <section class="content-header">
	           <%@ include file="/WEB-INF/jspf/content_header.jspf"%>
	        </section>
	        <!-- Main content -->
	        <section class="content">
	           <jsp:doBody />
	        </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
        
	    <footer class="main-footer">
	       <%@ include file="/WEB-INF/jspf/footer.jspf"%>
	    </footer>
	    
    </div><!-- ./wrapper -->
    
  </body>
</html>