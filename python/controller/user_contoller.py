from util import Util
from usecases.user_usecase import UserUseCase
from datastore.local_datastore import LocalDataStore


class UserController:
    def __init__(self):
        self._user_use_case = UserUseCase(LocalDataStore())

    @Util.check_no_of_args(Util.get_function_params_list(UserUseCase.new_user))
    def new_user(self, *args):
        return self._user_use_case.new_user(*args)

    @Util.check_no_of_args(Util.get_function_params_list(UserUseCase.transaction))
    def new_transaction(self, *args):
        return self._user_use_case.transaction(*args)

    @Util.check_no_of_args(Util.get_function_params_list(UserUseCase.payback_dues))
    def payback_dues(self, *args):
        return self._user_use_case.payback_dues(*args)

    @Util.check_no_of_args(Util.get_function_params_list(UserUseCase.report_total_dues))
    def report_total_dues(self):
        return self._user_use_case.report_total_dues()

    @Util.check_no_of_args(Util.get_function_params_list(UserUseCase.report_users_at_credit_limit))
    def report_users_at_credit_limit(self):
        return self._user_use_case.report_users_at_credit_limit()

    @Util.check_no_of_args(Util.get_function_params_list(UserUseCase.report_dues))
    def report_dues(self, *args):
        return self._user_use_case.report_dues(*args)
