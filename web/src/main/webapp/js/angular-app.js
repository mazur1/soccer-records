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
            //when('/results/:resultId', {templateUrl: 'partials/detail/player_result.html', controller: 'PlayerResultDetailController'}).
            when('/newPlayer', {templateUrl: 'partials/admin/new_player.html', controller: 'NewPlayerController'}).
            when('/newmatch', {templateUrl: 'partials/admin/new_match.html', controller: 'NewMatchController'}).
            when('/newteam', {templateUrl: 'partials/admin/new_team.html', controller: 'NewTeamController'}).
            when('/editteam/:teamId', {templateUrl: 'partials/admin/edit_team.html', controller: 'EditTeamController'}).
            when('/editplayer/:playerId', {templateUrl: 'partials/admin/edit_player.html', controller: 'EditPlayerController'}).
            when('/editmatch/:matchId', {templateUrl: 'partials/admin/edit_match.html', controller: 'EditMatchController'}).
            when('/newplayerresult', {templateUrl: 'partials/admin/new_player_result.html'}).
            when('/editplayerresult/:playerResultId', {templateUrl: 'partials/admin/edit_player_result.html'}).
            otherwise({redirectTo: '/home'});
    }]);

/*
 * alert closing functions defined in root scope to be available in every template
 */
soccerRecordspApp.run(function ($rootScope, $cookieStore) {

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

    if ($cookieStore.get('MsgRedirect') !== undefined) {
        setMessage($rootScope, "success", $cookieStore.get('MsgRedirect'));
        $cookieStore.remove('MsgRedirect');
    }

});

function setMessage($rootScope, type, msg) {
    switch (type) {
        case "success":
            $rootScope.successAlert = msg;
            setTimeout(function () {
                $(".alert-success").toggle(200);
            }, 5000);
            break;
        case "warning":
            $rootScope.warningAlert = msg;
            setTimeout(function () {
                $(".alert-warning").toggle(200);
            }, 5000);
            break;
        case "error":
            $rootScope.errorAlert = msg;
            setTimeout(function () {
                $(".alert-danger").toggle(200);
            }, 5000);
            break;
        default:
            break;
    }
}

function parseUserData($cookieStore, $rootScope) {

    if ($cookieStore.get('user') === undefined) {
        $cookieStore.put('user', {
            'name': "",
            'email': "",
            'logged': false
        });
    }

    $rootScope.loggedUser = $cookieStore.get('user');

    if ($rootScope.loggedUser.logged) {
        $("#login").find("li").html('<a>Logged user: ' + $rootScope.loggedUser.name + '</a>');
        $("#login").find("ul").append('<li><a href="#!/logout">Log out</a></li>')
    }

}

function userIsLogged(cookieStore) {
    var user = cookieStore.get('user');
    if (user !== undefined) {
        return user.logged;
    }
    return false;
}

/* Controllers */

/* Public default interface */

soccerControllers.controller('DefaultController', function ($scope, $rootScope, $http) {
    // Home controller
});

soccerControllers.controller('LoginController', function ($scope, $routeParams, $http, $location, $rootScope, $cookieStore) {

    //set object bound to form fields
    $scope.user = {
        'usernname': '',
        'password': ''
    };

    // function called when submit button is clicked, creates product on server
    $scope.login = function (user) {

        $http({
            method: 'POST',
            url: '/pa165/api/v1/users/login/',
            data: user
        }).then(function success(response) {

            $cookieStore.remove('user');

            $cookieStore.put('user', {
                'name': response.data.username,
                'email': "",
                'logged': true
            });

            parseUserData($cookieStore, $rootScope);
            setMessage($rootScope, "success", 'Login succesfull');

            window.location.href = "/pa165/#!/home"

        }, function error(response) {
            setMessage($rootScope, "error", 'Login failed!');
        });
    };

});

soccerControllers.controller('LogoutController', function ($scope, $routeParams, $http, $location, $rootScope, $cookieStore) {

    $cookieStore.remove('user');
    $cookieStore.put('MsgRedirect', "Logout succesfull");
    
    var parts = window.location.href.split("#");
    window.location.href = parts[0];

});

