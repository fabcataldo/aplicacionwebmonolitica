angular.module('moduloPrincipal')
.controller('rolePerUserAddEditRemoveFormErrorController', ['$rootScope', '$scope', '$uibModalInstance', RolePerUserAddEditRemoveFormErrorController]);

function RolePerUserAddEditRemoveFormErrorController($rootScope, $scope, $uibModalInstance) {
	var $ctrlroleaddeditremoveformerror = this;
	$ctrlroleaddeditremoveformerror.mensajedeok="Hubo un error en el envío del mail";
	
	$ctrlroleaddeditremoveformerror.ok = function() {
		$uibModalInstance.close($ctrlroleaddeditremoveformerror.mensajedeok);
		$uibModalInstance.dismiss();
	};
}
