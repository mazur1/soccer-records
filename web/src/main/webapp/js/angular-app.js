'use strict';

/* Defines application and its dependencies */

var soccerRecordspApp = angular.module('soccerRecordspApp', ['ngRoute', 'ngCookies', 'soccerControllers']);
var soccerControllers = angular.module('soccerControllers', []);

/* Configures URL fragment routing, e.g. #/product/1  */
soccerRecordspApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/home', {templateUrl: 'partials/home.html', controller: 'DefaultController'}).
            when('/login', {templateUrl: 'partials/admin/forms/login.html', controller: 'LoginController'}).
            when('/logout', {templateUrl: 'partials/home.html',controller: 'LogoutController'}).
            when('/teams', {templateUrl: 'partials/teams.html', controller: 'TeamsController'}).
            when('/players', {templateUrl: 'partials/players.html', controller: 'PlayersController'}).
            when('/matches', {templateUrl: 'partials/matches.html', controller: 'MatchesController'}).
            when('/results', {templateUrl: 'partials/results.html', controller: 'ResultsController'}).
            when('/teams/:teamId', {templateUrl: 'partials/detail/team.html', controller: 'TeamDetailController'}).
            when('/players/:playerId', {templateUrl: 'partials/detail/player.html', controller: 'PlayerDetailController'}).            
            when('/matches/:matchId', {templateUrl: 'partials/detail/match.html', controller: 'MatchDetailController'}).
            when('/newPlayer', {templateUrl: 'partials/admin/new_player.html', controller: 'NewPlayerController'}).
            when('/newmatch', {templateUrl: 'partials/admin/new_match.html', controller: 'NewMatchController'}).
            when('/newteam', {templateUrl: 'partials/admin/new_team.html', controller: 'NewTeamController'}).
            when('/newplayerresult', {templateUrl: 'partials/admin/new_player_result.html'}).
            otherwise({redirectTo: '/home'});
    }]);

/*
 * alert closing functions defined in root scope to be available in every template
 */
soccerRecordspApp.run(function($rootScope, $cookieStore) {
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    }; 
  
    parseUserData($cookieStore, $rootScope);

    if($cookieStore.get('MsgRedirect') !== undefined){
        $rootScope.successAlert = $cookieStore.get('MsgRedirect');
        $cookieStore.remove('MsgRedirect');
    }
});


function parseUserData($cookieStore, $rootScope){
    
    if($cookieStore.get('user') === undefined){
        $cookieStore.put('user', {
            'name': "",
            'email': "",
            'logged': false
        });
    }
    
    $rootScope.loggedUser = $cookieStore.get('user');
    
    if($rootScope.loggedUser.logged){         
        $("#login").find("li").html('<a>Logged user: '+$rootScope.loggedUser.name + '</a>');      
        $("#login").find("ul").append('<li><a href="#!/logout">Log out</a></li>')
    }

}

/* Controllers */

/*
 * Public default interface
 */

/*
 * Shopping page with all categories and products
 */
soccerControllers.controller('DefaultController', function ($scope, $rootScope, $http) {
    

    
});

soccerControllers.controller('LoginController', function ($scope, $routeParams, $http, $location, $rootScope, $cookieStore) {
    
        //set object bound to form fields
        $scope.user = {
            'usernname': '',
            'password': ''
        };
        
        // function called when submit button is clicked, creates product on server
        $scope.login = function (user) {
                    
            console.log(user);        
                    
            $http({
                method: 'POST',
                url: '/pa165/api/v1/users/login/',
                data: user
            }).then(function success(response) {
                
                console.log('User succesfully logged');      

                $cookieStore.remove('user');

                $cookieStore.put('user', {
                    'name': response.data.username,
                    'email': "",
                    'logged': true
                });
                
                parseUserData($cookieStore, $rootScope);
            
               $cookieStore.put('MsgRedirect', "Login succesfull");
            
                $location.path("/home");
                
            }, function error(response) {
                
                //display error
                $rootScope.errorAlert = 'Login failed!';
            });
        };
    
});

soccerControllers.controller('LogoutController', function ($scope, $routeParams, $http, $location, $rootScope, $cookieStore) {
 
   $cookieStore.remove('user');
   
   var href = window.location.href;
   var parts = href.split("#");
   
   $cookieStore.put('MsgRedirect', "Logout succesfull");
   
   window.location.href = parts[0];
   
});

