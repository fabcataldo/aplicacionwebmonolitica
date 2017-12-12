angular.module('moduloPrincipal')
.controller('ChangePasswordFormController', ['$rootScope', '$scope', '$log', '$uibModalInstance', '$uibModal', 'usuariosService', ChangePasswordFormController]);

function ChangePasswordFormController($rootScope, $scope, $log, $uibModalInstance, $uibModal, usuariosService, coreService) {
	$scope.edituser = {
			//info nueva
			newpassword : "",
			
			//info de la bd que tengo que conservar
			username : $rootScope.user.name,
			idUser : 0,
			firstName: "",
			lastName: "",
			email: "",
			credentialsExpired: "",
			accountLocked: "",
			accountExpired: "",
			accountEnabled: ""
	};
	
	$scope.changePassword = function () {
		//guardo el resto de la info del usuario en edituser
		//tengo que conservar el resto de la información del usuario que estoy cambiandole el password
		usuariosService.list().then(
				function(respuesta){
					if(respuesta.status===200){
						respuesta.forEach(function(o,i){
							if(o.username==$scope.edituser.username) {
								o.idUser[i]=edituser.idUser;
								o.firstName[i]=edituser.firstName;
								o.lastName[i]=edituser.lastName;
								o.email[i]=edituser.email;
								o.credentialsExpired[i]=edituser.credentialsExpired;
								o.accountLocked[i]=edituser.accountLocked;
								o.accountExpired[i]=edituser.accountExpired;
								o.accountEnabled[i]=edituser.accountEnabled;
							}
						});
					}
				},
				function(respuestaerronea){
					$scope.userbd={};
				}
		); 
		//ahora llamo al método edit() de usuariosService, enviandolé el usuario ya modificado
		usuariosService.edit($scope.edituser).then(
			function(resp){ 
				if(resp.status===200) {
					$uibModalInstance.dismiss();
					$rootScope.authenticated=false;
					$rootScope.logout();
					$rootScope.openLoginForm();
				}
			},
			function(respErr){
				$log.log(respErr);
			}
		);
	  };
}
