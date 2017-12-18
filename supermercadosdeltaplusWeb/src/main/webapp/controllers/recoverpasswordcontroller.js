angular.module('moduloPrincipal')
.controller('RecoverPasswordController', ['$rootScope', '$scope', '$uibModalInstance', '$uibModal', 'recoverPasswordService', RecoverPasswordController]);

function RecoverPasswordController($rootScope, $scope, $uibModalInstance, $uibModal, recoverPasswordService) {
	var $ctrl2 = this;
	$ctrl2.email="";
	$ctrl2.mensajeok="Ok";
	
	$ctrl2.ok = function() {
		recoverPasswordService.sendMailToResetPasswordService($ctrl2.email).then(
				function(respuesta) {
						if(respuesta.status!=500){
							var modalInstance = $uibModal.open({
								animation : true,
								backdrop: false,
								ariaLabelledBy : 'modal-title',
								ariaDescribedBy : 'modal-body',
								templateUrl : 'views/recoverPasswordMailSent.html',
								controller : 'RecoverPasswordMailSentController',
								controllerAs : '$ctrl3',
								size : 'lg',
							});
							modalInstance.result.then(function(mensajedeokdelotromodal) {
								if(mensajedeokdelotromodal){
									$uibModalInstance.close($ctrl2.mensajeok);
									$uibModalInstance.dismiss();
								}
							}, 
							function() {
								//error del controlador del modal
							});
						}
						else{
								var modalInstance = $uibModal.open({
									animation : true,
									backdrop: false,
									ariaLabelledBy : 'modal-title',
									ariaDescribedBy : 'modal-body',
									templateUrl : 'views/recoverPasswordMailSentError.html',
									controller : 'RecoverPasswordMailSentErrorController',
									controllerAs : '$ctrlrecoverpasswordmailsenterrorcontroller',
									size : 'lg',
								});
								modalInstance.result.then(function(mensajedelcontrolador) {
									if(mensajedelcontrolador){
										$uibModalInstance.close("Hubo un error en el envío del mail");
										$uibModalInstance.dismiss();
									}
								}, 
								function() {
									//error del controlador del modal
								});		
						}
					}, 
				function(respuesta) {
					
				}
			);
	};
	$ctrl2.cancel = function() {
		$uibModalInstance.dismiss();
	};
}