soccerControllers.controller('TeamsController', function ($scope, $rootScope, $http) {
    
    $http.get('/pa165/api/v1/teams').then(function(response) {        
        var teams = response.data['_embedded']['teams'];             
        console.log('AJAX loaded all teams');  
        $scope.teams = teams;
        
    }, function error(error) {
        //display error
        $rootScope.errorAlert = error.data.message;
    });
    
});


soccerControllers.controller('NewTeamController',  function ($scope, $rootScope, $window, $http) {
        
    $scope.create = function (team) {
    
            $http({
                method: 'POST',
                url: '/pa165/api/v1/teams/create',
                data: team
            })
            .then(function(response) {
                console.log('team succesfuly created');
                $window.location='/pa165/#!/teams';
        
            }, 
            function(response) { 
                console.log('new team create failed'); 
                $rootScope.errorAlert = "new team create failed";
            });
    }
});

soccerControllers.controller('TeamDetailController', function ($scope, $routeParams, $http, $rootScope) {
    
    // get team id from URL fragment #/product/:productId
        
    var teamId = $routeParams.teamId;
    
    $http.get('/pa165/api/v1/teams/' + teamId).then(function (response) {
            
        console.log(response);    
            
        $scope.team = response.data;
        console.log('AJAX loaded detail of team ' + $scope.team.name);
    
    }, function error(error) {
        //display error
        $rootScope.errorAlert = error.data.message;
    });
});

soccerControllers.controller('PlayersController', function ($scope, $rootScope, $http) {
    
    $http.get('/pa165/api/v1/players').then(function(response) {
        
        var players = response.data['_embedded']['players'];             
        console.log('AJAX loaded all players');  
        $scope.players = players;

    }, function error(error) {
        //display error
        $rootScope.errorAlert = error.data.message;
    });
    
});

soccerControllers.controller('PlayerDetailController', function ($scope, $rootScope, $routeParams, $http) {
        // get team id from URL fragment #/product/:productId
        
        var playerId = $routeParams.playerId;
        $http.get('/pa165/api/v1/players/' + playerId).then(function (response) {
            $scope.player = response.data;
            console.log('AJAX loaded detail of team ' + $scope.player.name);
    
    }, function error(error) {
        //display error
        $rootScope.errorAlert = error.data.message;
    });
});

soccerControllers.controller('NewPlayerController',  function ($scope, $location, $rootScope, $window, $http) {
        
    $scope.posts = ['ATTACKER', 'MIDFIELDER', 'DEFENDER', 'GOLMAN'];
    
    $http.get('/pa165/api/v1/teams/').then(function (response) {
        $scope.teams = response.data['_embedded']['teams'];
    });
        
    $scope.player = {
        'name': '',
        'surname': '',
        'age': 0,
        'post': '',
        'captain': false,
        'country': '',
        'city': '',
        'team': null
    };    
        
    $scope.create = function (player) {
    
            alert(JSON.stringify(player));
    
            $http({
                method: 'POST',
                url: '/pa165/api/v1/players/create/',
                data: player
            }).then(function success(response) {
                
                $rootScope.successAlert = "Player succesfuly created";
                console.log('player succesfuly created');
                $location.path("/players");
                
            }, function error(response) {
                console.log('new player create failed'); 
                $rootScope.errorAlert = "new player create failed";
            }); 
       
    }
});

soccerControllers.controller('MatchesController', function ($scope, $rootScope, $http) {
    
    $http.get('/pa165/api/v1/matches').then(function(response) {
        
        var matches = response.data['_embedded']['matches'];             
        console.log('AJAX loaded all matches');  
        $scope.matches = matches;

    }, function error(error) {
        console.log(error);
        $rootScope.errorAlert = error.data.message;
    });
    
});