soccerControllers.controller('TeamsController', function ($scope, $rootScope, $http) {

    function loadTeams(){
        $http.get('/pa165/api/v1/teams').then(function (response) {
            var teams = response.data['_embedded']['teams'];
            console.log('AJAX loaded all teams');
            $scope.teams = teams;
        }, function error(error) {
            setMessage($rootScope, "error", error.data.message);
        });
    }
    
    loadTeams();

    $scope.deleteTeam = function(team){
        
        if(confirm("Do you really want remove team: "+team.name+"?")){
            $http({
                method: 'DELETE',
                url: '/pa165/api/v1/teams/' + team.id,
            }).then(function (response) {
                setMessage($rootScope, "success", "Team "+team.name+" succesfuly deleted");
                loadTeams();
            },function (response) {
                setMessage($rootScope, "error", "Team "+team.name+" deletion failed");
            });
        }

    }

});

soccerControllers.controller('EditTeamController', function ($scope, $window, $rootScope, $routeParams, $http) {

    var teamId = $routeParams.teamId;

    $http.get('/pa165/api/v1/teams/' + teamId).then(function (response) {
        $scope.team = response.data;
        console.log('AJAX loaded detail of team ' + $scope.team.name);
    }, function error(error) {
        setMessage($rootScope, "error", error.data.message);
    });

    $scope.editTeam = function (team) {

        $http({
            method: 'PUT',
            url: '/pa165/api/v1/teams/' + teamId,
            data: team
        }).then(function (response) {
            setMessage($rootScope, "success", "Team succesfuly edited");
            $window.location = '/pa165/#!/teams';
        },function (response) {
            setMessage($rootScope, "error", "Team edit failed");
        });
    };

});


soccerControllers.controller('NewTeamController', function ($scope, $rootScope, $window, $http) {

    $scope.create = function (team) {

        $http({
            method: 'POST',
            url: '/pa165/api/v1/teams/create',
            data: team
        }).then(function (response) {
            setMessage($rootScope, "success", "Team succesfuly created");
            $window.location = '/pa165/#!/teams';
        },function (response) {
            setMessage($rootScope, "success", "New team create failed");
        });
    }
});

soccerControllers.controller('TeamDetailController', function ($scope, $window, $routeParams, $http, $rootScope) {

    var teamId = $routeParams.teamId;

    $http.get('/pa165/api/v1/teams/' + teamId).then(function (response) {
        $scope.team = response.data;
        console.log('AJAX loaded detail of team ' + $scope.team.name);
        $scope.matches = $scope.team.matchesHome.concat($scope.team.matchesAway);
        formatDates($scope.matches);
        
    }, function error(error) {
        setMessage($rootScope, "error", error.data.message);
    });

    $scope.deleteTeam = function () {
        $http.delete('/pa165/api/v1/teams/' + teamId).then(function success(response) {
            setMessage($rootScope, "success", "A team was deleted");
            $window.location = '/pa165/#!/teams';
        }, function error(response) {
            setMessage($rootScope, "error", "Cannot delete team!");
        });
    };
});

soccerControllers.controller('PlayersController', function ($scope, $rootScope, $http) {

    function loadPlayers(){
        $http.get('/pa165/api/v1/players').then(function (response) {
            var players = response.data['_embedded']['players'];
            console.log('AJAX loaded all players');
            $scope.players = players;
        }, function error(error) {
            setMessage($rootScope, "error", error.data.message);
        });        
    }
    
    loadPlayers();

    $scope.deletePlayer = function(player){
        
        if(confirm("Do you really want remove player: "+player.name+" "+player.surname+"?")){
            $http({
                method: 'DELETE',
                url: '/pa165/api/v1/players/' + player.id,
            }).then(function (response) {
                setMessage($rootScope, "success", "Player "+player.name+" "+player.surname+" succesfuly deleted");
                loadPlayers();
            },function (response) {
                setMessage($rootScope, "error", "Player "+player.name+" "+player.surname+" deletion failed");
            });
        }

    }
    
});

