angular.module('moduloPrincipal')
.controller('privilegeRemoveFormController', ['$rootScope', '$scope', '$uibModalInstance', PrivilegeRemoveFormController]);

function PrivilegeRemoveFormController($rootScope, $scope, $uibModalInstance) {
	var $ctrlprivilegeremoveformcontroller = this;
	$ctrlprivilegeremoveformcontroller.mensajedeok="OK, ya está";
	
	$ctrlprivilegeremoveformcontroller.ok = function() {
		$uibModalInstance.close($ctrlprivilegeremoveformcontroller.mensajedeok);
		$uibModalInstance.dismiss();
	};
	
	$ctrlprivilegeremoveformcontroller.cancel = function() {
		$uibModalInstance.close();
		$uibModalInstance.dismiss();
	};
}
