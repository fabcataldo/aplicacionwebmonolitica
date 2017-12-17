angular.module('moduloPrincipal')
.controller('ChangePasswordFormController', ['$rootScope', '$scope', '$log', '$uibModal', 'usuariosService', 'URL_API_BASE', '$http', '$routeParams', 'recoverPasswordService', 'coreService', ChangePasswordFormController]);

function ChangePasswordFormController($rootScope, $scope, $log, $uibModal, usuariosService, URL_API_BASE, $http, $routeParams, recoverPasswordService, coreService) {
	$scope.userbd={};
	$scope.modifieduser={};
	$scope.bandera=0;
	$scope.actualusername=$rootScope.user.name;
	
	if($routeParams.token!=undefined){
		var tokenValue = $routeParams.token;
		console.log(tokenValue);
		recoverPasswordService.loginTemporarly(tokenValue).then(
				function(resp){ 
					if(resp.status===404){
						console.log("PISO ACAAA");
						coreService.pingAuth().then(
								function(resp){   
									  console.log(resp);
									  if(resp.status===200 && resp.data.code==0) {
										  $rootScope.user.name=resp.data.username;
										  $rootScope.authenticated=true;
										  $rootScope.regularCall=true;
										  console.log($rootScope.user);
									  } else {
										  
									  }
									});
						/*var modalInstance = $uibModal.open({
							animation : true,
							backdrop: false,
							ariaLabelledBy : 'modal-title',
							ariaDescribedBy : 'modal-body',
							templateUrl : 'views/recoverPasswordOK.html',
							controller : 'recoverPasswordOKController',
							controllerAs : '$ctrlrecover',
							size : 'lg',
						});
						modalInstance.result.then(function() {
										
						}, function() {
							
						});  */
					}
				},
				function(respErr){
					$log.log(respErr);
				}
		);
	}
	
	usuariosService.list().then(
			function(respuesta){			
				if(respuesta.status===200){
					for(i=0;i<respuesta.data.length;i++){						
						if(respuesta.data[i].username==$rootScope.user.name){
							$scope.userbd=respuesta.data[i];
							break;
						}
					}
					$scope.modifieduser=$scope.userbd;					
					$scope.bandera=1;
				}
				console.log("LLAMOOO A LIST() DE USERS");
				console.log($scope.modifieduser);
			},
			function(respuestaerronea){
				$scope.userbd={};
			}
	);
	


	$scope.changePassword = function () {	
		if($scope.bandera==1){
			usuariosService.edit($scope.modifieduser).then(
				function(resp){ 
					if(resp.status===200) {
						$rootScope.authenticated=false;
						$rootScope.logout();
						$rootScope.openLoginForm();
					}
				},
				function(respErr){
					if(resp.status==500)
						$log.log(respErr);
				}
			);
		}	
	}
}