soccerControllers.controller('MatchDetailController', function ($scope, $rootScope, $routeParams, $http) {
                
        var matchId = $routeParams.matchId;
        $http.get('/pa165/api/v1/matches/'+matchId).then(function (response) {
            $scope.match = response.data;
            
            console.log('AJAX loaded detail of match ' + $scope.match.toString());
            
    }, function error(error) {
        console.log(error);
        $rootScope.errorAlert = error.data.message;
    });
    
    /*$http.get('/pa165/api/v1/teams'+$scope.match.teamHome.id).then(function(response) {
        
        var players = response.data['_embedded']['players'];             
        console.log('AJAX loaded all teams');  
        //$scope.items2 = players;

    }, function error(error) {
        console.log(error);
        $scope.errorAlert = error;
    });*/   
    
    $scope.deleteMatch = function() {
        $http.delete('/pa165/api/v1/matches/'+matchId)
            .then(function success(response) {
            console.log('deleted match');
            //display confirmation alert
            $rootScope.successAlert = 'A match was deleted';
            //change view to list
            $location.path("/matches");
        }, function error(response) {
            //display error
            $scope.errorAlert = 'Cannot delete match!';
        });
        
    };
    
    $scope.deleteResult = function(resultId) {
        $http.delete('/pa165/api/v1/matches/'+matchId+'results', resultId)
            .then(function success(response) {
            console.log('deleted match');
            //display confirmation alert
            $rootScope.successAlert = 'A result was deleted';
            //change view to list
            $location.path("/matches");
        }, function error(response) {
            //display error
            $scope.errorAlert = 'Cannot delete match!';
        });
        
    };
        
    $scope.IsHidden = true;
    $scope.ShowHide = function () {
        $scope.IsHidden = $scope.IsHidden ? false : true;
    };
    
    //set object bound to form fields
    $scope.playerResult = {
        'player': null,
        'goalsScored': null
    };
    // function called when submit button is clicked, creates match on server
    $scope.create = function (playerResult) {
    
        $http({
            method: 'POST',
            url: '/pa165/api/v1/matches/'+matchId+'/results',
            data: playerResult
        }).then(function success(response) {
            console.log('created player result');
            var created= response.data;
            //display confirmation alert
            $rootScope.successAlert = 'A new player result was created';
            //change view to list
            $location.path("/matches");
        }, function error(response) {
            //display error
            $scope.errorAlert = 'Cannot create player result!';
        });
    };
    
});

soccerControllers.controller('NewMatchController', 
    function ($scope, $routeParams, $http, $location, $rootScope) {
        
    $http.get('/pa165/api/v1/teams').then(function(response) {
        
        var teams = response.data['_embedded']['teams'];             
        console.log('AJAX loaded all teams');  
        $scope.items = teams;

    }, function error(error) {
        console.log(error);
        $scope.errorAlert = error;
    });        

    //set object bound to form fields
    $scope.match = {
        'teamHome': 0,
        'teamAway': 0,
        'dateAndTime': null,
        'location': null,
        'teamHomeGoalsScored': 0,
        'teamAwayGoalsScored': 0,
        'teamHomeGoalsScoredHalf': 0,
        'teamAwayGoalsScoredHalf': 0       
    };
    
    // function called when submit button is clicked, creates match on server
    $scope.create = function (match) {
              
        alert(JSON.stringify(match));
        
        /*$http.get('/pa165/api/v1/teams/' + $scope.selectedTeamHomeId).then(function (response) {
            
        console.log(response);    
            
        $scope.match.teamHome = response.data;
        console.log('AJAX loaded detail of team ' + $scope.team.name);
    
    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });
    $http.get('/pa165/api/v1/teams/' + $scope.selectedTeamAwayId).then(function (response) {
            
        console.log(response);    
            
        $scope.match.teamAway = response.data;
        console.log('AJAX loaded detail of team ' + $scope.team.name);
    
    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });*/
    
        $http({
            method: 'POST',
            url: '/pa165/api/v1/matches/create',
            data: match
        }).then(function success(response) {
            console.log('created match');
            var created= response.data;
            //display confirmation alert
            $rootScope.successAlert = 'A new match was created';
            //change view to list
            $location.path("/matches");
        }, function error(response) {
            //display error
            $rootScope.errorAlert = 'Cannot create match!';
        });
    };
});



soccerControllers.controller('ResultsController', function ($scope, $rootScope, $http) {
    
    $http.get('/pa165/api/v1/results').then(function(response) {
        
        var results = response.data['_embedded']['playerResults'];             
        console.log('AJAX loaded all matches');  
        $scope.results = results;

    }, function error(error) {
        console.log(error);
        $rootScope.errorAlert = error.data.message;
    });
    
});


// defines new directive (HTML attribute "convert-to-int") for conversion between string and int
// of the value of a selection list in a form
// without this, the value of the selected option is always a string, not an integer
soccerControllers.directive('convertToInt', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function (val) {
                return parseInt(val, 10);
            });
            ngModel.$formatters.push(function (val) {
                return '' + val;
            });
        }
    };
});