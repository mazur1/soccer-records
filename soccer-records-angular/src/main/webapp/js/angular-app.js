'use strict';

/* Defines application and its dependencies */

var soccerRecordspApp = angular.module('soccerRecordspApp', ['ngRoute', 'soccerControllers']);
var soccerControllers = angular.module('soccerControllers', []);

/* Configures URL fragment routing, e.g. #/product/1  */
soccerRecordspApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/home', {templateUrl: 'partials/home.html', controller: 'DefaultController'}).
            when('/login', {templateUrl: 'partials/admin/forms/login.html', controller: 'LoginController'}).
            when('/teams', {templateUrl: 'partials/teams.html', controller: 'TeamsController'}).
            when('/players', {templateUrl: 'partials/players.html', controller: 'PlayersController'}).
            when('/matches', {templateUrl: 'partials/matches.html', controller: 'MatchesController'}).
            when('/results', {templateUrl: 'partials/results.html', controller: 'ResultsController'}).
            when('/teams/:teamId', {templateUrl: 'partials/detail/team.html', controller: 'TeamDetailController'}).
            when('/players/:playerId', {templateUrl: 'partials/detail/player.html', controller: 'PlayerDetailController'}).            
            when('/matches/:matchId', {templateUrl: 'partials/detail/match.html', controller: 'MatchDetailController'}).
            when('/newmatch', {templateUrl: 'partials/admin/new_match.html', controller: 'NewMatchController'}).
            when('/newplayerresult', {templateUrl: 'partials/admin/new_player_result.html'}).
            otherwise({redirectTo: '/home'});
    }]);

/*
 * alert closing functions defined in root scope to be available in every template
 */
soccerRecordspApp.run(function($rootScope) {
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    }; 

    $rootScope.loggedUser = {
        'name': "",
        'email': "",
        'logged': false
    };

});

/* Controllers */

/*
 * Public default interface
 */

/*
 * Shopping page with all categories and products
 */
soccerControllers.controller('DefaultController', function ($scope, $rootScope, $http) {
    
    console.log($rootScope.loggedUser);
    
    /*
    $http.get('api/v1/teams').then(function(response) {
        
        var teams = response.data['_embedded']['teams'];             
        console.log('AJAX loaded all teams');  
        $scope.teams = teams;

    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });
    
    */
    
});

soccerControllers.controller('LoginController', function ($scope, $routeParams, $http, $location, $rootScope) {
    
        //set object bound to form fields
        $scope.user = {
            'userName': '',
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

                $rootScope.loggedUser.name = "Karel";
                $rootScope.loggedUser.logged = true;  
            
                $("#login").attr('style', 'display: none !important');
                $("#logged").attr('style', 'display: block !important');     
                $("#logged").find("li").html('<a>Logged user: '+$rootScope.loggedUser.name + '</a>');  
                
                $location.path("/home");
                
            }, function error(response) {
                
                console.log(response);
                
                //display error
                $scope.errorAlert = 'Login failed!';
            });
        };
    
});


soccerControllers.controller('TeamsController', function ($scope, $http) {
    
    $http.get('/pa165/api/v1/teams').then(function(response) {        
        var teams = response.data['_embedded']['teams'];             
        console.log('AJAX loaded all teams');  
        $scope.teams = teams;
        
    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });
    
});

soccerControllers.controller('TeamDetailController', function ($scope, $routeParams, $http) {
    
    // get team id from URL fragment #/product/:productId
        
    var teamId = $routeParams.teamId;
    
    $http.get('/pa165/api/v1/teams/' + teamId).then(function (response) {
            
        console.log(response);    
            
        $scope.team = response.data;
        console.log('AJAX loaded detail of team ' + $scope.team.name);
    
    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });
});

soccerControllers.controller('PlayersController', function ($scope, $http) {
    
    $http.get('/pa165/api/v1/players').then(function(response) {
        
        var players = response.data['_embedded']['players'];             
        console.log('AJAX loaded all players');  
        $scope.players = players;

    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });
    
});

soccerControllers.controller('PlayerDetailController', function ($scope, $routeParams, $http) {
        // get team id from URL fragment #/product/:productId
        
        var playerId = $routeParams.playerId;
        $http.get('/pa165/api/v1/players/' + playerId).then(function (response) {
            $scope.player = response.data;
            console.log('AJAX loaded detail of team ' + $scope.player.name);
    
    }, function error(error) {
        //display error
        console.log(error);
        $scope.errorAlert = error;
    });
});

