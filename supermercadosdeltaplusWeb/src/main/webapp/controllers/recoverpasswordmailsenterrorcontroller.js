angular.module('moduloPrincipal')
.controller('RecoverPasswordMailSentErrorController', ['$rootScope', '$scope', '$uibModalInstance', RecoverPasswordMailSentErrorController]);

function RecoverPasswordMailSentErrorController($rootScope, $scope, $uibModalInstance) {
	var $ctrlrecoverpasswordmailsenterrorcontroller = this;
	$ctrlrecoverpasswordmailsenterrorcontroller.mensajedeok="Hubo un error en el envío del mail";
	
	$ctrlrecoverpasswordmailsenterrorcontroller.ok = function() {
		$uibModalInstance.close($ctrlrecoverpasswordmailsenterrorcontroller.mensajedeok);
		$uibModalInstance.dismiss();
	};
}
