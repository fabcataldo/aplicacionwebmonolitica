angular.module('moduloPrincipal')
.controller('roleRemoveFormController', ['$rootScope', '$scope', '$uibModalInstance', RoleRemoveFormController]);

function RoleRemoveFormController($rootScope, $scope, $uibModalInstance) {
	var $ctrlroleremoveformcontroller = this;
	$ctrlroleremoveformcontroller.mensajedeok="OK, ya está";
	
	$ctrlroleremoveformcontroller.ok = function() {
		$uibModalInstance.close($ctrlroleremoveformcontroller.mensajedeok);
		$uibModalInstance.dismiss();
	};
	
	$ctrlroleremoveformcontroller.cancel = function() {
		$uibModalInstance.close();
		$uibModalInstance.dismiss();
	};
}
