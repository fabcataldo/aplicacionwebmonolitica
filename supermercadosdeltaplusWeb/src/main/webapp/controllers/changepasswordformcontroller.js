angular.module('moduloPrincipal')
.controller('ChangePasswordFormController', ['$rootScope', '$scope', '$log', '$uibModal', 'usuariosService', 'URL_API_BASE', '$http', ChangePasswordFormController]);

function ChangePasswordFormController($rootScope, $scope, $log, $uibModal, usuariosService, URL_API_BASE, $http) {
	$scope.userbd={};
	$scope.modifieduser={};
	$scope.bandera=0;
	$scope.actualusername=$rootScope.user.name;
	
	usuariosService.list().then(
			function(respuesta){			
				if(respuesta.status===200){
					for(i=0;i<respuesta.data.length;i++){						
						if(respuesta.data[i].username==$scope.actualusername){
							$scope.userbd=respuesta.data[i];
							break;
						}
					}
					$scope.modifieduser=$scope.userbd;					
					$scope.bandera=1;
				}
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