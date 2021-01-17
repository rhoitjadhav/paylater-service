from controller.merchant_controller import MerchantController
from controller.user_contoller import UserController


class MainController:
    def __init__(self):
        self._merchant_controller = MerchantController()
        self._user_controller = UserController()

    def handler(self, *args):
        command_type = args[0]
        command_method = args[1]
        command_args = args[2:]

        if command_type == 'new':
            if command_method == 'merchant':
                response = self._merchant_controller.new_merchant(*command_args)

            elif command_method == 'user':
                response = self._user_controller.new_user(*command_args)

            elif command_method == 'txn':
                response = self._user_controller.new_transaction(*command_args)

            else:
                response = {'success': False, 'message': 'Error: Invalid command method!'}

        elif command_type == 'update':
            if command_method == 'merchant':
                response = self._merchant_controller.update_merchant_discount_percentage(*command_args)

            else:
                response = {'success': False, 'message': 'Error: Invalid command method!'}

        elif command_type == 'payback':
            if command_method == 'dues':
                response = self._user_controller.payback_dues(*command_args)

            else:
                response = {'success': False, 'message': 'Error: Invalid command method!'}

        elif command_type == 'report':
            if command_method == 'discount':
                response = self._merchant_controller.report_discount(*command_args)

            elif command_method == 'dues':
                response = self._user_controller.report_dues(*command_args)

            elif command_method == 'users-at-credit-limit':
                response = self._user_controller.report_users_at_credit_limit()

            elif command_method == 'total-dues':
                response = self._user_controller.report_total_dues()

            else:
                response = {'success': False, 'message': 'Error: Invalid command method!'}

        else:
            response = {'success': False, 'message': 'Error: Invalid command type!'}

        return response