soccerControllers.controller('EditPlayerController', function ($scope, $window, $rootScope, $routeParams, $http) {

    $scope.posts = ['ATTACKER', 'MIDFIELDER', 'DEFENDER', 'GOLMAN'];

    $http.get('/pa165/api/v1/teams/').then(function (response) {
        $scope.teams = response.data['_embedded']['teams'];
    });

    var playerId = $routeParams.playerId;

    $http.get('/pa165/api/v1/players/' + playerId).then(function (response) {
        $scope.player = response.data;
        console.log('AJAX loaded detail of player ' + $scope.player.name);
    }, function error(error) {
        setMessage($rootScope, "error", error.data.message);
    });

    $scope.editPlayer = function (player) {

        $http({
            method: 'PUT',
            url: '/pa165/api/v1/players/' + playerId,
            data: player
        }).then(function (response) {
            $window.location = '/pa165/#!/players';
        },function (response) {
            setMessage($rootScope, "error", 'Player edit failed!');
        });
    };

});

soccerControllers.controller('PlayerDetailController', function ($scope, $window, $rootScope, $routeParams, $http) {

    var playerId = $routeParams.playerId;

    $http.get('/pa165/api/v1/players/' + playerId).then(function (response) {
        $scope.player = response.data;
        var scored = 0;
        for(var i = 0; i < $scope.player.playerResults.length; i++) {
            scored = scored + $scope.player.playerResults[i].goalsScored;
        }
        $scope.scored = scored;
    }, function error(error) {
        setMessage($rootScope, "error", error.data.message);
    });

    $scope.deletePlayer = function () {
        $http.delete('/pa165/api/v1/players/' + playerId).then(function success(response) {
            setMessage($rootScope, "success", 'A player was deleted');
            $window.location = '/pa165/#!/players';
        }, function error(response) {
            setMessage($rootScope, "error", 'Cannot delete player!');
        });
    };
});

soccerControllers.controller('NewPlayerController', function ($scope, $location, $rootScope, $window, $http) {

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
        'teamId': null
    };

    $scope.create = function (player) {

        $http({
            method: 'POST',
            url: '/pa165/api/v1/players/create/',
            data: player
        }).then(function success(response) {
            setMessage($rootScope, "success", "Player succesfuly created");
            $location.path("/players");
        }, function error(response) {
            setMessage($rootScope, "error", "new player create failed");
        });

    }
});

soccerControllers.controller('MatchesController', function ($scope, $rootScope, $http, $filter) {


    function loadMatches(){
        $http.get('/pa165/api/v1/matches').then(function (response) {
            var matches = response.data['_embedded']['matches'];
            console.log('AJAX loaded all matches');
            $scope.matches = matches;
            formatDates($filter,$scope.matches,false);
        }, function error(error) {
            setMessage($rootScope, "error", error.data.message);
        });
    }
    
    loadMatches();

    $scope.deleteMatch = function(match){
        
        if(confirm("Do you really want remove match: "+match.teamHome.name+" x "+match.teamAway.name+" "+match.dateAndTime+"?")){
            $http({
                method: 'DELETE',
                url: '/pa165/api/v1/players/' + match.id,
            }).then(function (response) {
                setMessage($rootScope, "success", "Match "+match.teamHome.name+" x "+match.teamAway.name+" "+match.dateAndTime+" succesfuly deleted");
                loadMatches();
            },function (response) {
                setMessage($rootScope, "error", "Match "+match.teamHome.name+" x "+match.teamAway.name+" "+match.dateAndTime+" deletion failed");
            });
        }

    }

});

