from util import Util
from usecases.merchant_usecase import MerchantUseCase
from datastore.local_datastore import LocalDataStore


class MerchantController:
    def __init__(self):
        self._merchant_use_case = MerchantUseCase(LocalDataStore())

    @Util.check_no_of_args(Util.get_function_params_list(MerchantUseCase.new_merchant))
    def new_merchant(self, *args):
        return self._merchant_use_case.new_merchant(*args)

    @Util.check_no_of_args(Util.get_function_params_list(MerchantUseCase.update_merchant_discount_percentage))
    def update_merchant_discount_percentage(self, *args):
        return self._merchant_use_case.update_merchant_discount_percentage(*args)

    @Util.check_no_of_args(Util.get_function_params_list(MerchantUseCase.report_discount))
    def report_discount(self, *args):
        return self._merchant_use_case.report_discount(*args)
