var app = angular.module("myApp", ['ngResource', 'ngCookies', '$strap.directives']);

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
		update: {
		      method: 'PUT'
		}
	});
});   

/**
 * Dependent service
 */
app.factory('Dependent', function($resource){
	return $resource('/census/rest/json/case/census/:caseId/enrollee/:enrolleeId/dependent/:dependentId', {'dependentId':0}, {
		update: {
		      method: 'PUT'
		}
	});
}); 


app.controller("CensusCtrl", function($scope, Census, Enrollee, Dependent) {
	$scope.model = {caseId:1, census:Census.get({caseId:1})};
	$scope.enrollee = {};
	$scope.genderOptions = [{value:'M', name:'Male'}, {value:'F', name:'Female'}];
	$scope.dependentTypeOptions = [{value:'Spouse', name:'Spouse'}, {value:'Child', name:'Child'}];
	
	$scope.getCase = function() {
		$scope.model.census = Census.get({caseId:$scope.model.caseId});
	};
	
	$scope.saveEnrollee = function(isAdd) { 
		if (isAdd) {
			Enrollee.save({caseId:$scope.model.caseId}, $scope.enrollee, function(){
				$scope.getCase();
				$scope.enrollee = {};
			});
		} else {
			Enrollee.update({caseId:$scope.model.caseId}, $scope.enrollee, function(){
				$scope.getCase();
				$scope.enrollee = {};
			});
		}
		
	};
	
	$scope.saveDependent = function(enrollee) { 
		if (enrollee.dependent.id == null) {
			Dependent.save({caseId:$scope.model.caseId, enrolleeId:enrollee.id}, enrollee.dependent, function(){
				$scope.getCase();
				enrollee.dependent = {};
			});
		} else {
			Dependent.update({caseId:$scope.model.caseId, enrolleeId:enrollee.id}, enrollee.dependent, function(){
				$scope.getCase();
				enrollee.dependent = {};
			});
		}
		
	};
	
	
	$scope.deleteEnrollee = function(enrollee) { 
		Enrollee.remove({caseId:$scope.model.caseId, enrolleeId:enrollee.id}, function(){
			var i = $scope.model.census.population.indexOf(enrollee);
			if(i != -1) {
				$scope.model.census.population.splice(i, 1);
			}
			$scope.enrollee = {};
		});
		
	};
	
	
	$scope.deleteDependent = function(enrollee, dependent) { 
		Dependent.remove({caseId:$scope.model.caseId, enrolleeId:enrollee.id, dependentId:dependent.id}, function(){
			var i = enrollee.enrolleeDependents.indexOf(dependent);
			if(i != -1) {
				enrollee.enrolleeDependents.splice(i, 1);
			}
			enrollee.dependent = {};
		});
		
	};
	
	
	$scope.editEnrollee = function(selectedEnrollee) {
		$scope.enrollee = selectedEnrollee;	
	};
	
	$scope.editDependent = function(selectedEnrollee, selectedDependent) {
		selectedEnrollee.dependent = selectedDependent;
	};
	
	$scope.cancelEditEnrollee = function() {
		$scope.enrollee = {};
	};
	
	$scope.cancelEditDependent = function(enrollee) {
		enrollee.dependent = {};
	};
	
});




