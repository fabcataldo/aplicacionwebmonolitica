angular.module('moduloPrincipal')
.controller('LoginFormController', ['$rootScope', '$scope', '$log', '$uibModalInstance', 'coreService','user', '$uibModal', LoginFormController]);

function LoginFormController($rootScope, $scope, $log, $uibModalInstance, coreService, user, $uibModal) {
	$scope.user=user;
	$scope.login = function () {
		coreService.login(user).then(
			function(resp){ 
				if(resp.status===200) {
					$rootScope.loginOpen=false;
					$uibModalInstance.dismiss();
					coreService.pingAuth().then(
							function(resp){   
								  if(resp.status===200 && resp.data.code==0) {
									  $rootScope.user.name=resp.data.username;
									  $rootScope.authenticated=true;
								  } else {
									  $rootScope.authenticated=false;
									  $rootScope.openLoginForm();
								  }
								  $rootScope.regularCall=true;
								});
				}
			},
			function(respErr){
				$log.log(respErr);
			}
		);
	};
	
	$scope.sendMailToRecoverPasswordFromLoginForm = function () {
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'views/recoverPasswordForm.html',
			controller : 'RecoverPasswordController',
			controllerAs : '$ctrl2',
			size : 'lg',
		});
		modalInstance.result.then(function(mensajeok) {
			if(mensajeok){
				$rootScope.authenticated=false;
				$rootScope.openLoginForm();
			}
		}, function() {
			$scope.cancelar();
		});
	}
	$scope.cancelar = function(i) {
		
	}
}
