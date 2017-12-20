angular.module('moduloPrincipal')
.controller('rolePerUserAddEditRemoveFormErrorController', ['$rootScope', '$scope', '$uibModalInstance', RolePerUserAddEditRemoveFormErrorController]);

function RolePerUserAddEditRemoveFormErrorController($rootScope, $scope, $uibModalInstance) {
	var $ctrlroleaddeditremoveformerror = this;
	$ctrlroleaddeditremoveformerror.mensajedeok="all";
	
	$ctrlroleaddeditremoveformerror.ok = function() {
		$uibModalInstance.close($ctrlroleaddeditremoveformerror.mensajedeok);
		$uibModalInstance.dismiss();
	};
}
