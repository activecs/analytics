<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>

<!DOCTYPE>
<html>
<head>
	<title>${title}</title>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
    <!--
	BODY TAG OPTIONS:
	=================
	Apply one or more of the following classes to get the 
	desired effect
	|---------------------------------------------------------|
	| SKINS         | skin-blue                               |
	|               | skin-black                              |
	|               | skin-purple                             |
	|               | skin-yellow                             |
	|               | skin-red                                |
	|               | skin-green                              |
	|---------------------------------------------------------|
	|LAYOUT OPTIONS | fixed                                   |
	|               | layout-boxed                            |
	|               | layout-top-nav                          |
	|               | sidebar-collapse                        |  
	|---------------------------------------------------------|
    -->
    <body class="skin-blue">
	    <div class="wrapper">
			<header class="main-header">
				<%@ include file="/WEB-INF/jspf/header.jspf"%>
			</header>
			
	        <aside class="main-sidebar">
	            <%@ include file="/WEB-INF/jspf/sidebar.jspf"%>
	        </aside>
	        
	        <div class="content-wrapper">
	            <section class="content-header">
		            <%@ include file="/WEB-INF/jspf/content_header.jspf"%>
	            </section>
	            <section class="content">
	                <jsp:doBody />
	            </section>
	        </div>
	        
	        <footer class="main-footer">
                <%@ include file="/WEB-INF/jspf/footer.jspf"%>
			</footer>
		</div>
    </body>
</html>