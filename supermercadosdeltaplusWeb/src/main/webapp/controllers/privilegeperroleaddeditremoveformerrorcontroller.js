angular.module('moduloPrincipal')
.controller('privilegePerRoleAddEditRemoveFormErrorController', ['$rootScope', '$scope', '$uibModalInstance', PrivilegePerRoleAddEditRemoveFormErrorController]);

function PrivilegePerRoleAddEditRemoveFormErrorController($rootScope, $scope, $uibModalInstance) {
	var $ctrlprivilegeaddeditremoveformerror = this;
	$ctrlprivilegeaddeditremoveformerror.mensajedeok="lalala";
	
	$ctrlprivilegeaddeditremoveformerror.ok = function() {
		$uibModalInstance.close($ctrlprivilegeaddeditremoveformerror.mensajedeok);
		$uibModalInstance.dismiss();
	};
}