soccerControllers.controller('MatchDetailController', function ($scope, $rootScope, $routeParams, $http, $location, $route, $filter) {

    var matchId = $routeParams.matchId;
    
    $http.get('/pa165/api/v1/matches/' + matchId).then(function (response) {
        $scope.match = response.data;
        formatDate($filter, $scope.match, false);
        console.log('AJAX loaded detail of match ' + $scope.match.toString());
        
        $http.get('/pa165/api/v1/teams/'+$scope.match.teamHome.id).then(function(response) {    
        var teamHome = response.data;             
        console.log('AJAX loaded all players');  
        $scope.playersHome = teamHome.players;
        
        });
        $http.get('/pa165/api/v1/teams/'+$scope.match.teamAway.id).then(function(response) {    
        var teamAway = response.data;             
        console.log('AJAX loaded all players');  
        $scope.playersAway = teamAway.players;
        
        });

    }, function error(error) {
        setMessage($rootScope, "error", error.data.message);
    });

    $scope.deleteMatch = function () {
        $http.delete('/pa165/api/v1/matches/' + matchId).then(function success(response) {
            setMessage($rootScope, "success", "A match was deleted");
            $location.path("/matches");
        }, function error(response) {
            setMessage($rootScope, "error", "Cannot delete match!");
        });
    };

    $scope.deleteResult = function (resultId) {
        $http.delete('/pa165/api/v1/results/' + resultId).then(function success(response) {
            setMessage($rootScope, "success", "A result was deleted");
            $route.reload();
        }, function error(response) {
            setMessage($rootScope, "error", "Cannot delete match!");
        });
    };

    $scope.IsHidden = true;
    $scope.ShowHide = function () {
        $scope.IsHidden = $scope.IsHidden ? false : true;
    };
    $scope.IsHiddenEdit = true;
    $scope.ShowHideEdit = function (playerResult) {
        $scope.IsHiddenEdit = false;
        // player result in child scope
        $http.get('/pa165/api/v1/results/' + playerResult.id).then(function (response) {
        $scope.playerResultE = response.data;
        console.log('AJAX loaded detail of match ' + $scope.match.toString());
        
    }, function error(error) {
        setMessage($rootScope, "error", error.data.message);
    });
    };

    //set object bound to form fields
    $scope.playerResult = {
        'matchId': matchId,
        'playerId': null,
        'goalsScored': 0
    };
    // function called when submit button is clicked, creates match on server
    $scope.create = function (playerResult) {

        $http({
            method: 'POST',
            url: '/pa165/api/v1/results/create',
            data: playerResult
        }).then(function success(response) {
            var created = response.data;
            setMessage($rootScope, "success", "A new player result was created");
            $route.reload();
        }, function error(response) {
            setMessage($rootScope, "error", "Cannot create player result!");
        });
    };
    
    $scope.editResult = function (result) {
        
        var resultData = {
        'playerId' : result.player.id,
        'matchId' : result.match.id,
        'goalsScored': result.goalsScored
        };
        
        $http({
            method: 'PUT',
            url: '/pa165/api/v1/results/' + result.id,
            data: resultData
        })
        .then(function success(response) {
            console.log('result succesfuly edited');
            $route.reload();
        }, 
        function error(response) { 
            console.log('result edit failed'); 
            $rootScope.errorAlert = "result edit failed";
        });
        
    };
    
    $scope.updateMatchScore = function () {
        
        $http.put('/pa165/api/v1/matches/' + matchId + '/score')
        .then(function success(response) {
            console.log('match succesfuly edited');
            $route.reload();
        }, 
        function error(response) { 
            console.log('result edit failed'); 
            $rootScope.errorAlert = "result edit failed";
        });
        
    };

});

soccerControllers.controller('NewMatchController', function ($scope, $routeParams, $http, $location, $rootScope, $filter) {

    $http.get('/pa165/api/v1/teams').then(function (response) {
        var teams = response.data['_embedded']['teams'];
        console.log('AJAX loaded all teams');
        $scope.items = teams;
    }, function error(error) {
        $scope.errorAlert = error;
    });

    //set object bound to form fields
    $scope.match = {
        'teamHomeId': null,
        'teamAwayId': null,
        'dateAndTime': null,
        'location': null,
        'teamHomeGoalsScored': 0,
        'teamAwayGoalsScored': 0,
        'teamHomeGoalsScoredHalf': 0,
        'teamAwayGoalsScoredHalf': 0
    };

    // function called when submit button is clicked, creates match on server
    $scope.create = function (match) {

        formatDate($filter, match);
        
        $http({
            method: 'POST',
            url: '/pa165/api/v1/matches/create',
            data: match
        }).then(function success(response) {
            var created = response.data;
            setMessage($rootScope, "success", "A new match was created");
            $location.path("/matches");
        }, function error(response) {
            setMessage($rootScope, "error", "Cannot create match!");
        });
        
    };
});