soccerControllers.controller('MatchesController', function ($scope, $http) {
    
    $http.get('/pa165/api/v1/matches').then(function(response) {
        
        var matches = response.data['_embedded']['matches'];             
        console.log('AJAX loaded all matches');  
        $scope.matches = matches;

    }, function error(error) {
        console.log(error);
        $scope.errorAlert = error;
    });
    
});

soccerControllers.controller('MatchDetailController', function ($scope, $routeParams, $http) {
                
        var matchId = $routeParams.matchId;
        $http.get('/pa165/api/v1/matches/' + matchId).then(function (response) {
            $scope.match = response.data;
            console.log('AJAX loaded detail of match ' + $scope.match.name);
    
    }, function error(error) {
        console.log(error);
        $scope.errorAlert = error;
    });
    
    $scope.IsHidden = true;
    $scope.ShowHide = function () {
        $scope.IsHidden = $scope.IsHidden ? false : true;
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
        'teamHome': '',
        'teamAway': '',
        'dateAndTime': '',
        'location': ''
    };
    // function called when submit button is clicked, creates match on server
    $scope.create = function (match) {
        
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
            $rootScope.successAlert = 'A new match "'+created.name+'" was created';
            //change view to list
            $location.path("/matches");
        }, function error(response) {
            //display error
            $scope.errorAlert = 'Cannot create match!';
        });
    };
});

soccerControllers.controller('ResultsController', function ($scope, $http) {
    
    $http.get('/pa165/api/v1/results').then(function(response) {
        
        var results = response.data['_embedded']['playerResults'];             
        console.log('AJAX loaded all matches');  
        $scope.results = results;

    }, function error(error) {
        console.log(error);
        $scope.errorAlert = error;
    });
    
});

/*
// helper procedure loading products to category
function loadCategoryProducts($http, category, prodLink) {
    $http.get(prodLink).then(function (response) {
        category.products = response.data['_embedded']['products'];
        console.log('AJAX loaded ' + category.products.length + ' products to category ' + category.name);
    });
}

soccerControllers.controller('ProductDetailCtrl',
    function ($scope, $routeParams, $http) {
        // get product id from URL fragment #/product/:productId
        var productId = $routeParams.productId;
        $http.get('/eshop/api/v1/products/' + productId).then(function (response) {
            $scope.product = response.data;
            console.log('AJAX loaded detail of product ' + $scope.product.name);
        });
    });

soccerControllers.controller('CategoryDetailCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        var categoryId = $routeParams.categoryId;
        $http.get('/eshop/api/v1/categories/' + categoryId).then(function (response) {
            var category = response.data;
            $scope.category = category;
            console.log('AJAX loaded detail of category ' + category.name);
            loadCategoryProducts($http, category, category['_links'].products.href);
        });
    }]);

function loadAdminProducts($http, $scope) {
    $http.get('/eshop/api/v1/products').then(function (response) {
        $scope.products = response.data._embedded.products;
        console.log('AJAX loaded all products ');
    });
}
soccerControllers.controller('AdminProductsCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        //initial load of all products
        loadAdminProducts($http, $scope);
        // function called when Delete button is clicked
        $scope.deleteProduct = function (product) {
	        console.log("deleting product with id=" + product.id + ' (' + product.name + ')');
            $http.delete(product._links.delete.href).then(
                function success(response) {
                    console.log('deleted product ' + product.id + ' on server');
                    //display confirmation alert
                    $rootScope.successAlert='Deleted product "'+product.name+'"';
                    //load new list of all products
                    loadAdminProducts($http, $scope);
                },
                function error(response) {
                    console.log('server returned error');
                    $rootScope.errorAlert = 'Cannot delete product "'+product.name+'"! It is used in an order.';
                }
            );
        };
    });

soccerControllers.controller('AdminNewProductCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //prepare data for selection lists
        $scope.colors = ['RED', 'GREEN', 'BLUE', 'BLACK'];
        $scope.currencies = ['CZK', 'EUR', 'USD'];
        //get categories from server
        $http.get('/eshop/api/v1/categories/').then(function (response) {
            $scope.categories = response.data['_embedded']['categories'];
        });
        //set object bound to form fields
        $scope.product = {
            'name': '',
            'description': '',
            'categoryId': 1,
            'price': 0,
            'color': $scope.colors[1],
            'currency': $scope.currencies[0]
        };
        // function called when submit button is clicked, creates product on server
        $scope.create = function (product) {
            $http({
                method: 'POST',
                url: '/eshop/api/v1/products/create',
                data: product
            }).then(function success(response) {
                console.log('created product');
                var createdProduct= response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new product "'+createdProduct.name+'" was created';
                //change view to list of products
                $location.path("/admin/products");
            }, function error(response) {
                //display error
                $scope.errorAlert = 'Cannot create product !';
            });
        };
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

*/