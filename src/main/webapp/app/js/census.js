var app = angular.module("myApp", ['ngResource', 'ngCookies']);

app.config(function ($httpProvider, $routeProvider) {
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
	
    //Define url routing
    $routeProvider.
    when('/', {templateUrl: 'templates/census.html',   controller: "CensusCtrl"}).
    otherwise({redirectTo: '/'});
});


/**
 * Census service
 */
app.factory('Census', function($resource){
	return $resource('/census/rest/json/case/census/:caseId', {}, {
	});
});

/**
 * Enrollee service
 */
app.factory('Enrollee', function($resource){
	return $resource('/census/rest/json/case/census/:caseId/enrollee/:enrolleeId', {'enrolleeId':0}, {
		
	});
});   

/**
 * Dependent service
 */
app.factory('Dependent', function($resource){
	return $resource('/census/rest/json/case/census/:caseId/enrollee/:enrolleeId/dependent/:dependentId', {'dependentId':0}, {
		
	});
}); 


app.controller("CensusCtrl", function($scope, Census, Enrollee) {
	$scope.model = {caseId:1, census:Census.get({caseId:1})};
	$scope.enrollee = {};
	
	$scope.getCase = function() {
		$scope.model.census = Census.get({caseId:$scope.model.caseId});
	};
	
	$scope.addEnrollee = function() {
		Enrollee.save({caseId:$scope.model.caseId}, $scope.enrollee, function(data){
			$scope.model.census.population.push($scope.enrollee);
			$scope.enrollee = {};
		});
	};
	
});




