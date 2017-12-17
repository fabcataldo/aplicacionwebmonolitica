angular.module('moduloPrincipal')
.controller('RecoverPasswordMailSentController', ['$rootScope', '$scope', '$uibModalInstance', RecoverPasswordMailSentController]);

function RecoverPasswordMailSentController($rootScope, $scope, $uibModalInstance) {
	var $ctrl3 = this;
	$ctrl3.mensajedeok="OK, ya está";
	
	$ctrl3.ok = function() {
		console.log("YA SE TERMINO EL MODAL DE MAIL ENVIADO");
		$uibModalInstance.close($ctrl3.mensajedeok);
		$uibModalInstance.dismiss();
	};
}
