angular.module('moduloPrincipal')
.controller('ChangePasswordFormController', ['$rootScope', '$scope', '$log', '$uibModal', 'usuariosService', 'URL_API_BASE', '$http', ChangePasswordFormController]);

function ChangePasswordFormController($rootScope, $scope, $log, $uibModal, usuariosService, URL_API_BASE, $http) {
	$scope.edituser={};
	$scope.edituser.username="";
	$scope.edituser.newpassword="";
	$scope.edituser.idUser="34";
	$scope.edituser.firstName="xx";
	$scope.edituser.lastName="xx";
	$scope.edituser.email="xx";
	$scope.edituser.credentialsExpired=false;
	$scope.edituser.accountLocked=false;
	$scope.edituser.accountExpired=false;
	$scope.edituser.accountEnabled=true;
	
	
	$scope.changePassword = function () {
	//guardo el resto de la info del usuario en edituser
	//tengo que conservar el resto de la información del usuario que estoy cambiandole el password
		usuariosService.list().then(
				function(respuesta){			
					if(respuesta.status===200){
						for(i=0;i<respuesta.data.length;i++){						
							if(respuesta.data[i].username==$scope.edituser.username){
								$scope.edituser.idUser=respuesta.data[i].idUser;
								$scope.edituser.firstName=respuesta.data[i].firstName;
								$scope.edituser.lastName=respuesta.data[i].lastName;
								$scope.edituser.email=respuesta.data[i].email;
								$scope.edituser.credentialsExpired=respuesta.data[i].credentialsExpired;
								$scope.edituser.accountLocked=respuesta.data[i].accountLocked;
								$scope.edituser.accountExpired=respuesta.data[i].accountExpired;
								$scope.edituser.accountEnabled=respuesta.data[i].accountEnabled;	
							}
						}

						//ahora llamo al método edit() de usuariosService, enviandolé el usuario ya modificado
						var req = {
							method: 'PUT',
							url: URL_API_BASE+'usuarios',
							headers : { 'Content-Type': 'application/json' },
							data: "{\"idUser\":\""+$scope.edituser.idUser+"\",\"email\": \"" 
								+$scope.edituser.email+"\",\"firstName\": \"" 
								+$scope.edituser.firstName+ "\",\"lastName\": \"" 
								+$scope.edituser.lastName+"\",\"credentialsExpired\": " 
								+$scope.edituser.credentialsExpired+",\"accountExpired\": " 
								+$scope.edituser.accountExpired+",\"accountLocked\": " 
								+$scope.edituser.accountLocked+",\"accountEnabled\": " 
								+$scope.edituser.accountEnabled+",\"username\": \"" 
								+$scope.edituser.username+"\",\"password\": \"" 
								+$scope.edituser.newpassword+"\"}"
						};
						$http(req).then(
							function(resp){ 
								if(resp.status===200|| resp.status===500) {
									$rootScope.authenticated=false;
									$rootScope.logout();
									$rootScope.openLoginForm();
								}
							},
							function(respErr){
								$log.log(respErr);
							}
						);
					}
				},
				function(respuestaerronea){
					$scope.edituser={};
				}
		); 
	  };
}