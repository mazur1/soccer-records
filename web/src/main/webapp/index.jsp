<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>

<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Soccer Records AngularJS</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-resource.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-route.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/angular-app.js"></script>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css" />
    
    </head>
    
    <body>
        
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Soccer records</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#!/teams">Teams list</a></li>
                        <li><a href="#!/players">Players list</a></li>
                        <li><a href="#!/matches">Matches list</a></li>
                        <li><a href="#!/results">Players result list</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#!/admin/teams">Teams</a></li>
                                <li><a href="#!/admin/players">Players</a></li>
                                <li><a href="#!/admin/matches">Matches</a></li>
                            </ul>
                        </li>
                    </ul>
                                    
                    <div id="login" class="collapse navbar-collapse pull-right">
                        <ul class="nav navbar-nav">
                             <li>
                                 <a href="#!/login">Log in</a>
                             </li>
                        </ul>
                    </div>

                    <div style="display: none !important;" id="logged" class="collapse navbar-collapse pull-right">
                        <ul class="nav navbar-nav">
                             <li>
                                 
                             </li>
                        </ul>
                    </div>                    
                    
                </div>
                 <!--/.nav-collapse -->
            </div>
        </nav>

        <main>

            <div ng-app="soccerRecordspApp" class="container">

                <div><!-- AngularJS takes care of this element -->

                    <!-- Bootstrap-styled alerts, visible when $rootScope.xxxAlert is defined -->
                    <div ng-show="warningAlert" class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" aria-label="Close" ng-click="hideWarningAlert()"> <span aria-hidden="true">&times;</span></button>
                        <strong>Warning!</strong> <span>{{warningAlert}}</span>
                    </div>
                    <div ng-show="errorAlert" class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" aria-label="Close" ng-click="hideErrorAlert()"> <span aria-hidden="true">&times;</span></button>
                        <strong>Error!</strong> <span>{{errorAlert}}</span>
                    </div>
                    <div ng-show="successAlert" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" aria-label="Close" ng-click="hideSuccessAlert()"> <span aria-hidden="true">&times;</span></button>
                        <strong>Success !</strong> <span>{{successAlert}}</span>
                    </div>

                    <!-- the place where HTML templates are replaced by AngularJS routing -->
                    <div ng-view></div>
                    
                </div>

            </div>

        </main>

        <footer class="footer">
            <div class="container">
                <p>&copy;&nbsp;2017&nbsp;Soccer records&nbsp;-&nbsp;Masaryk University</p>
            </div>
        </footer>

    </body>
</html>