soccerControllers.controller('EditMatchController', function ($scope, $window, $rootScope, $routeParams, $http, $filter) {
    
    var matchId = $routeParams.matchId;
    
    $http.get('/pa165/api/v1/matches/' + matchId).then(function (response) {   
        $scope.match = response.data;
        formatDate($filter, $scope.match, false); 
        console.log('AJAX loaded detail of match ' + $scope.match.id);
    }, function error(error) {
        //display error
        $rootScope.errorAlert = error.data.message;
    });
    
    $scope.editMatch = function (match) {
        formatDate($filter, match);
//        var matchData = {
//        'teamHomeId': match.teamHome.id,
//        'teamAwayId': match.teamAway.id,
//        'dateAndTime': match.dateAndTime,
//        'location': match.location,
//        'teamHomeGoalsScored': match.teamHomeGoalsScored,
//        'teamAwayGoalsScored': match.teamAwayGoalScored,
//        'teamHomeGoalsScoredHalf': match.teamHomeGoalsScoredHalf,
//        'teamAwayGoalsScoredHalf': match.teamAwayGoalScoredHalf
//        };
    
        $http({
                method: 'PUT',
                url: '/pa165/api/v1/matches/' + matchId,
                data: match
        })
        .then(function(response) {
            console.log('match succesfuly edited');
            $window.location='/pa165/#!/matches';
        
        }, 
        function(response) { 
            console.log('match edit failed'); 
            $rootScope.errorAlert = "match edit failed";
        });
    };
    
});


soccerControllers.controller('ResultsController', function ($scope, $rootScope, $http) {

    function loadResults(){
        $http.get('/pa165/api/v1/results').then(function (response) {
            var results = response.data['_embedded']['playerResults'];
            console.log('AJAX loaded all matches');
            $scope.results = results;
            
            console.log(results);
            
        }, function error(error) {
            setMessage($rootScope, "error", error.data.message);
        });
    }
    
    loadResults();
    
    $scope.deleteResult = function(result){
        
        if(confirm("Do you really want remove result?")){
            $http({
                method: 'DELETE',
                url: '/pa165/api/v1/players/' + result.id,
            }).then(function (response) {
                setMessage($rootScope, "success", "Player result succesfuly deleted");
                loadMatches();
            },function (response) {
                setMessage($rootScope, "error", "Player result deletion failed");
            });
        }

    }

});

soccerControllers.controller('EditPlayerResultController', function ($scope, $window, $rootScope, $routeParams, $http) {
    
    var resultId = $routeParams.resultId;
    
    $http.get('/pa165/api/v1/results/' + resultId).then(function (response) {
            
        console.log(response);    
            
        $scope.result = response.data;
        console.log('AJAX loaded detail of result ' + $scope.result.id);
    
    }, function error(error) {
        //display error
        $rootScope.errorAlert = error.data.message;
    });
    
    $scope.editResult = function (result) {
        
        $http({
                method: 'PUT',
                url: '/pa165/api/v1/results/' + resultId,
                data: result
        })
        .then(function(response) {
            console.log('result succesfuly edited');
            $window.location='/pa165/#!/results';
        
        }, 
        function(response) { 
            console.log('result edit failed'); 
            $rootScope.errorAlert = "result edit failed";
        });
    };
    
});

function formatDates(filter, matches, db) {
    
    db = typeof db !== 'undefined' ? db : true;
    
    for (var i = 0; i < matches.length; ++i) {
        formatDate(filter,matches[i], db);
    }
}
function formatDate(filter, match, db) {
    
    db = typeof db !== 'undefined' ? db : true;
    
    if(db){
        match.dateAndTime = filter('date')(new Date(match.dateAndTime),'yyyy-MM-dd HH:mm');
    } else {
        match.dateAndTime = new Date(match.dateAndTime);
    }
    
    //alert(match.dateAndTime);
    
}

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