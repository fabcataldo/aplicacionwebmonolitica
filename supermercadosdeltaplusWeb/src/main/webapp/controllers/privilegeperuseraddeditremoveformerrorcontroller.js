angular.module('moduloPrincipal')
.controller('privilegePerUserAddEditRemoveFormErrorController', ['$rootScope', '$scope', '$uibModalInstance', PrivilegePerUserAddEditRemoveFormErrorController]);

function PrivilegePerUserAddEditRemoveFormErrorController($rootScope, $scope, $uibModalInstance) {
	var $ctrlprivilegeaddeditremoveformerror = this;
	$ctrlprivilegeaddeditremoveformerror.mensajedeok="lalala";
	
	$ctrlprivilegeaddeditremoveformerror.ok = function() {
		$uibModalInstance.close($ctrlprivilegeaddeditremoveformerror.mensajedeok);
		$uibModalInstance.dismiss();
	};
}
