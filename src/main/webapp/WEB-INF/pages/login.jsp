<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>

<template:page title="Template">

    <!--login modal-->
    <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h1 class="text-center">Login</h1>
                </div>
                <div class="modal-body">
                    <form class="form col-md-12 center-block" action="login" method='POST' >
                        <div class="form-group">
                            <input type="text" class="form-control input-lg" name='username' placeholder="Email">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control input-lg" name='password' placeholder="Password">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary btn-lg btn-block">Sign In</button>
                            <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="col-md-12">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template:page>