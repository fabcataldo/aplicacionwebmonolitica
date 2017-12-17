angular.module('moduloPrincipal')
.controller('RecoverPasswordController', ['$rootScope', '$scope', '$uibModalInstance', '$uibModal', 'recoverPasswordService', RecoverPasswordController]);

function RecoverPasswordController($rootScope, $scope, $uibModalInstance, $uibModal, recoverPasswordService) {
	var $ctrl2 = this;
	$ctrl2.email="";
	$ctrl2.mensajeok="Ok";
	
	$ctrl2.ok = function() {
		console.log("BBBBB");
		console.log($ctrl2.email);
		recoverPasswordService.sendMailToResetPasswordService($ctrl2.email).then(
				function(respuesta) {
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
							
						});
					}, 
				function(respuestaerror) {
					
				}
			);
	};
	$ctrl2.cancel = function() {
		$uibModalInstance.dismiss();
	};
}
